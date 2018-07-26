import api.Course;
import api.Request;
import api.WatchSection;

import java.io.IOException;

public class Commands {

    public static void snipesection(String id, Boolean auto) throws IOException {

        //below we add the section index to list
        WatchSection w = new WatchSection();
        w.setId(id);
        w.setAuto(auto);
        Sniper.watchList.add(w);

        if(Sniper.running == null || Sniper.running == false ){ //if sniper is not running then we run it on new thread
            Thread t = new Thread(new Sniper());
            t.start();
        }
    }

    public static void snipecourse(String id) throws IOException {
        Organizer.sort(Request.get("courses.gz?year=2018&term=9&campus=NB"));
        for (Course c: Sniper.courseList) {
            //System.out.println(c.getCourseString());
            if(c.getCourseString().equals("01:640:151")){
                System.out.println(c.getCourseTitle());
                for (String s: c.getSectionIndexes()) {
                    System.out.println(s);
                }
            }
        }

    }

}
