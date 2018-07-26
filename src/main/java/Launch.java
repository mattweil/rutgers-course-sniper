import api.Course;
import api.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Launch {


    public static void main(String[] args) throws IOException {
        System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Rutgers Course Sniper!");
        System.out.println("You can begin by entering a command, type 'help' for more information.");
        waitForCommand();
        //Sniper.start();

        //parse to array then bst
        //decide if we want to push data to mongo of just notify users directrly from here
    }

    public static void waitForCommand() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("rcs> ");
        String command = input.readLine().toLowerCase();
        System.err.println(command);

        if (command.startsWith("snipe ")) {
            String id;
            Boolean auto;
            if (command.contains("-a")){
                id = StringUtils.substringBetween(command, "snipe ", " -");
                auto = true;
            } else {
                auto = false;
                id = StringUtils.substringAfter(command,"snipe ");
                System.out.println(id);
            }
            Commands.snipe(id, auto);
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
            waitForCommand();
        }

        else if (command.startsWith("help")) {
            //Commands.sendHelp();
            waitForCommand();


        }

//        else if(command.startsWith("artist ")){
//            String artist = StringUtils.substringAfter(command,"artist ");
//            Commands.getDataforArtist(artist);
//            waitForCommand();
//        }
//
//        else if(command.startsWith("lyrics " )){
//            String song = StringUtils.substringAfter(command,"lyrics ");
//            Genius.grabLyrics(song);
//            waitForCommand();
//        }

    }

    public static void grabDefinitions() throws IOException {
        String bulkData = Request.get("courses.gz?year=2018&term=7&campus=NB");
        Organizer.sort(bulkData);
        //above is executed at runtime and gets initial data from soc

    }



}
