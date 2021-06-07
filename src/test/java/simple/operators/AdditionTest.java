package simple.operators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {

    @Test
    void perform() {
        assertEquals(-1, new Addition().perform(2.5, -3.5), 0d);
    }

    @Test
    void getSymbol() {
        assertEquals('+', new Addition().getSymbol());
    }
}