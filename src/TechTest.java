import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    TechTest says it is only hitting 88% of the lines (23/26).  However, the lines that appear to be missed are
    blank or an instance variable?
 */

class TechTest {

    @Test
    public void constructor() {
        Tech testTech = new Tech("5555","John","Smith","jsmith5");
        assertEquals("5555", testTech.getNumber());
        assertEquals("John", testTech.getFirstName());
        assertEquals("Smith", testTech.getLastName());
        assertEquals("jsmith5", testTech.getUsername());
    }

    @Test
    public void setFirstName() {
        Tech testTech = new Tech("5555","John","Smith","jsmith5");
        testTech.setFirstName("Brian");
        assertEquals("Brian", testTech.getFirstName());
        assertThrows(IllegalArgumentException.class,
                () -> testTech.setFirstName(""));
        assertThrows(IllegalArgumentException.class,
                () -> testTech.setFirstName(null));
    }

    @Test
    public void setLastName() {
        Tech testTech = new Tech("5555","John","Smith","jsmith5");
        testTech.setLastName("Doe");
        assertEquals("Doe", testTech.getLastName());
        assertThrows(IllegalArgumentException.class,
                () -> testTech.setLastName(""));
        assertThrows(IllegalArgumentException.class,
                () -> testTech.setLastName(null));
    }

    @Test
    public void setUsername() {
        Tech testTech = new Tech("5555","John","Smith","jsmith5");
        testTech.setUsername("bdoe");
        assertEquals("bdoe", testTech.getUsername());
        assertThrows(IllegalArgumentException.class,
                () -> testTech.setUsername(""));
        assertThrows(IllegalArgumentException.class,
                () -> testTech.setUsername(null));
    }
}