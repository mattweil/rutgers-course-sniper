import api.Course;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Organizer {

    public static void sort(String data){
        ArrayList<Course> clist = new ArrayList<Course>();
        String[] rawCourses = StringUtils.substringsBetween(data, "\"campusLocations\":", "courseNotes");
        for (String r: rawCourses) {
            ArrayList<String> slist = new ArrayList<>();
            System.out.println(r);
            Course c = new Course();

            c.setCourseTitle(StringUtils.substringBetween(r, "title\":\"", "\","));
            c.setCourseString(StringUtils.substringBetween(r, "courseString\":\"", "\","));
            String[] sectionIndexes = StringUtils.substringsBetween(r, "\"index\":\"", "\",\"");

            for (String i : sectionIndexes) {
                System.out.println(c.getCourseTitle());
                System.out.println(i);
                slist.add(i);
            }

            c.setSectionIndexes(slist);


            Sniper.courseList.add(c);
        }
    }

}
