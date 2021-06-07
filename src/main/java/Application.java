import simple.Calculator;
import simple.operators.*;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input expression");
        String input = sc.nextLine();
        try {
            Double result = new Calculator(getSupportedOperators()).calculate(input);
            System.out.println("Result : " + result);
        } catch (Exception e) {
            System.out.println("Error encountered : " + e.getMessage());
        }
    }

    private static List<Operator> getSupportedOperators() {
        return List.of(new Division(), new Multiplication(), new Subtraction(), new Addition());
    }
}
