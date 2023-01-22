package Objects;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointments {

    private int aptID;
    private String aptTitle;
    private String aptDescription;
    private String aptLocation;
    private String aptType;
    private LocalDateTime aptStart;
    private LocalDateTime aptEnd;
    private java.sql.Date createDate;
    private String createdBy;
    private java.sql.Date lastUpdated;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;


    public Appointments(int aptID, String aptTitle, String aptDescription, String aptLocation, String aptType,
                             LocalDateTime aptStart, LocalDateTime aptEnd, java.sql.Date createDate, String createdBy,
                             java.sql.Date lastUpdated, String lastUpdatedBy, int customerID, int userID, int contactID) {

        this.aptID = aptID;
        this.aptTitle = aptTitle;
        this.aptDescription = aptDescription;
        this.aptLocation = aptLocation;
        this.aptType = aptType;
        this.aptStart = aptStart;
        this.aptEnd = aptEnd;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getAptID() {
        return aptID;
    }

    public void setAptID(int aptID) {
        this.aptID = aptID;
    }

    public String getAptTitle() {
        return aptTitle;
    }

    public void setAptTitle(String aptTitle) {
        this.aptTitle = aptTitle;
    }

    public String getAptDescription() {
        return aptDescription;
    }

    public void setAptDescription(String aptDescription) {
        this.aptDescription = aptDescription;
    }

    public String getAptLocation() {
        return aptLocation;
    }

    public void setAptLocation(String aptLocation) {
        this.aptLocation = aptLocation;
    }

    public String getAptType() {
        return aptType;
    }

    public void setAptType(String aptType) {
        this.aptType = aptType;
    }

    public LocalDateTime getAptStart() {
        return aptStart;
    }

    public void setAptStart(LocalDateTime aptStart) {
        this.aptStart = aptStart;
    }

    public LocalDateTime getAptEnd() {
        return aptEnd;
    }

    public void setAptEnd(LocalDateTime aptEnd) {
        this.aptEnd = aptEnd;
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

    public java.sql.Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(java.sql.Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
