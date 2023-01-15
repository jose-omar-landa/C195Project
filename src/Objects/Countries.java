package Objects;

public class Countries {

    private int countryID;
    private String country;
    private java.sql.Date createDate;
    private String createdBy;
    private java.sql.Date lastUpdate;
    private String lastUpdatedBy;

    public Countries(int countryID, String country, java.sql.Date createDate, String createdBy,
                     java.sql.Date lastUpdate, String lastUpdatedBy) {

        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.sql.Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(java.sql.Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public java.sql.Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(java.sql.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}
