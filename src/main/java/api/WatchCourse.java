package api;

public class WatchCourse {
    String id;
    Boolean auto;
    Boolean emailNotify;
    String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    public Boolean getEmailNotify() {
        return emailNotify;
    }

    public void setEmailNotify(Boolean emailNotify) {
        this.emailNotify = emailNotify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
