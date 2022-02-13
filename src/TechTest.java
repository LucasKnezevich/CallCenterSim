import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechTest {

    @Test
    public void constructor() {
        Tech testTech = new Tech("5555","John","Smith","jsmith5");
        assertEquals("5555", testTech.number());
        assertEquals("John", testTech.firstName());
        assertEquals("Smith", testTech.lastName());
        assertEquals("jsmith5", testTech.username());

        assertThrows(IllegalArgumentException.class,
                () -> new Tech("123456","","",""));
        assertThrows(IllegalArgumentException.class,
                () -> new Tech("123456","name","",""));
        assertThrows(IllegalArgumentException.class,
                () -> new Tech("123456","name","name",""));
    }
}