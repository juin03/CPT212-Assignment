import java.util.Stack;
import java.util.Random;

public class Part1 {
    public static void main(String[] args) {
        // Clear the console
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 1; i <= 10; i++) {
            int n = i; // number of digits, but the weakness is that it can only handle up to 10 digits
            Random rand = new Random();

            int lowerBound = (int) Math.pow(10, n - 1);
            int upperBound = (int) Math.pow(10, n) - 1;

            int multiplier = rand.nextInt(upperBound - lowerBound) + lowerBound;
            int multiplicand = rand.nextInt(upperBound - lowerBound) + lowerBound;
            System.out.println("==============================================================");
            System.out.println(n + " digits");
            System.out.println("Multiplier: " + multiplier);
            System.out.println("Multiplicand: " + multiplicand);
            System.out.println(
                    "\nMultiplication result of " + multiplier + " x " + multiplicand + " = "
                            + multiply(multiplier, multiplicand) + "\n");
        }

    }

    public static long multiply(int multiplier, int multiplicand) {
        long result = 0;
        StringBuilder sb = new StringBuilder();
        String partial = "";
        String carrier = "";
        Stack<Integer> stack_partial = new Stack();
        Stack<Integer> stack_carrier = new Stack();

        // Convert the numbers to strings
        String multiplicand_str = String.valueOf(multiplicand);
        String multiplier_str = String.valueOf(multiplier);

        int counter = 0;
        // Perform multiplication digit by digit
        for (int i = multiplicand_str.length() - 1; i >= 0; i--) {
            for (int j = multiplier_str.length() - 1; j >= 0; j--) {

                int product = (multiplicand_str.charAt(i) - '0') * (multiplier_str.charAt(j) - '0');

                if (product >= 10) {
                    stack_partial.push(product % 10);
                    stack_carrier.push(product / 10);
                } else {
                    stack_partial.push(product);
                    stack_carrier.push(0);
                }

            }
            System.out.println("\nPartial products for " + multiplier_str + " x " + multiplicand_str.charAt(i));
            // Pop stack_partial and append to a string
            while (!stack_partial.isEmpty()) {
                partial = String.valueOf(stack_partial.pop());
                sb.append(partial);
            }
            System.out.println("Partial Result: " + sb.toString());
            for (int k = 0; k < counter; k++) {
                sb.append("0");
            }
            result += Long.valueOf(sb.toString());

            sb.setLength(0); // Clear sb after printing

            System.out.println("\nCarrier products for " + multiplier_str + " x " + multiplicand_str.charAt(i));

            // Pop stack_carrier and append to a string
            while (!stack_carrier.isEmpty()) {
                carrier = String.valueOf(stack_carrier.pop());
                sb.append(carrier);
            }
            System.out.println("Carrier Result: " + sb.toString());
            for (int k = 0; k < counter + 1; k++) {
                sb.append("0");
            }
            result += Long.valueOf(sb.toString());

            sb.setLength(0); // Clear sb after printing

            // Clear the queues
            stack_partial.clear();
            stack_carrier.clear();
            counter++;
        }

        // Return the final result as a string
        return result;
    }
}