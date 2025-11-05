// ConsoleCalculator.java - Works in JVDroid
import java.util.Scanner;

public class ConsoleCalculator {
    private static double memory = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🧮 CONSOLE CALCULATOR 🧮");
        System.out.println("========================");
           System.out.println("Simple Calculator Without GUI JVDROIND FRIENDLY");
        boolean running = true;
        
        while (running) {
            showMenu();
            System.out.print("Choose option (1-6): ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addition();
                    break;
                case "2":
                    subtraction();
                    break;
                case "3":
                    multiplication();
                    break;
                case "4":
                    division();
                    break;
                case "5":
                    showMemory();
                    break;
                case "6":
                    running = false;
                    System.out.println("Goodbye! 👋");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please enter 1-6");
            }
        }
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("\n┌─────────────────┐");
        System.out.println("│ 1. Addition     │");
        System.out.println("│ 2. Subtraction  │");
        System.out.println("│ 3. Multiplication│");
        System.out.println("│ 4. Division     │");
        System.out.println("│ 5. Show Memory  │");
        System.out.println("│ 6. Exit         │");
        System.out.println("└─────────────────┘");
    }
    
    private static void addition() {
        System.out.println("\n➕ ADDITION");
        double[] numbers = getNumbers();
        double result = numbers[0] + numbers[1];
        memory = result;
        System.out.println("Result: " + numbers[0] + " + " + numbers[1] + " = " + formatResult(result));
    }
    
    private static void subtraction() {
        System.out.println("\n➖ SUBTRACTION");
        double[] numbers = getNumbers();
        double result = numbers[0] - numbers[1];
        memory = result;
        System.out.println("Result: " + numbers[0] + " - " + numbers[1] + " = " + formatResult(result));
    }
    
    private static void multiplication() {
        System.out.println("\n✖️ MULTIPLICATION");
        double[] numbers = getNumbers();
        double result = numbers[0] * numbers[1];
        memory = result;
        System.out.println("Result: " + numbers[0] + " × " + numbers[1] + " = " + formatResult(result));
    }
    
    private static void division() {
        System.out.println("\n➗ DIVISION");
        double[] numbers = getNumbers();
        if (numbers[1] == 0) {
            System.out.println("❌ Error: Cannot divide by zero!");
            return;
        }
        double result = numbers[0] / numbers[1];
        memory = result;
        System.out.println("Result: " + numbers[0] + " ÷ " + numbers[1] + " = " + formatResult(result));
    }
    
    private static void showMemory() {
        System.out.println("\n💾 MEMORY: " + formatResult(memory));
    }
    
    private static double[] getNumbers() {
        double[] numbers = new double[2];
        
        System.out.print("Enter first number: ");
        numbers[0] = getValidNumber();
        
        System.out.print("Enter second number: ");
        numbers[1] = getValidNumber();
        
        return numbers;
    }
    
    private static double getValidNumber() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid number! Please enter a valid number: ");
            }
        }
    }
    
    private static String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            // Remove trailing zeros
            String str = String.valueOf(result);
            if (str.contains(".")) {
                str = str.replaceAll("0*$", "").replaceAll("\\.$", "");
            }
            return str;
        }
    }
}
