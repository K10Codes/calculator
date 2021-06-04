package simple.operators;

import static simple.Constants.DIVISION;

public class Division implements Operator {
    @Override
    public Double perform(Double n1, Double n2) {
        return n1/n2;
    }

    @Override
    public char getSymbol() {
        return DIVISION;
    }
}
