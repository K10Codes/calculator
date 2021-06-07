package simple.operators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionTest {

    @Test
    void perform() {
        assertEquals(6.0, new Subtraction().perform(2.5, -3.5), 0d);
    }

    @Test
    void getSymbol() {
        assertEquals('-', new Subtraction().getSymbol());
    }
}