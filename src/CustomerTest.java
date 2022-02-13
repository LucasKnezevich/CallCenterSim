import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    public void constructor() {
        Customer testCust = new Customer("123456","John","Smith",
                "jsmith@something.com","2061234567");
        assertEquals("123456", testCust.number());
        assertEquals("John", testCust.firstName());
        assertEquals("Smith", testCust.lastName());
        assertEquals("jsmith@something.com", testCust.email());
        assertEquals("2061234567", testCust.phone());

        assertThrows(IllegalArgumentException.class,
                () -> new Customer("132654","","","",""));
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("132654","name","","",""));
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("132654","name","name","",""));
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("132654","name","name","email",""));
    }
}