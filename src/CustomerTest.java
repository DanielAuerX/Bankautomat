import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {



    @Test
    void shouldReturnHerrMeier(){
        Customer herrMeier = new Customer("Hans", "Meier", 0, 1001);
        assertEquals("Herr Meier", herrMeier.greeting());
    }

}