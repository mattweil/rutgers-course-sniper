package api;

import java.util.ArrayList;

public class Section {
    public String courseIndex;
    public String sectionIndex;
    //public int sectionIndexInt;
    public ArrayList<String> emailNotifylist;

    public String getCourseIndex() {
        return courseIndex;
    }

    public void setCourseIndex(String courseIndex) {
        this.courseIndex = courseIndex;
    }

    public String getSectionIndex() {
        return sectionIndex;
    }

    public void setSectionIndex(String sectionIndex) {
        this.sectionIndex = sectionIndex;
    }

    //public int getSectionIndexInt() {
    //    return sectionIndexInt;
    //}

    //public void setSectionIndexInt(int sectionIndexInt) {
    //    this.sectionIndexInt = sectionIndexInt;
    //}

    public ArrayList<String> getEmailNotifylist() {
        return emailNotifylist;
    }

    public void setEmailNotifylist(ArrayList<String> emailNotifylist) {
        this.emailNotifylist = emailNotifylist;
    }
}
