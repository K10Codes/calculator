package simple.operators;

import static simple.Constants.SUBTRACTION;

public class Subtraction implements Operator {
    @Override
    public Double perform(Double n1, Double n2) {
        return n1 - n2;
    }

    @Override
    public char getSymbol() {
        return SUBTRACTION;
    }
}
