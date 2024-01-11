package Classes;

public class Webcast {
    private String presenterName;
    private String presenterOrganisation;
    private String webcastTheme;
    private String webcastDuration;
    private String URL;

    public Webcast(String presenterName, String presenterOrganisation, String webcastTheme, 
                   String webcastDuration, String URL) {
        this.presenterName = presenterName;
        this.presenterOrganisation = presenterOrganisation;
        this.webcastTheme = webcastTheme;
        this.webcastDuration = webcastDuration;
        this.URL = URL;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    public String getPresenterOrganisation() {
        return presenterOrganisation;
    }

    public void setPresenterOrganisation(String presenterOrganisation) {
        this.presenterOrganisation = presenterOrganisation;
    }

    public String getWebcastTheme() {
        return webcastTheme;
    }

    public void setWebcastTheme(String webcastTheme) {
        this.webcastTheme = webcastTheme;
    }

    public String getWebcastDuration() {
        return webcastDuration;
    }

    public void setWebcastDuration(String webcastDuration) {
        this.webcastDuration = webcastDuration;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}