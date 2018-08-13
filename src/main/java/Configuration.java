import api.Course;
import api.Request;
import api.Section;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Configuration {
    public static int refreshRate = 10000; //rate at which the sniper checks for open sections in milliseconds (10 mintues)
    public static String campus = "NB";
    public static int year;
    public static int term;

    public static ArrayList<Course> grabCourseDefinitions() throws IOException {
        ArrayList<Course> defs;
        String response = Request.get("courses.gz?year=2018&term=9&campus=NB");
        defs = Organizer.sort(response);
        return defs;
    }

    public static void setUpCourses() throws IOException {
        ArrayList<Course> defs;
        String response = Request.get("courses.gz?year=2018&term=9&campus=NB");
        defs = Organizer.sort(response);
        for (Course c: defs) {
            //System.err.println(c.getCourseString());
            for (String sectionIndex : c.getSectionIndexes()) {
                ArrayList<String> emptyList = new ArrayList<>();
                Section section = new Section();
                section.setCourseIndex(c.getCourseString());
                section.setSectionIndex(sectionIndex);
                section.setEmailNotifylist(emptyList);

                String jsonString = new Gson().toJson(section);
                System.out.println(jsonString);
                Request.post(jsonString, "https://mattweil.net/projects/rusnipe/setupc/");

            }
        }
    }


}
