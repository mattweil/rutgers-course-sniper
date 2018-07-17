package api;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Course {
    public String courseTitle;
    public String courseString;
    public ArrayList<String> sectionNumbers;
    public ArrayList<String> sectionIndexes;
    public ArrayList<Boolean> sectionStatuses;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseString() {
        return courseString;
    }

    public void setCourseString(String courseString) {
        this.courseString = courseString;
    }

    public ArrayList<String> getSectionNumbers() {
        return sectionNumbers;
    }

    public void setSectionNumbers(ArrayList<String> sectionNumbers) {
        this.sectionNumbers = sectionNumbers;
    }

    public ArrayList<String> getSectionIndexes() {
        return sectionIndexes;
    }

    public void setSectionIndexes(ArrayList<String> sectionIndexes) {
        this.sectionIndexes = sectionIndexes;
    }

    public ArrayList<Boolean> getSectionStatuses() {
        return sectionStatuses;
    }

    public void setSectionStatuses(ArrayList<Boolean> sectionStatuses) {
        this.sectionStatuses = sectionStatuses;
    }
}
