import api.Course;
import api.Request;
import api.WatchSection;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Sniper implements Runnable {

    public static ArrayList<WatchSection> watchList = new ArrayList();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static Boolean running;


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

    public void run() {

        running = true;

        while (running == true) {
        for (int i = 0; i < watchList.size(); i++) {
            int[] watchArray = new int[watchList.size()];
            WatchSection w = watchList.get(i);
            System.out.println(i);
            watchArray[i] = Integer.parseInt(w.getId());
            String bulkData = null;
            try {
                bulkData = Request.get("openSections.gz?year=2018&term=7&campus=NB");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int[] courseIndexes = Arrays.stream(StringUtils.substringsBetween(bulkData, "\"", "\"")).mapToInt(Integer::parseInt).toArray();
            System.out.println(bulkData);
            for (int x : watchArray) {
                if (bSearch(courseIndexes, x) == true) {
                    System.out.println(x + " OPEN");
                    //ACTION TO BE TAKEN AFTER SECTION IS OPEN BELOW
                    System.err.println(w.getId());
                    System.err.println(w.getAuto());
                } else {
                    //ACTION TO BE TAKEN IF SECTION IS NOT OPEN
                    System.out.println(x + " not open");
                }
            }
            try {
                Thread.sleep(Configuration.refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Anything you insert after i will be discovered during next iterations
        }


    }




    }

    public static boolean bSearch(int array[], int item)
    {
        BinarySearch ob = new BinarySearch();
        int arr[] = array;
        int n = arr.length;
        int x = item;
        int result = ob.binarySearch(arr, x);
        if (result == -1) {
            System.out.println("Element not present");
            return false;
        }
        else {
            System.out.println("Element found at " + "index " + result);
            return true;
        }

    }





}
