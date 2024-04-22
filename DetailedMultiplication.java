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

        for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
            int digitM = Character.digit(multiplicandStr.charAt(i), 10);

            for (int j = multiplierStr.length() - 1; j >= 0; j--) {
                int digitN = Character.digit(multiplierStr.charAt(j), 10);
                int product = digitM * digitN;

                partials.push(product % 10);
                carriers.push(product / 10);
            }

            // Print partial results
            System.out.println("\nPartial products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
            while (!partials.isEmpty()) {
                sb.append(partials.pop());
            }
            System.out.println("Partial Result: " + sb.toString());
            result += Long.parseLong(sb.toString()) * Math.pow(10, multiplicandStr.length() - 1 - i);
            sb.setLength(0); // Clear StringBuilder

            // Print carrier results
            System.out.println("\nCarrier products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
            while (!carriers.isEmpty()) {
                sb.append(carriers.pop());
            }
            System.out.println("Carrier Result: " + sb.toString());
            result += Long.parseLong(sb.toString()) * Math.pow(10, multiplicandStr.length() - i);
            sb.setLength(0); // Clear StringBuilder
        }

        System.out.println("\nMultiplication result of " + multiplier + " x " + multiplicand + " = " + result + "\n");
    }
}




