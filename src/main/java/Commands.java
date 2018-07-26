import api.WatchCourse;

import java.io.IOException;
import java.util.ArrayList;

public class Commands {

    public static void snipe(String id, Boolean auto) throws IOException {
        WatchCourse w = new WatchCourse();
        w.setId(id);
        w.setAuto(auto);
        Sniper.watchList.add(w);

        if(Sniper.running == null){
            Thread t = new Thread(new Sniper());
            t.start();
        }

    }

}
