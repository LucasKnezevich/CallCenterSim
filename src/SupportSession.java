/**
 * Support session record.
 * @param tech      Tech in the support session
 * @param customer  Customer in the support session
 */
public record SupportSession(Tech tech, Customer customer) {

    @Override
    public String toString() {
        return String.format(tech.firstName() + " " +  tech.lastName() + " (#" + tech.number()
                + ") helped " + customer.firstName() + " " + customer.lastName() + " (#" + customer.number() + ")");
    }


}
