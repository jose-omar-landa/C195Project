package Objects;

/**This class creates the contacts object for use within the application. */
public class Contacts {

    private int contactID;
    private String contactName;
    private String email;

    /**This method creates the contacts object.
     * @param contactID provides the contact ID.
     * @param contactName provides the contact name.
     * @param email provides the contact email address. */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**This is the getter method for the contact ID.
     * @return returns the contact ID. */
    public int getContactID() {
        return contactID;
    }

    /**This is the setter method for the contact ID.
     * @param contactID provides the contact ID. */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**This is the getter method for the contact name.
     * @return returns the contact name. */
    public String getContactName() {
        return contactName;
    }

    /**This is the setter method for the contact name.
     * @param contactName provides the contact name. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**This is the getter method for the contact email.
     * @return returns the contact email. */
    public String getEmail() {
        return email;
    }

    /**This is the setter method for the contact email.
     * @param email provides the contact email. */
    public void setEmail(String email) {
        this.email = email;
    }

}
