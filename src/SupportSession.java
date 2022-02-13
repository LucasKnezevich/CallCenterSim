/**
 * Support session record
 * @param tech          Tech for the support session
 * @param customer      Customer for the support session
 * @param dur           Duration of the support session in minutes
 */
public record SupportSession (Tech tech, Customer customer, int dur) implements Comparable<SupportSession> {

    /**
     * Retrieves information about the support session
     * @return      The information about the support session
     */
    @Override
    public String toString() {
        return String.format("%s %s (#%s) helped %s %s (#%s), call duration was %d minutes.",
                tech.firstName(), tech.lastName(), tech.number(),
                customer.firstName(), customer.lastName(), customer.number(),
                dur);
    }

    @Override
    public int compareTo(SupportSession o) {
        return this.dur - o.dur;
    }
}
