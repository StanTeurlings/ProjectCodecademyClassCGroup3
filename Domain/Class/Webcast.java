package Domain.Class;

public class Webcast {
    private String webcastTheme;
    private double webcastDuration;
    private Expert expert;

    public Webcast(String webcastTheme, double webcastDuration, Expert expert) {
        this.webcastTheme = webcastTheme;
        this.webcastDuration = webcastDuration;
        this.expert = expert;
    }

    // Getters and setters
    public String getWebcastTheme() {
        return webcastTheme;
    }

    public void setWebcastTheme(String webcastTheme) {
        this.webcastTheme = webcastTheme;
    }

    public double getWebcastDuration() {
        return webcastDuration;
    }

    public void setWebcastDuration(double webcastDuration) {
        this.webcastDuration = webcastDuration;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }
}
