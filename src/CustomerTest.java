import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    public void constructor() {
        Customer testCust = new Customer("123456","John","Smith",
                "jsmith@something.com","2061234567");
        assertEquals("123456", testCust.getNumber());
        assertEquals("John", testCust.getFirstName());
        assertEquals("Smith", testCust.getLastName());
        assertEquals("jsmith@something.com", testCust.getEmail());
        assertEquals("2061234567", testCust.getPhone());
    }

    @Test
    public void setFirstName() {
        Customer testCust = new Customer("123456","John","Smith",
                "jsmith@something.com","2061234567");
        testCust.setFirstName("Brian");
        assertEquals("Brian", testCust.getFirstName());
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setFirstName(""));
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setFirstName(null));
    }

    @Test
    public void setLastName() {
        Customer testCust = new Customer("123456","John","Smith",
                "jsmith@something.com","2061234567");
        testCust.setLastName("Doe");
        assertEquals("Doe", testCust.getLastName());
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setLastName(""));
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setLastName(null));
    }

    @Test
    public void setEmail() {
        Customer testCust = new Customer("123456","John","Smith",
                "jsmith@something.com","2061234567");
        testCust.setEmail("bdoe@something.com");
        assertEquals("bdoe@something.com", testCust.getEmail());
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setEmail(""));
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setEmail(null));
    }

    @Test
    public void setPhone() {
        Customer testCust = new Customer("123456","John","Smith",
                "jsmith@something.com","2061234567");
        testCust.setPhone("2039876541");
        assertEquals("2039876541", testCust.getPhone());
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setPhone(""));
        assertThrows(IllegalArgumentException.class,
                () -> testCust.setPhone(null));
    }



}