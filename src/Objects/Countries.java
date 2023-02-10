package Objects;

/**This class creates the countries object for use within the application. */
public class Countries {

    private int countryID;
    private String country;

    /**This method creates the countries object.
     * @param countryID provides the country ID.
     * @param country provides the country name. */
    public Countries(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**This is the getter method for the country ID.
     * @return returns the country ID. */
    public int getCountryID() {
        return countryID;
    }

    /**This is the setter method for the country ID.
     * @param countryID provides the country ID. */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**This is the getter method for the country name.
     * @return returns the country name. */
    public String getCountry() { return country; }

    /**This is the setter method for the country name.
     * @param country provides the country name. */
    public void setCountry(String country) { this.country = country; }

    /**This method allows the country parameter to be converted into a string.
     * @return returns the country name as a string. */
    @Override
    public String toString() { return country; }

}
