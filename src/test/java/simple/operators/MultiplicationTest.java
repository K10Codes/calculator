package simple.operators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest {

    @Test
    void perform() {
        assertEquals(-8.75, new Multiplication().perform(2.5, -3.5), 0d);
    }

    @Test
    void getSymbol() {
        assertEquals('*', new Multiplication().getSymbol());
    }
}