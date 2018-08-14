package api;

import java.util.ArrayList;

public class innerNotifyObject {
    private String courseIndex;
    private String sectionIndex;
    private ArrayList<String> emailList;

    public String getCourseIndex() {
        return courseIndex;
    }


    public String getSectionIndex() {
        return sectionIndex;
    }

    public void setSectionIndex(String sectionIndex) {
        this.sectionIndex = sectionIndex;
    }

    public void setCourseIndex(String courseIndex) {
        this.courseIndex = courseIndex;
    }

    public ArrayList<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }
}
