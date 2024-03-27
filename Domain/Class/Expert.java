package Domain.Class;

public class Expert {
    private String expertName;
    private String expertOrganisation;

    public Expert(String expertName, String expertOrganisation) {
        this.expertName = expertName;
        this.expertOrganisation = expertOrganisation;
    }

    // Getters and setters
    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertOrganisation() {
        return expertOrganisation;
    }

    public void setExpertOrganisation(String expertOrganisation) {
        this.expertOrganisation = expertOrganisation;
    }
}
