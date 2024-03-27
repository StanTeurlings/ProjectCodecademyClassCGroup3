package Domain.Class;

public class Module {
    private String version;
    private String moduleSubject;
    private ContactPerson contactPerson;
    private int orderNumber;
    private boolean inCourse;

    public Module(String version, String moduleSubject, ContactPerson contactPerson, int orderNumber,
            boolean inCourse) {
        this.version = version;
        this.moduleSubject = moduleSubject;
        this.contactPerson = contactPerson;
        this.orderNumber = orderNumber;
        this.inCourse = inCourse;
    }

    // Getters and setters
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModuleSubject() {
        return moduleSubject;
    }

    public void setModuleSubject(String moduleSubject) {
        this.moduleSubject = moduleSubject;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isInCourse() {
        return inCourse;
    }

    public void setInCourse(boolean inCourse) {
        this.inCourse = inCourse;
    }
}
