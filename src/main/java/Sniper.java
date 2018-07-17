import api.Course;
import api.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Sniper {

    public static ArrayList<Course> courseList = new ArrayList<Course>();

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

                // If x greater, ignore left half
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

    public static void start() throws IOException {
        int[] watchList = grabWatchList();
        String bulkData = Request.get("openSections.gz?year=2018&term=7&campus=NB");
        int[] courseIndexes = Arrays.stream(StringUtils.substringsBetween(bulkData, "\"", "\"")).mapToInt(Integer::parseInt).toArray();
        System.out.println(bulkData);
        for (int i: watchList) { //wlist = user courses we are sniping
            if(bSearch(courseIndexes, i) == true){
                System.out.println(i + " OPEN");
            } else {
                System.out.println(i + " not open");
            }
        }

/*        for (int e: array) {
            System.out.println(e);
        }*/





    }

    private static int[] grabWatchList() throws IOException {
        int[] wlist = new int[5];
        wlist[0] = 00003;
        wlist[1] = 00005;
        wlist[2] = 00006;
        wlist[3] = 00007;
        //above is e0xecuted at runtime and gets initial data from soc
        return wlist;

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
