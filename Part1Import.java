import java.math.BigInteger;

public class Part1Import {
    public static void main(String[] args) {
        // Loop through 1 to 10 digits
        for (int n = 1; n <= 10; n++) {
            BigInteger lowerBound = BigInteger.TEN.pow(n - 1);
            BigInteger upperBound = BigInteger.TEN.pow(n).subtract(BigInteger.ONE);

            BigInteger multiplier = pseudoRandom(lowerBound, upperBound);
            BigInteger multiplicand = pseudoRandom(lowerBound, upperBound);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LINE BREAK~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~·~~~~~~~~~~~~~~~~~~~~~~~~\n");
            System.out.println("Number of digits, n: " + n);
            System.out.println("Multiplicand: " + multiplicand);
            System.out.println("Multiplier: " + multiplier + "\n");
            System.out.println(String.format("%"+25+"s", multiplier));
            System.out.print("x");
            System.out.println(String.format("%"+24+"s", multiplicand));
            System.out.println("_____________________________");

            multiply(multiplier, multiplicand);
        }
    }

    // Pseudo-random number generator
    public static BigInteger pseudoRandom(BigInteger lowerBound, BigInteger upperBound) {
        long currentTimeMillis = System.currentTimeMillis();
        BigInteger range = upperBound.subtract(lowerBound).add(BigInteger.ONE);
        BigInteger result = BigInteger.valueOf(currentTimeMillis).mod(range).add(lowerBound);
        return result;
    }

    public static void multiply(BigInteger multiplier, BigInteger multiplicand) {
        // Result container for final assembly
        BigInteger result = BigInteger.ZERO;
        int operationCount = 0; // Initialize operation counter

        String multiplierStr = multiplier.toString();
        String multiplicandStr = multiplicand.toString();
        operationCount += 4; // For parsing multiplier and multiplicand

        int partialCount = 0;
        int carrierCount = 1;
        operationCount += 2; // For the initialization of partialCount and carrierCount

        /*
        For loop:
         * Initialization = 3
         * Condition check = n+1
         * Decrement = 2n
         */
        operationCount += 3; // For initialization of i
        for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
            int digitM = Character.digit(multiplicandStr.charAt(i), 10);
            operationCount += 3; // For character access, C

            StringBuilder partials = new StringBuilder();
            StringBuilder carriers = new StringBuilder();
            operationCount += 2; // For two StringBuilder initializations

            /*
             * For loop:
             * Initialization = 3
             * Condition check = n+1
             * Decrement = 2n
             */
            operationCount += 3; // For initialization of j
            for (int j = multiplierStr.length() - 1; j >= 0; j--) {
                int digitN = Character.digit(multiplierStr.charAt(j), 10);
                operationCount += 3; // For character access, digit conversion, and assignment

                BigInteger product = BigInteger.valueOf(digitM).multiply(BigInteger.valueOf(digitN));
                operationCount += 2; // For multiplication and assignment

                partials.append(product.mod(BigInteger.TEN));
                carriers.append(product.divide(BigInteger.TEN));
                operationCount += 4; // For two append operations and arithmetic operations

                operationCount++; // For the loop condition check
                operationCount += 2; // For the decrement j--
            }
            operationCount++; // For the final condition check in the inner loop

            // Print partial results
            System.out.print(String.format("%"+25+"s", partials.reverse().toString() + " ".repeat(partialCount)));
            System.out.println("\t"+"partial products for (=" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
            partialCount++;

            // Print carrier results
            if (i == 0) { // For the last carrier, print the plus sign
                System.out.print("+");
                System.out.print(String.format("%"+24+"s", carriers.reverse().toString() + " ".repeat(carrierCount)));
                System.out.println("\t"+"carriers for (" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
            } else {
                System.out.print(String.format("%"+25+"s", carriers.reverse().toString() + " ".repeat(carrierCount)));
                System.out.println("\t"+"carriers for (" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
            }
            carrierCount++;

            // Accumulate the final result considering both partials and carries
            BigInteger partialValue = new BigInteger(partials.toString()).multiply(BigInteger.TEN.pow(multiplicandStr.length() - 1 - i));
            BigInteger carrierValue = new BigInteger(carriers.toString()).multiply(BigInteger.TEN.pow(multiplicandStr.length() - i));
            result = result.add(partialValue).add(carrierValue);
            operationCount += 18; // For partialValue and carrierValue calculation
            operationCount += 3; // For the addition and assignment

            operationCount++; // For the loop condition check
            operationCount += 2; // For the decrement i--
        }
        operationCount++; // For the final condition check in the outer loop

        System.out.println("_____________________________");
        System.out.println(String.format("%"+25+"s", result));
        System.out.println("=============================\n");
        System.out.println("Total primitive operations: " + operationCount + "\n");
    }
}
