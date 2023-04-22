package cms.stephenwongc195.model;

/**
 * This class is used to store the division id, division name, and country id of a division
 */
public class Division{
    private int divisionId;
    private int countryId;
    private String divisionName;

    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Returns division id as an int
     * @return division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * sets division id as an int
     * @return division id
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * gets division name as string
     * @return division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * sets division name as string
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * gets country id as int
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * sets country id as int
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * returns division name and division id as string
     * @return string of division name and division id
     */
    @Override
    public String toString(){
        return divisionName + " [" + divisionId + "]";
    }
}

