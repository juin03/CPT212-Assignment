import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1WithBigInteger {
    public static void main(String[] args) {
     
        //  Loop through 1 to 10 digits
        for (int z = 1; z <= 10; z++) {
        String filePath = "Karatsuba.csv";

        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Skip header line
            lines.add("n,Multiplicand,Multiplier,Result,PrimitiveOperations,PrimitiveOperations2\n"); // Add new header with Result column
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int n = Integer.parseInt(data[0]);
                BigInteger multiplicand = new BigInteger(data[1]);
                BigInteger multiplier = new BigInteger(data[2]);
                PrimitiveOperationsCounter counter = new PrimitiveOperationsCounter();
                BigInteger product = multiply(multiplicand, multiplier, counter, n);

                lines.add(String.format("%d,%s,%s,%s,%s,%d", n, multiplicand, multiplier, product, "" , counter.getCount()));

                // Output only if n<=10
             if(z<=10){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LINE BREAK~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~·~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("Number of digits, n: " + z);
                System.out.println("Multiplicand: " + multiplicand);
                System.out.println("Multiplier: " + multiplier + "\n");
                System.out.println(String.format("%"+25+"s", multiplier));
                System.out.print("x");
                System.out.println(String.format("%"+24+"s", multiplicand));
                System.out.println("_____________________________");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
             
        }

        
    }
  
    private static BigInteger multiply(BigInteger multiplicand, BigInteger multiplier,
            Part1WithBigInteger.PrimitiveOperationsCounter counter, int n) {
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }

    // Pseudo-random number generator
    public static int pseudoRandom(int lowerBound, int upperBound) {
        long currentTimeMillis = System.currentTimeMillis();
        int range = upperBound - lowerBound + 1;
        return (int) ((currentTimeMillis % range) + lowerBound);
    }
  
    public static void multiply(int multiplier, int multiplicand, PrimitiveOperationsCounter counter, int n) {
        // Result container for final assembly
        BigInteger result = BigInteger.ZERO;
  
        String multiplierStr = Integer.toString(multiplier);
        String multiplicandStr = Integer.toString(multiplicand);
        counter.increment(4); // For parsing multiplier and multiplicand
  
        int partialCount = 0;
        int carrierCount = 1;
        counter.increment(2);; // For the initialization of partialCount and carrierCount
  
        /*
        For loop:
         * Initialization = 3
         * Condition check = n+1
         * Decrement = 2n
         */
        counter.increment(3);; // For initialization of i
        for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
            int digitM = Character.digit(multiplicandStr.charAt(i), 10);
            counter.increment(3);; // For character access, digit conversion and assignment
  
            StringBuilder partials = new StringBuilder();
            StringBuilder carriers = new StringBuilder();
            counter.increment(2);; // For two StringBuilder initializations
  
            /*
             * For loop:
             * Initialization = 3
             * Condition check = n+1
             * Decrement = 2n
             */
            counter.increment(3);; // For initialization of j
            for (int j = multiplierStr.length() - 1; j >= 0; j--) {
  
                int digitN = Character.digit(multiplierStr.charAt(j), 10);
                counter.increment(3);; // For character access, digit conversion and assignment
                int product = digitM * digitN;
                counter.increment(3);; // For multiplication and assignment
  
                partials.append(product % 10);
                carriers.append(product / 10);
                counter.increment(4);; // For two append operations and arithmetic operations
  
                counter.increment(1);; // For the loop condition check
                counter.increment(2);; // For the decrement j--
            }
            counter.increment(1);; // For the final condition check in the inner loop
  
            // Print partial results
            System.out.print(String.format("%"+25+"s",partials.reverse().toString()+ (" ".repeat(partialCount))));
            System.out.println("\t"+"partial products for (=" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
            partialCount++;
  
            // Print carrier results
            if (i==0){ // For the last carrier, print the plus sign
              System.out.print("+");
              System.out.print(String.format("%"+24+"s",carriers.reverse().toString()+ (" ".repeat(carrierCount))));
              System.out.println("\t"+"carriers for (" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
            }else{
              System.out.print(String.format("%"+25+"s",carriers.reverse().toString()+ (" ".repeat(carrierCount))));
              System.out.println("\t"+"carriers for (" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
            }
            carrierCount++;
  
            // Accumulate the final result considering both partials and carries
            BigInteger partialValue = new BigInteger(partials.toString()).multiply(BigInteger.TEN.pow(multiplicandStr.length() - 1 - i));
            BigInteger carrierValue = new BigInteger(carriers.toString()).multiply(BigInteger.TEN.pow(multiplicandStr.length() - i));
            result = result.add(partialValue).add(carrierValue);
            counter.increment(18); // For partialValue and carrierValue calculation
            counter.increment(3); // For the addition and assignment
  
            counter.increment(1); // For the loop condition check
            counter.increment(2); // For the decrement i--
        }
        counter.increment(1);; // For the final condition check in the outer loop
        
        
        System.out.println("_____________________________");
        System.out.println(String.format("%"+25+"s", result));
        System.out.println("=============================\n");
        System.out.println("Total primitive operations: " + counter.getCount + "\n");
    }
    // Utility class to count primitive operations
    static class PrimitiveOperationsCounter {
        public String getCount;
        private int count;
    
        public void increment(int times) {
            this.count += times;
        }
    
        public int getCount() {
            return this.count;
        }
        
    }
  }
  