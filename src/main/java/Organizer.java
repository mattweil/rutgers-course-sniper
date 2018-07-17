import api.Course;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Organizer {

    public static void sort(String data){
        ArrayList<Course> clist = new ArrayList<Course>();
        String[] rawCourses = StringUtils.substringsBetween(data, "\"campusLocations\":", "courseNotes");
        for (String r: rawCourses) {
           // System.out.println(r);
            Course c = new Course();

            c.setCourseTitle(StringUtils.substringBetween(r, "title\":\"", "\","));
            c.setCourseString(StringUtils.substringBetween(r, "courseString\":\"", "\","));


            Sniper.courseList.add(c);
        }
    }

}
