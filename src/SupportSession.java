/**
 * Support session record.
 * @param tech      Tech in the support session
 * @param customer  Customer in the support session
 */
public record SupportSession(Tech tech, Customer customer) {

    @Override
    public String toString() {
        return String.format("%s %s (#%s) helped %s %s (#%s)", tech.getFirstName(), tech.getLastName(), tech.getNumber(),
                customer.getFirstName(), customer.getLastName(), customer.getNumber());
    }
}
