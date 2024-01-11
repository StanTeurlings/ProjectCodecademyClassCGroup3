package Classes;

public class Module {
    private String version;
    private String moduleSubject;
    private String contactPersonName;
    private String contactPersonEmail;
    private String orderNumber;

    public Module(String version, String moduleSubject, String contactPersonName,
                  String contactPersonEmail, String orderNumber) {
        this.version = version;
        this.moduleSubject = moduleSubject;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.orderNumber = orderNumber;
    }

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
