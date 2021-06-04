package simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simple.operators.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private List<Operator> supportedOperators = new ArrayList<>();

    @BeforeEach
    void setup() {
        supportedOperators = List.of(new Division(), new Multiplication(), new Subtraction(), new Addition());
    }

    @Test
    public void calculateWithUnsupportedOperatorFails() {
        Exception exception = assertThrows(Exception.class, () -> new Calculator(supportedOperators).calculate("5%4"));
        assertEquals("Operator not supported.", exception.getMessage());
    }

    @Test
    public void calculateWithInvalidInputFails() {
        Exception exception = assertThrows(Exception.class, () -> new Calculator(supportedOperators).calculate("5++4+3"));
        assertEquals("Invalid expression.", exception.getMessage());
    }

    @Test
    public void onlyAddition() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("510+40+3");
        assertEquals(553, result, 0d);
    }

    @Test
    public void additionWithDecimalNumbers() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("5.1+4.8+3");
        assertEquals(12.9, result, 0.1d);
    }

    @Test
    public void additionAndSubtraction() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("5-4+3-2+1");
        assertEquals(3, result, 0d);
    }

    @Test
    public void additionAndSubtractionAndMultiplication() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("6+5-4*3-2*1");
        assertEquals(-3, result, 0d);
    }

    @Test
    public void additionAndSubtractionAndMultiplicationAndDivision() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("6+14/3-4*3-12/4*6+9");
        assertEquals(-10.33, result, 0.3d);
    }

    @Test
    public void expressionWithInvalidParenthesisFails() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> new Calculator(supportedOperators).calculate("(6+5)/3)"));
        assertEquals("Incorrect use of parenthesis.", exception.getMessage());
    }

    @Test
    public void expressionWithParenthesis() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("(6+5)/3-(4*3-2/4*6)*2+9");
        assertEquals(-5.33, result, 0.3d);
    }


    @Test
    public void expressionWithMultiParenthesis() throws Exception {
        Double result = new Calculator(supportedOperators).calculate("((6+5)/3-4*3-2/(4*6))*2+9");
        assertEquals(-7.83, result, 0.3d);
    }
}