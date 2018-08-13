import api.Course;
import api.Request;
import api.WatchSection;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.ArrayList;

public class Commands {



    public static void webMode() throws IOException {
        ArrayList<Course> courseDefinitions = Configuration.grabCourseDefinitions();
        Thread t = new Thread(new WebSniper(courseDefinitions));
        t.start();
    }

    public static void setupc() throws IOException {
        Configuration.setUpCourses();
    }


}
