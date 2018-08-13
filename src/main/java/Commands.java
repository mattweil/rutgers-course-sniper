import api.Course;
import api.Request;
import api.WatchSection;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.ArrayList;

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
        list();
        Launch.waitForCommand();
    }

    public static void webMode() throws IOException {
        ArrayList<Course> courseDefinitions = Configuration.grabCourseDefinitions();
        Thread t = new Thread(new WebSniper(courseDefinitions));
        t.start();
    }

    public static void setupc() throws IOException {
        Configuration.setUpCourses();
    }

    public static void snipesections(ArrayList<String> ids, Boolean auto) throws IOException {
        for (String id:ids) {
            WatchSection w = new WatchSection();
            w.setId(id);
            w.setAuto(auto);
            Sniper.watchList.add(w);
        }

        //below we add the section index to list


        if(Sniper.running == null || Sniper.running == false ){ //if sniper is not running then we run it on new thread
            Thread t = new Thread(new Sniper());
            t.start();
        }
        list();
        Launch.waitForCommand();
    }

    public static void snipecourse(String id) throws IOException {
        Organizer.sort(Request.get("courses.gz?year=" + Configuration.year + "&term=" + Configuration.term + "&campus=NB"));
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

    public static void list() {

        System.out.print("Currently watching sections: [");
        for (int i = 0; i < Sniper.watchList.size(); i++) {
            if(i == Sniper.watchList.size() - 1 ){
                System.out.print(Sniper.watchList.get(i).getId() + "]");
            }

            else {
                System.out.print(Sniper.watchList.get(i).getId() + ", ");
            }

        }
        System.out.println("");

    }

}
