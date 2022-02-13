/**
 * Tech record
 *
 * @param number        Tech ID number
 * @param firstName     Tech first name
 * @param lastName      Tech last name
 * @param username      Tech username
 */
public record Tech(String number, String firstName, String lastName, String username) {

    public Tech {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be blank or null.");
        } else if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be blank or null.");
        } else if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank or null.");
        }
    }
}
