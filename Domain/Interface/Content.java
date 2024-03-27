package Domain.Interface;

import java.time.LocalDate;

public interface Content {
    int getContentId();

    void setContentId(int contentId);

    LocalDate getPublicationDate();

    void setPublicationDate(LocalDate publicationDate);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);
}
