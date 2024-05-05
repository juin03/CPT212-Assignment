import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class KaratsubaAlgo {

    public static BigInteger mult(BigInteger x, BigInteger y, PrimitiveOperationsCounter counter, int times) {
        // Base case for recursion
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            counter.increment(5);  // Counting multiplication as a primitive operation
            return x.multiply(y);
        }

        int noOneLength = x.toString().length();
        counter.increment(3);
        int noTwoLength = y.toString().length();
        counter.increment(3);
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
                .add(z1.subtract(z0).subtract(z2).multiply(BigInteger.TEN.pow(halfMaxNumLength)))
                .add(z2);
        counter.increment(10);

        counter.increment(1);
        return result;
    }

    public static void main(String[] args) {
        String filePath = "CPT212-Assignment\\Karatsuba.csv";

        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Skip header line
            lines.add("n,Multiplicand,Multiplier,Result,PrimitiveOperations\n"); // Add new header with Result column
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int n = Integer.parseInt(data[0]);
                BigInteger multiplicand = new BigInteger(data[1]);
                BigInteger multiplier = new BigInteger(data[2]);
                PrimitiveOperationsCounter counter = new PrimitiveOperationsCounter();
                BigInteger product = mult(multiplicand, multiplier, counter, n);

                lines.add(String.format("%d,%s,%s,%s,%d", n, multiplicand, multiplier, product, counter.getCount()));
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