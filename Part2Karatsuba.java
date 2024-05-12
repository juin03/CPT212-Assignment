import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Part2Karatsuba {

    public static BigInteger mult(BigInteger x, BigInteger y, PrimitiveOperationsCounter counter, int times) {
        // Base case for recursion
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            counter.increment(6);  // Counting multiplication as a primitive operation
            return x.multiply(y);
        }

        int noOneLength = numLength(x, counter);
        counter.increment(2);
        int noTwoLength = numLength(y, counter);
        counter.increment(2);
        int maxNumLength = Math.max(noOneLength, noTwoLength);
        counter.increment(2);

        int halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        counter.increment(4);
        BigInteger maxNumLengthTen = BigInteger.TEN.pow(halfMaxNumLength);
        counter.increment(2);

        // Counting division as a primitive operation
        BigInteger a = x.divide(maxNumLengthTen);
        counter.increment(2);

        // Counting remainder as a primitive operation
        BigInteger b = x.remainder(maxNumLengthTen);
        counter.increment(2);

        // Counting division as a primitive operation
        BigInteger c = y.divide(maxNumLengthTen);
        counter.increment(2);

        // Counting remainder as a primitive operation
        BigInteger d = y.remainder(maxNumLengthTen);
        counter.increment(2);

        // Recursive calls
        BigInteger z0 = mult(a, c, counter, times);
        counter.increment(2);

        BigInteger z1 = mult(a.add(b), c.add(d), counter, times);
        counter.increment(4);  

        BigInteger z2 = mult(b, d, counter, times);
        counter.increment(2);

        // Counting addition and subtraction as primitive operations
        BigInteger result = z0.multiply(BigInteger.TEN.pow(2 * halfMaxNumLength))
                .add(z1.subtract(z0).subtract(z2).multiply(BigInteger.TEN.pow(halfMaxNumLength))
                .add(z2));
        counter.increment(10);

        counter.increment(1);
        return result;
    }

    // To calculate length of the number
    public static int numLength(BigInteger x, PrimitiveOperationsCounter counter) {
        int noLen = 0;
        counter.increment(1);

        while (x.compareTo(BigInteger.ZERO) > 0) {
            counter.increment(1);
            noLen++;
            counter.increment(2);
            x = x.divide(BigInteger.TEN);
            counter.increment(2);
        }
        counter.increment(2);
        return noLen;
    }

    public static void main(String[] args) {
        
        String filePath = "Dataset.csv";

        List<String> lines = new ArrayList<>();
         try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); 
            lines.add("n,Multiplicand,Multiplier,Product,Result(Part 1),Result(Part 2),PrimitiveOperations(Part 1),PrimitiveOperations(Part 2)");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                String[] data = line.split(",");
                if (data.length < 6) {
                    // Skip rows that don't have enough columns
                    System.out.println("Skipping row due to insufficient columns: " + line);
                    continue;
                }

                try {
                    int n = Integer.parseInt(data[0]);
                    BigInteger multiplicand = new BigInteger(data[1]);
                    BigInteger multiplier = new BigInteger(data[2]);
                    String productPart1 = data[3]; // Keep the original Product
                    String resultPart1 = data[4];  // Keep the original Result(Part 1)
                    String primitiveOpsPart1 = data[5]; // Keep the original PrimitiveOperations(Part 1)

                    // Initialize the primitive operation counter for Karatsuba
                    PrimitiveOperationsCounter counter = new PrimitiveOperationsCounter();

                    // Compute the product using Karatsuba's algorithm
                    BigInteger productPart2 = mult(multiplicand, multiplier, counter, n);

                    // Add the new results to the existing data
                    lines.add(String.format("%s,%s,%s,%s,%s,%s,%s,%d",
                            data[0], data[1], data[2], productPart1, resultPart1, productPart2, primitiveOpsPart1, counter.getCount()));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Handle invalid data by printing a warning message
                    System.out.println("Skipping row due to invalid data: " + line);
                }
            }
        } catch (FileNotFoundException e) {
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

    // Utility class to count primitive operations
    static class PrimitiveOperationsCounter {
        private int count;
    
        public void increment(int times) {
            this.count += times;
        }
    
        public int getCount() {
            return this.count;
        }
    }
}