/**
 * Represents a customer
 */
public class Customer {

    /**
     * The customer's number
     */
    private final String number;

    /**
     * The customer's first name
     */
    private String firstName;

    /**
     * The customer's last name
     */
    private String lastName;

    /**
     * The customer's email
     */
    private String email;

    /**
     * The customer's phone
     */
    private String phone;

    /**
     * Customer constructor
     *
     * @param number    Customer ID number
     * @param email     Customer email
     * @param firstName Customer first name
     * @param lastName  Customer last name
     * @param phone     Customer phone number
     */
    public Customer(String number, String firstName, String lastName, String email, String phone) {
        this.number = number;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
    }

    /**
     * Retrieves the customer's number
     * @return  The customer's number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Retrieves the customer's first name
     * @return  The customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the customer's last name
     * @return  The customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the customer's email
     * @return  The customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the customer's phone number
     * @return  The customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Changes the customer's first name
     * @param firstName     The customer's first name
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be blank or null.");
        }
        this.firstName = firstName;
    }

    /**
     * Changes the customer's last name
     * @param lastName      The customer's last name
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be blank or null.");
        }
        this.lastName = lastName;
    }

    /**
     * Changes the customer's email
     * @param email     The customer's email
     */
    public void setEmail(String email) {
        if (email == null || email == "") {
            throw new IllegalArgumentException("Email cannot be blank or null.");
        }
        this.email = email;
    }

    /**
     * Changes the customer's phone number
     * @param phone     The customer's phone number
     */
    public void setPhone(String phone) {
        if (phone == null || phone == "") {
            throw new IllegalArgumentException("Phone cannot be blank or null.");
        }
        this.phone = phone;
    }
}