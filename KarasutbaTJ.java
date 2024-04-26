import java.util.Random;

class KaratsubaTJ {

    // Single counter for all operations
    static long operationCount = 0;

    // Method to multiply numbers using Karatsuba algorithm
    public static long mult(long x, long y) {
        // Increment operation count for entering this method
        operationCount++; 

        // Base case for multiplication
        if (x < 10 && y < 10) {
            operationCount++; // Counting the multiplication
            return x * y;
        }

        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        int maxNumLength = Math.max(noOneLength, noTwoLength);
        Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);

        long maxNumLengthTen = (long)Math.pow(10, halfMaxNumLength);
        operationCount++; // Counting the power operation

        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;
        operationCount += 4; // Counting divisions and mod operations

        // Recursive multiplications
        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);
        operationCount += 2; // Counting additions

        long result = z0 * (long)Math.pow(10, 2 * halfMaxNumLength) +
                      (z1 - z0 - z2) * (long)Math.pow(10, halfMaxNumLength) + z2;
        operationCount += 5; // Counting additions and multiplications in the result calculation

        return result;
    }

    // Method to calculate the length of the number
    public static int numLength(long n) {
        int noLen = 0;
        while (n > 0) {
            noLen++;
            n /= 10;
            operationCount++; // Counting divisions and assignments
        }
        return noLen;
    }

    public static void main(String[] args) {
      Random r = new Random();
      for (int n = 1; n <= 10; n++) { // Test for 1 to 10 digit numbers
          long lowerBound = (long) Math.pow(10, n - 1);
          long upperBound = (long) Math.pow(10, n) - 1;

          long x = r.nextLong(upperBound - lowerBound + 1) + lowerBound;
          long y = r.nextLong(upperBound - lowerBound + 1) + lowerBound;
          operationCount = 0; // Reset counter for each digit test
          long product = mult(x, y); // Assumes mult is defined elsewhere

          System.out.println("Digits: " + n);
          System.out.println("x = " + x + ", y = " + y);
          System.out.println("Product = " + product);
          System.out.println("Total operations: " + operationCount + "\n");
        }
    }
}
