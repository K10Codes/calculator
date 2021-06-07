package simple;

import simple.operators.Operator;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static simple.Constants.*;

public class Calculator {

    private static final String INVALID_EXPRESSION = "Invalid expression.";
    private static final String OPERATOR_NOT_SUPPORTED = "Operator not supported.";
    private static final String INCORRECT_USE_OF_PARENTHESIS = "Incorrect use of parenthesis.";

    private Map<Character, Operator> symbolToOperator;
    private List<Character> precedenceOrder;

    public Calculator(List<Operator> supportedOperators) {
        this.symbolToOperator = supportedOperators.stream().collect(Collectors.toMap(o -> o.getSymbol(), o -> o));
        this.precedenceOrder = supportedOperators.stream().map(Operator::getSymbol).collect(Collectors.toList());
    }

    public Double calculate(String input) throws Exception {
        Stack<Character> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();
        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= ZERO && chars[i] <= NINE) {
                String num = String.valueOf(chars[i]);
                while (i < chars.length - 1 && isNextCharDigitOrDecimal(chars[i + 1])) {
                    num = num + chars[i + 1];
                    i++;
                }
                operands.add(Double.valueOf(num));
                continue;
            }

            if (chars[i] == LEFT_PARENTHESIS) {
                operators.push(chars[i]);
                continue;
            }

            if (chars[i] == RIGHT_PARENTHESIS) {
                while (operators.size() > 0 && operators.peek() != LEFT_PARENTHESIS) {
                    doCalculation(operands, operators.pop());
                }
                if (operators.isEmpty()) {
                    throw new Exception(INCORRECT_USE_OF_PARENTHESIS);
                }
                operators.pop();
                continue;
            }

            Character operator = chars[i];
            while (operators.size() > 0 && calculatePrevious(operator, operators.peek())) {
                doCalculation(operands, operators.pop());
            }

            operators.push(operator);

        }
        evaluateExpression(operators, operands);

        return operands.pop();
    }

    private boolean isNextCharDigitOrDecimal(char nextChar) {
        return (nextChar >= ZERO && nextChar <= NINE) || nextChar == '.';
    }

    private boolean calculatePrevious(char currentOp, char prevOp) {
        if (prevOp == RIGHT_PARENTHESIS || prevOp == LEFT_PARENTHESIS) {
            return false;
        }
        if (precedenceOrder.indexOf(currentOp) < precedenceOrder.indexOf(prevOp)) {
            return false;
        }
        return true;
    }

    private void evaluateExpression(Stack<Character> operators, Stack<Double> operands) throws Exception {
        while (!operators.empty()) {
            doCalculation(operands, operators.pop());
        }
    }

    private void doCalculation(Stack<Double> operands, Character operator) throws Exception {
        if (operands.size() < 2) {
            throw new Exception(INVALID_EXPRESSION);
        }
        Double n1 = operands.pop();
        Double n2 = operands.pop();
        operands.push(getOperator(operator).perform(n2, n1));

    }

    private Operator getOperator(char c) throws Exception {
        Operator operator = this.symbolToOperator.getOrDefault(c, null);
        if (isNull(operator)) {
            throw new Exception(OPERATOR_NOT_SUPPORTED);
        }
        return operator;
    }
}
