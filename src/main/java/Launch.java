import api.Course;
import api.Request;

import java.io.IOException;
import java.util.ArrayList;

public class Launch {



    public static void main(String[] args) throws IOException {
        grabDefinitions();
        Sniper.start();

        //parse to array then bst
        //decide if we want to push data to mongo of just notify users directrly from here
    }

    public static void grabDefinitions() throws IOException {
        String bulkData = Request.get("courses.gz?year=2018&term=7&campus=NB");
        Organizer.sort(bulkData);
        //above is executed at runtime and gets initial data from soc

    }



}
