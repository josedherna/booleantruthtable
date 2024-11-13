import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parsing infixToPostfix = new Parsing();
        TruthTable t = new TruthTable();
        int choice = 0;
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.println("Select one of the options:");
            System.out.println("0. Exit");
            System.out.println("1. Generate truth table");
            choice = userInput.nextInt();
            userInput.nextLine();
            switch(choice) {
                case 0:
                    System.out.println("Successfully exited");
                    break;
                case 1:
                    System.out.println("Enter infix boolean expression: ");
                    String expression = userInput.nextLine();
                    String converted = infixToPostfix.convert(expression);
                    System.out.println("Postfix Notation: " + converted);
                    t.generation(converted, expression);
                    break;
                default:
                    System.out.println("Please enter valid number");
                    break;
            }
        }
        while (choice != 0);
    }
}
