/**
 * Support session record.
 * @param tech      Tech in the support session
 * @param customer  Customer in the support session
 */
public record SupportSession (Tech tech, Customer customer, int endTime) {

    @Override
    public String toString() {
        return String.format("%s %s (#%s) helped %s %s (#%s), call time %d minutes.",
                tech.firstName(), tech.lastName(), tech.number(),
                customer.firstName(), customer.lastName(), customer.number(),
                endTime);
    }
}
