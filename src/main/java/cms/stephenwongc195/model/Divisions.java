package cms.stephenwongc195.model;

public class Divisions extends Country {
    private int divisionId;
    private String divisionName;

    public Divisions(int countryId, String countryName, int divisionId, String divisionName) {
        super(countryId, countryName);
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}

