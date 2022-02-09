/**
 * Represents a tech
 */
public class Tech {

    /**
     * Tech's number
     */
    private final String number;

    /**
     * Tech's first name
     */
    private String firstName;

    /**
     * Tech's last name
     */
    private String lastName;

    /**
     * Tech's username
     */
    private String username;

    /**
     * Tech constructor
     *
     * @param number    Tech ID number
     * @param firstName Tech first name
     * @param lastName  Tech last name
     * @param username  Tech username
     */
    public Tech(String number, String firstName, String lastName, String username) {
        this.number = number;
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
    }

    /**
     * Retrieve the tech number
     * @return  The tech number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Retrieve the tech's first name
     * @return  The tech's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieve the tech's last name
     * @return  The tech's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieve the tech's username
     * @return  The tech's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Changes the tech's first name
     * @param firstName     The new first name; must not be null or empty
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be blank or null.");
        }
        this.firstName = firstName;
    }

    /**
     * Changes the tech's last name
     * @param lastName      The new last name; must not be null or empty
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be blank or null.");
        }
        this.lastName = lastName;
    }

    /**
     * Changes the tech's username
     * @param username      The new username; must not be null or empty
     */
    public void setUsername(String username) {
        if (username == null || username == "") {
            throw new IllegalArgumentException("Username cannot be blank or null.");
        }
        this.username = username;
    }
}