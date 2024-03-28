package Domain.Class;

import java.util.Date;

public class Content {
    private int contentId;
    private Date publicationDate;
    private String status;
    private String title;
    private String description;
    private Course course;

    public Content(int contentId, Date publicationDate, String status, String title, String description, Course course) {
        this.contentId = contentId;
        this.publicationDate = publicationDate;
        this.status = status;
        this.title = title;
        this.description = description;
        this.course = course;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}