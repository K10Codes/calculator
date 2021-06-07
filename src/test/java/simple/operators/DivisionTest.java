package simple.operators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {

    @Test
    void perform() {
        assertEquals(-0.7142857142857143, new Division().perform(2.5, -3.5), 0.d);
    }

    @Test
    void getSymbol() {
        assertEquals('/', new Division().getSymbol());
    }
}