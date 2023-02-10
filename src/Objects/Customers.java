package Objects;

/**This class creates a customers object for use within the application. */
public class Customers {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String postalCode;
    private String customerPhone;
    private java.sql.Date createDate;
    private String createdBy;
    private java.sql.Date lastUpdated;
    private String lastUpdatedBy;
    private int divisionID;
    private String country;
    private String division;

    /**This method creates the customers object.
     * @param customerID provides the customer ID number.
     * @param customerName provides the customer name.
     * @param customerAddress provides the customer address.
     * @param postalCode provides the customer postal code.
     * @param customerPhone provides the customer phone number.
     * @param createDate provides the creation date.
     * @param createdBy provides the created by data.
     * @param lastUpdated provides the last updated date.
     * @param lastUpdatedBy provides the last updated by data.
     * @param divisionID provides the division ID.
     * @param country provides the country.
     * @param division provides the division. */
    public Customers(int customerID, String customerName, String customerAddress, String postalCode, String customerPhone,
                     java.sql.Date createDate, String createdBy, java.sql.Date lastUpdated, String lastUpdatedBy, int divisionID, String country, String division) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.customerPhone = customerPhone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.country = country;
        this.division = division;

    }

    /**This is the getter method for the customer ID.
     * @return returns the customer ID. */
    public int getCustomerID() {
        return customerID;
    }

    /**This is the setter method for the customer ID.
     * @param customerID provides the customer ID. */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**This is the getter method for the customer name.
     * @return returns the customer name. */
    public String getCustomerName() {
        return customerName;
    }

    /**This is the setter method for the customer name.
     * @param customerName provides the customer name. */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**This is the getter method for the customer address.
     * @return returns the customer address. */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**This is the setter method for the customer address.
     * @param customerAddress provides the customer address. */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**This is the getter method for the customer postal code.
     * @return returns the customer postal code. */
    public String getPostalCode() {
        return postalCode;
    }

    /**This is the setter method for the customer postal code.
     * @param postalCode provides the customer postal code. */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**This is the getter method for the customer phone number.
     * @return retuns the customer phone number. */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**This is the setter method for the customer phone number.
     * @param customerPhone provides the customer phone number. */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**This is the getter method for the customer creation date.
     * @return returns the customer creation date. */
    public java.sql.Date getCreateDate() {
        return createDate;
    }

    /**This is the setter method for the customer creation date.
     * @param createDate provides the customer creation date. */
    public void setCreateDate(java.sql.Date createDate) {
        this.createDate = createDate;
    }

    /**This is the getter method for the created by data.
     * @return returns the created by data. */
    public String getCreatedBy() {
        return createdBy;
    }

    /**This is the setter method for the created by data.
     * @param createdBy provides the created by data. */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**This is the getter method for the last updated date data.
     * @return returns the last updated date. */
    public java.sql.Date getLastUpdated() {
        return lastUpdated;
    }

    /**This is the setter method for the last updated date data.
     * @param lastUpdated provides the last updated date.*/
    public void setLastUpdated(java.sql.Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**This is the getter method for the last updated by data.
     * @return returns the last updated by data. */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**This is the setter method for the last updated by data.
     * @param lastUpdatedBy provides the last updated by data. */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**This is the getter method for the division ID.
     * @return returns the division ID. */
    public int getDivisionID() {
        return divisionID;
    }

    /**This is the setter method for the customer division ID.
     * @param divisionID provides the customer division ID. */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**This is the getter method for the customer country.
     * @return returns the customer country. */
    public String getCountry() { return country; }

    /**This is the setter method for the customer country.
     * @param country provides the customer country. */
    public void setCountry(String country) {
        this.country = country;
    }

    /**This method converts the customer country into a string.
     * @return returns the customer country as a string. */
    @Override
    public String toString() {
        return country;
    }

    /**This is the getter method for the customer division.
     * @return returns the customer division. */
    public String getDivision() { return division; }

    /**This is the setter method for the customer division.
     * @param division provides the customer division. */
    public void setDivision(String division) { this.division = division; }


}
