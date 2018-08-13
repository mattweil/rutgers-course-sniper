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
        Commands.webMode();
        //waitForCommand();
    }

    public static void waitForCommand() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("rcs> ");
        String command = input.readLine().toLowerCase();
        //System.err.println(command);



        if (command.startsWith("help")) {
            //Commands.sendHelp();
            waitForCommand();


        }

        else if (command.startsWith("web")) {
            Commands.webMode();
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
        }

        else if (command.startsWith("setupc")) {
            Commands.setupc();
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
