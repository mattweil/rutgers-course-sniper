import api.Course;
import api.Section;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Database {

    public static ArrayList<Section> webWatch(ArrayList<Course> courseData){
        MongoClient mc = new MongoClient("localhost", 27017);
        ArrayList<Course> courseDefs = courseData;
        ArrayList<Section> webWatchList = new ArrayList<>();
        MongoDatabase db = mc.getDatabase("rusnipe");
        MongoCollection sectionColl = db.getCollection("sections");
        //MongoCollection courseColl = db.getCollection("courses"); //course collection be turned into sections
        String[] emptyArray = new String[0];
        BasicDBObject query = new BasicDBObject("usersToNotify.email", new BasicDBObject("$exists", true).append("$ne", emptyArray));


        MongoCursor<Document> sectionCursor = sectionColl.find(query).iterator();
        //MongoCursor<Document> courseCursor = courseColl.find().iterator();


        if (!sectionCursor.hasNext()) {
            System.out.println("No sections to snipe...");
        } else {

            do {
                //System.out.println(sectionCursor.hasNext());
                Section s = new Section();
                ArrayList<String> userEmails = new ArrayList<>();
                String dbData = sectionCursor.next().toString();
                String emailChunk = StringUtils.substringBetween(dbData, "email=[", "]}");
                String emails[] = emailChunk.split(", ");
                String sectionIndex = StringUtils.substringBetween(dbData, "sectionIndex=", ",");
                String courseIndex = StringUtils.substringBetween(dbData, "courseIndex=", ",");

                for (String e: emails) {
                    //System.out.println(e);
                    e = e.replaceAll("\\s+","");
                    userEmails.add(e);
                }

                s.setCourseIndex(courseIndex);
                s.setSectionIndex(sectionIndex);
                s.setEmailNotifylist(userEmails);
                //s.sectionIndex = sectionIndex;
                //s.emailNotifylist = userEmails;
                webWatchList.add(s);
            } while (sectionCursor.hasNext());
            sectionCursor.close();
        }

        mc.close();
        return webWatchList;

    }


}
