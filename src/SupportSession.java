public record SupportSession(Tech tech, Customer customer) {

    public static String toString(Tech tech, Customer customer) {
        return tech.firstName() + " " +  tech.lastName() + " (#" + tech.number() + ") helped " + customer.firstName()
                + " " + customer.lastName() + " (#" + customer.number() + ")";
    }

}
