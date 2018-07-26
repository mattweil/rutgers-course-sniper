import api.Course;
import api.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Launch {


    public static void main(String[] args) throws IOException {
        System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Rutgers Course Sniper!");
        System.out.println("You can begin by entering a command, type 'help' for more information.");
        waitForCommand();
    }

    public static void waitForCommand() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("rcs> ");
        String command = input.readLine().toLowerCase();
        System.err.println(command);

        if (command.startsWith("snipesection ")) {
            String id;
            Boolean auto;
            if (command.contains("-a")){
                id = StringUtils.substringBetween(command, "snipesection ", " -");
                auto = true;
            } else {
                auto = false;
                id = StringUtils.substringAfter(command,"snipesection ");
                System.out.println(id);
            }
            Commands.snipesection(id, auto);
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
            waitForCommand();
        }

        else if (command.startsWith("snipecourse ")) {
            String id;
            id = StringUtils.substringAfter(command,"snipecourse ");
            System.out.println(id);
            Commands.snipecourse(id);
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
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


}
