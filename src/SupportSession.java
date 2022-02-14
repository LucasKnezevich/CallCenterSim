/**
 * Support session record
 * @param tech             Tech for the support session
 * @param customer         Customer for the support session
 * @param callEndTime      Duration of the support session in minutes
 */
public record SupportSession (Tech tech, Customer customer, int callEndTime) implements Comparable<SupportSession> {

    /**
     * Retrieves information about the support session
     * @return      The information about the support session
     */
    @Override
    public String toString() {
        return String.format("%s %s (#%s) helped %s %s (#%s), call duration was %d minutes.",
                tech.firstName(), tech.lastName(), tech.number(),
                customer.firstName(), customer.lastName(), customer.number(),
                callEndTime);
    }

    /**
     * Compares two support sessions for priority
     * @param o     The support session being compared to this one.
     * @return      The priority.
     */
    @Override
    public int compareTo(SupportSession o) {
        return this.callEndTime - o.callEndTime;
    }
}
