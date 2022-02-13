/**
 * Customer record
 *
 * @param number        Customer's number
 * @param firstName     Customer's first name
 * @param lastName      Customer's last name
 * @param email         Customer's email address
 * @param phone         Customer's phone number
 */
public record Customer(String number, String firstName, String lastName, String email, String phone) {

    public Customer {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be blank or null.");
        } else if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be blank or null.");
        } else if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be blank or null.");
        } else if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be blank or null.");
        }
    }

}


