package api;

import java.util.ArrayList;

public class Course {
    public String courseTitle;
    public String courseString;
    public ArrayList<String> sectionIndexes;

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

    public ArrayList<String> getSectionIndexes() {
        return sectionIndexes;
    }

    public void setSectionIndexes(ArrayList<String> sectionIndexes) {
        this.sectionIndexes = sectionIndexes;
    }
}
