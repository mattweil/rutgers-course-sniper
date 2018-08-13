import api.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRules;
import java.util.*;

public class WebSniper implements Runnable {

    //public static ArrayList<WatchSection> watchList = new ArrayList();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static Boolean running;


    public WebSniper(ArrayList<Course> data) {
        this.courseList = data;
    }

    static class BinarySearch {
        // Returns index of x if it is present in arr[],
        // else return -1
        int binarySearch(int arr[], int x) {
            int l = 0, r = arr.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;

                // Check if x is present at mid
                if (arr[m] == x)
                    return m;

                // If x greater, ignore left halfs
                if (arr[m] < x)
                    l = m + 1;

                    // If x is smaller, ignore right half
                else
                    r = m - 1;
            }

            // if we reach here, then element was
            // not present
            return -1;
        }
    }

    public static boolean bSearch(int array[], int item) {
        BinarySearch ob = new BinarySearch();
        int arr[] = array;
        int n = arr.length;
        int x = item;
        int result = ob.binarySearch(arr, x);
        if (result == -1) {
            //System.out.println("Element not present");
            return false;
        } else {
            //System.out.println("Element found at " + "index " + result);
            return true;
        }

    }

    public void run() {

        while (0 < 1) {

            boolean webRegOnline = true;

            try {
                Thread.sleep(Configuration.refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final ZoneId zoneId = ZoneId.of("America/Puerto_Rico");

            LocalTime now = LocalTime.now(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
            String cT = formatter.format(now);



            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

            String webRegOfflineSTART = "01:59";
            String webRegOfflineEND = "06:30";


            LocalTime startTime = LocalTime.parse(webRegOfflineSTART, format);
            LocalTime endTime = LocalTime.parse(webRegOfflineEND, format);
            LocalTime targetTime = LocalTime.parse(cT, format);


            if (targetTime.isBefore(endTime) && targetTime.isAfter(startTime) ) {
                webRegOnline = false;
                System.out.println("Current time: " + cT + " | WebReg online: " + webRegOnline);
            } else {
                webRegOnline = true;
                System.out.println("Current time: " + cT + " | WebReg online: " + webRegOnline);
            }

            if(webRegOnline) {

                ArrayList<Section> notifyList = new ArrayList<>();
                ArrayList<Section> watchList = Database.webWatch(courseList);

                for (Section s : watchList) {

                    int sectionIndex = Integer.parseInt(s.getSectionIndex());

                    String bulkData = null;
                    try {
                        bulkData = Request.get("openSections.gz?year=" + Configuration.year + "&term=" + Configuration.term + "&campus=" + Configuration.campus);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    int[] openSectionIndexes = Arrays.stream(StringUtils.substringsBetween(bulkData, "\"", "\"")).mapToInt(Integer::parseInt).toArray();

                    if (bSearch(openSectionIndexes, sectionIndex) == true) {
                        notifyList.add(s);
                        //ArrayUtils.removeElement(watchArray, test);
                        System.out.println(sectionIndex + " is [OPEN]");

                    } else {
                        //ACTION TO BE TAKEN IF SECTION IS NOT OPEN
                        System.out.println(sectionIndex + " is [CLOSED]");
                    }


                }

                notifyObject sections = new notifyObject();
                ArrayList<innerNotifyObject> is = new ArrayList<innerNotifyObject>();

                for (Section s : notifyList) {
                    System.out.println("E" + s.getCourseIndex());
                    innerNotifyObject ino = new innerNotifyObject();
                    ino.setCourseIndex(s.getCourseIndex());
                    ino.setSectionIndex(s.getSectionIndex());
                    ino.setEmailList(s.getEmailNotifylist());
                    is.add(ino);
                }

                sections.setSection(is);

                String jsonString = new Gson().toJson(sections);
                System.out.println(jsonString);


                try {
                    Request.post(jsonString, "https://mattweil.net/projects/rusnipe/local");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        }

    }
}