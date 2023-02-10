package Objects;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** This class creates an Appointments object to be used within the application. */
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

    /** This method creates an appointments object.
     * @param aptID provides the appointment ID number.
     * @param aptTitle provides the appointment title.
     * @param aptDescription provides the appointment description.
     * @param aptLocation provides the appointment location.
     * @param aptType provides the appointment type.
     * @param aptStart provides the appointment start date and time.
     * @param aptEnd provides the appointment end date and time.
     * @param createDate provides the date the appointment was created.
     * @param createdBy provides who created the appointment.
     * @param lastUpdated provides the date the appointment was last updated.
     * @param lastUpdatedBy provides who last updated the appointment.
     * @param customerID provides the customer ID number.
     * @param userID provides the user ID number.
     * @param contactID provides the contact ID number. */
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

    /**This is the getter method for the appointment ID.
     * @return returns the appointment ID. */
    public int getAptID() {
        return aptID;
    }

    /**This is the setter method for the appointment ID.
     * @param aptID provides the appointment ID. */
    public void setAptID(int aptID) {
        this.aptID = aptID;
    }

    /**This is the getter method for the appointment title.
     * @return returns the appointment title. */
    public String getAptTitle() {
        return aptTitle;
    }

    /**This is the setter method for the appointment title.
     * @param aptTitle provides the appointment title. */
    public void setAptTitle(String aptTitle) {
        this.aptTitle = aptTitle;
    }

    /**This is the getter method for the appointment description.
     * @return returns the appointment description. */
    public String getAptDescription() {
        return aptDescription;
    }

    /**This is the setter method for the appointment description.
     * @param aptDescription provides the appointment description. */
    public void setAptDescription(String aptDescription) {
        this.aptDescription = aptDescription;
    }

    /**This is the getter method for the appointment location.
     * @return returns the appointment location. */
    public String getAptLocation() {
        return aptLocation;
    }

    /**This is the setter method for the appointment location.
     * @param aptLocation provides the appointment location. */
    public void setAptLocation(String aptLocation) {
        this.aptLocation = aptLocation;
    }

    /**This is the getter method for the appointment type.
     * @return returns the appointment type. */
    public String getAptType() {
        return aptType;
    }

    /**This is the setter method for the appointment type.
     * @param aptType provides the appointment type. */
    public void setAptType(String aptType) {
        this.aptType = aptType;
    }

    /**This is the getter method for the appointment start date and time.
     * @return returns the appointment start date and time. */
    public LocalDateTime getAptStart() {
        return aptStart;
    }

    /**This is the setter method for the appointment start date and time.
     * @param aptStart provides the appointment start date and time. */
    public void setAptStart(LocalDateTime aptStart) {
        this.aptStart = aptStart;
    }

    /**This is the getter method for the appointment end date and time.
     * @return returns the appointment end date and time. */
    public LocalDateTime getAptEnd() {
        return aptEnd;
    }

    /**This is the setter method for the appointment end date and time.
     * @param aptEnd provides the appointment end date and time. */
    public void setAptEnd(LocalDateTime aptEnd) {
        this.aptEnd = aptEnd;
    }

    /**This is the getter method for the appointment creation date.
     * @return returns the appointment creation date. */
    public java.sql.Date getCreateDate() {
        return createDate;
    }

    /**This is the setter method for the appointment creation date.
     * @param createDate provides the creation date. */
    public void setCreateDate(java.sql.Date createDate) {
        this.createDate = createDate;
    }

    /**This is the getter method for the appointment creator data.
     * @return returns the appointment creator data. */
    public String getCreatedBy() {
        return createdBy;
    }

    /**This is the setter method for the appointment creator data.
     * @param createdBy provides the appointment creator data. */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**This is the getter method for the appointment last updated data.
     * @return returns the appointment last updated data. */
    public java.sql.Date getLastUpdated() {
        return lastUpdated;
    }

    /**This is the setter method for the appointment last updated data.
     * @param lastUpdated provides the appointment last updated data. */
    public void setLastUpdated(java.sql.Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**This is the getter method for the appointment last updated by data.
     * @return returns the appointment last updated by data. */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**This is the setter method for the appointment last updated by data.
     * @param lastUpdatedBy provides the appointment last updated by data.*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**This is the getter method for the appointment customer ID.
     * @return returns the appointment customer ID. */
    public int getCustomerID() {
        return customerID;
    }

    /**This is the setter method for the appointment custoemr ID.
     * @param customerID provides the appointment customer ID. */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**This is the getter method for the appointment user ID.
     * @return returns the appointment user ID. */
    public int getUserID() {
        return userID;
    }

    /**This is the setter method for the appointment user ID.
     * @param userID provides the appointment user ID. */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**This is the getter method for the appointment contact ID.
     * @return returns the appointment contact ID. */
    public int getContactID() {
        return contactID;
    }

    /**This is the setter method for the appointmnet contact ID.
     * @param contactID provides the appointment contact ID. */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
