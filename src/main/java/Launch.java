import api.Course;
import api.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Launch {

    public static String season;


    public static void main(String[] args) throws IOException {
        configure();
        System.out.println("Welcome to Rutgers Course Sniper!");
        System.out.println("Currently sniping for " + season + " " + Configuration.year + " at " + Configuration.campus);
        System.out.println("You can begin by entering a command, type 'help' for more information.");
        waitForCommand();
    }

    public static void waitForCommand() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("rcs> ");
        String command = input.readLine().toLowerCase();
        //System.err.println(command);

        if (command.startsWith("snipesection ")) {
            String id;
            Boolean auto;
            if (command.contains("-a")){
                id = StringUtils.substringBetween(command, "snipesection ", " -");
                auto = true;
            } else {
                auto = false;
                id = StringUtils.substringAfter(command,"snipesection ");
            }
            Commands.snipesection(id, auto);
            //System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
        }

        else if (command.startsWith("snipesections ")) {
            ArrayList<String> idlist = new ArrayList<>();
            String ids[] = StringUtils.substringsBetween(command," ", ",");
            for (String id: ids) {
                idlist.add(id);
            }
            idlist.add(StringUtils.substringAfterLast(command,", "));
            //System.out.println(id);
            Commands.snipesections(idlist, false);//TODO ADD AUTO REG FUNCTION TO SNIPESECTIONS
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
            waitForCommand();
        }

        else if (command.startsWith("snipecourse ")) {
            String id;
            id = StringUtils.substringAfter(command,"snipecourse ");
            //System.out.println(id);
            Commands.snipecourse(id);
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
            waitForCommand();
        }

        else if (command.startsWith("list")) {
            Commands.list();
            waitForCommand();
        }

        else if (command.startsWith("help")) {
            //Commands.sendHelp();
            waitForCommand();


        }

        else {
            System.out.println("Invalid command usage, type 'help' for more information.");
            waitForCommand();
        }



    }

    public static void configure(){
        LocalDateTime now = LocalDateTime.now();
        Configuration.year = now.getYear();

        if(now.getMonthValue() >= 4) {
            Configuration.term = 9;
        }

        if(4 > now.getMonthValue()) {
            Configuration.term = 1;
        }

        if(Configuration.term == 9){
            season = "FALL";
        }
        else if(Configuration.term == 1){
            season = "SPRING";
        }


    }


}
