import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class BigIntegerCSVGenerator {

    public static void main(String[] args) {
        String filePath = "CPT212-Assignment\\Karatsuba.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header line
            writer.append("n,Multiplicand,Multiplier\n");

            // Loop from n-1 to n = 999
            for (int n = 1; n < 100; n++) {
                BigInteger multiplicand = generateBigIntegerWithNDigits(n);
                BigInteger multiplier = generateBigIntegerWithNDigits(n);

                // Write data to CSV
                writer.append(String.format("%d,%s,%s\n", n, multiplicand.toString(), multiplier.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to generate a random BigInteger with exactly n digits
    private static BigInteger generateBigIntegerWithNDigits(int n) {
        if (n < 1) {
            return BigInteger.ZERO;
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // First digit should not be zero
        sb.append(random.nextInt(9) + 1);

        for (int i = 1; i < n; i++) {
            sb.append(random.nextInt(10));
        }

        return new BigInteger(sb.toString());
    }
}