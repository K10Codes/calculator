package simple.operators;

import static simple.Constants.DIVISION;

public class Division implements Operator {
    @Override
    public Double perform(Double n1, Double n2) {
        if (n2 == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return n1 / n2;
    }

    @Override
    public char getSymbol() {
        return DIVISION;
    }
}
