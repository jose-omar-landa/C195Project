package Objects;

/**This class creates a Users objects for use within the application. */
public class Users {

    private int userID;
    private String userName;
    private String password;

    /**This method creates a Users object.
     * @param userID provides the user ID.
     * @param userName provides the username.
     * @param password provides the password. */
    public Users(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    /**This is the getter method for the user ID.
     * @return returns the user ID number. */
    public int getUserID() {
        return userID;
    }

    /**This is the setter method for the user ID.
     * @param userID provides the user ID number. */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**This is the getter method for the username.
     * @return returns the username. */
    public String getUserName() {
        return userName;
    }

    /**This is the setter method for the username.
     * @param userName provides the username. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**This is the getter method for the password.
     * @return returns the password. */
    public String getPassword() {
        return password;
    }

    /**This is the setter method for the password.
     * @param password provides the password. */
    public void setPassword(String password) {
        this.password = password;
    }

    /**This method converts the username into a String.
     * @return returns the username as String. */
    @Override
    public String toString() {
        return userName;
    }


}
