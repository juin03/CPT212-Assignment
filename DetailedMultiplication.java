import java.util.Random;
import java.util.Stack;

public class DetailedMultiplication {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Random rand = new Random();
            int lowerBound = (int) Math.pow(10, i - 1);
            int upperBound = (int) Math.pow(10, i) - 1;

            int multiplier = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int multiplicand = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;

            System.out.println("==============================================================");
            System.out.println(i + " digits");
            System.out.println("Multiplier: " + multiplier);
            System.out.println("Multiplicand: " + multiplicand);
            multiply(multiplier, multiplicand);
        }
    }

    public static void multiply(int multiplier, int multiplicand) {
        String multiplierStr = Integer.toString(multiplier);
        String multiplicandStr = Integer.toString(multiplicand);
        Stack<Integer> partials = new Stack<>();
        Stack<Integer> carriers = new Stack<>();
        StringBuilder sb = new StringBuilder();

        // Result container for final assembly
        long result = 0;
        int operationCount = 0; // Initialize operation counter

        for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
            int digitM = Character.digit(multiplicandStr.charAt(i), 10);
            operationCount += 2; // For character access and digit conversion

            for (int j = multiplierStr.length() - 1; j >= 0; j--) {
                int digitN = Character.digit(multiplierStr.charAt(j), 10);
                int product = digitM * digitN;
                operationCount += 4; // For character access, digit conversion, multiplication, and condition check

                partials.push(product % 10);
                carriers.push(product / 10);
                operationCount += 2; // For two push operations
            }
            operationCount++; // For the final condition check in the inner loop

            // Print partial results
            System.out.println("\nPartial products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
            while (!partials.isEmpty()) {
                sb.append(partials.pop());
                operationCount++; // For each pop operation
            }
            System.out.println("Partial Result: " + sb.toString());
            result += Long.parseLong(sb.toString()) * Math.pow(10, multiplicandStr.length() - 1 - i);
            operationCount += 2; // For parsing and multiplication
            sb.setLength(0); // Clear StringBuilder

            // Print carrier results
            System.out.println("\nCarrier products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
            while (!carriers.isEmpty()) {
                sb.append(carriers.pop());
                operationCount++; // For each pop operation
            }
            System.out.println("Carrier Result: " + sb.toString());
            result += Long.parseLong(sb.toString()) * Math.pow(10, multiplicandStr.length() - i);
            operationCount += 2; // For parsing and multiplication
            sb.setLength(0); // Clear StringBuilder
        }
        operationCount++; // For the final condition check in the outer loop

        System.out.println("\nMultiplication result of " + multiplier + " x " + multiplicand + " = " + result);
        System.out.println("Total primitive operations: " + operationCount + "\n");
    }
}
