package Objects;

/**This class creates the divisions object for use within the application. */
public class Divisions {

    private int divisionID;
    private String division;
    private int countryID;

    /**This method creates the Divisions object.
     * @param divisionID provides the division ID.
     * @param division provides the division name.
     * @param countryID provides the country name. */
    public Divisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**This is the getter method for the division ID.
     * @return returns the division ID number. */
    public int getDivisionID() {
        return divisionID;
    }

    /**This is the setter method for the division ID.
     * @param divisionID provides the division ID number. */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**This is the getter method for the division name.
     * @return returns the division name. */
    public String getDivision() {
        return division;
    }

    /**This is the setter method for the division name.
     * @param division provides the division name. */
    public void setDivision(String division) {
        this.division = division;
    }

    /**This is the getter method for the country ID.
     * @return returns the country ID number. */
    public int getCountryID() {
        return countryID;
    }

    /**This is the setter method for the country ID number.
     * @param countryID provides the country ID number. */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**This method converts the division into a String.
     * @return returns division as a String. */
    @Override
    public String toString() {
        return division;
    }

}
