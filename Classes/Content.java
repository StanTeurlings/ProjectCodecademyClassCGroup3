package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Content {
    private String contentID;
    private Date publicationDate;
    private String status;
    private String title;
    private String description;
    private List<Module> modules;
    private List<Webcast> webcasts;

    public Content(String contentID, Date publicationDate, String status, String title, String description) {
        this.contentID = contentID;
        this.publicationDate = publicationDate;
        this.status = status;
        this.title = title;
        this.description = description;
        this.modules = new ArrayList<>();
        this.webcasts = new ArrayList<>();
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public List<Webcast> getWebcasts() {
        return webcasts;
    }

    public void setWebcasts(List<Webcast> webcasts) {
        this.webcasts = webcasts;
    }

    public void addWebcast(Webcast webcast) {
        this.webcasts.add(webcast);
    }
}
