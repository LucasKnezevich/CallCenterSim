/**
 * Customer record.
 * @param number        Customer ID number
 * @param email         Customer email
 * @param firstName     Customer first name
 * @param lastName      Customer last name
 * @param phone         Customer phone number
 */
public record Customer(String number, String firstName, String lastName, String email, String phone) {
}
