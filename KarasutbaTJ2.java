class KaratsubaTJ2 {

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
        // Artificial data
        long[] xValues = {9, 99, 999, 9999, 99999,
                          999999, 9999999, 99999999, 999999999, 9999999999L};
        long[] yValues = {9, 99, 999, 9999, 99999,
                          999999, 9999999, 99999999, 999999999, 9999999999L};

        for (int i = 0; i < xValues.length; i++) {
            long x = xValues[i];
            long y = yValues[i];
            operationCount = 0; // Reset counter for each test
            long product = mult(x, y);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("x = " + x + ", y = " + y);
            System.out.println("Product = " + product);
            System.out.println("Total operations: " + operationCount + "\n");
        }
    }
}
