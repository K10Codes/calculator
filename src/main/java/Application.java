import simple.Calculator;
import simple.operators.*;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String arg[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input expression");
        String input = sc.nextLine();
        new Calculator(getSupportedOperators()).calculate(input);
    }

    private static List<Operator> getSupportedOperators() {
        return List.of(new Division(), new Multiplication(), new Subtraction(), new Addition());
    }
}
