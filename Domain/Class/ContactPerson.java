package Domain.Class;

public class ContactPerson {
    private String contactPersonName;
    private String contactPersonEmail;

    public ContactPerson(String contactPersonName, String contactPersonEmail) {
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
    }

    // Getters and setters
    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }
}
