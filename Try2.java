import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class Try2 {
  
  public static void main(String[] args) {
    try (FileWriter writer = new FileWriter("CPT212-Assignment\\operation_counts.csv")) {
      writer.write("Digits,Primitive Operations\n");  // Header for CSV file

      for (int i = 1; i <= 50; i += 1) {
        BigInteger multiplicant = generateRandomBigInteger(i);
        BigInteger multiplier = generateRandomBigInteger(i);

        System.out.println("Multiplicant: " + multiplicant);
        System.out.println("Multiplier  : " + multiplier);

        PrimitiveOperationCounter counter = new PrimitiveOperationCounter();
        BigInteger result = customMultiply(multiplicant, multiplier, counter);

        System.out.println("----------------------------------------------");
        System.out.println(" Total:" + String.format("%19d", result));
        System.out.println("==============================================\n");

        writer.write(i + "," + counter.count + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static BigInteger generateRandomBigInteger(int digits) {
    StringBuilder number = new StringBuilder();
    if (digits > 0) {
      int firstDigit = 1 + (int) (Math.random() * 9);
      number.append(firstDigit);
      for (int i = 1; i < digits; i++) {
        int digit = (int) (Math.random() * 10);
        number.append(digit);
      }
    } else {
      return BigInteger.ZERO;
    }
    return new BigInteger(number.toString());
  }

  public static BigInteger customMultiply(BigInteger multiplicant, BigInteger multiplier, PrimitiveOperationCounter counter) {
    String strMultiplicant = multiplicant.toString();
    counter.count += 2;
    String strMultiplier = multiplier.toString();
    counter.count += 2;
    int[] result = new int[strMultiplicant.length() + strMultiplier.length()];
    counter.count += 5;

    for (int j = strMultiplier.length() - 1; j >= 0; j--) {
      counter.count += 3;  // Count decrement, comparison, and assignment as primitive operations (i)
      System.out.println("\nCurrent operation: " + strMultiplicant + " X " + strMultiplier.charAt(j) + "\n");

      counter.count += 3;  // Count initialization, comparison, and assignment as primitive operations (j)
      for (int i = strMultiplicant.length() - 1; i >= 0; i--) {
        int digitMultiplier = strMultiplier.charAt(j) - '0';
        counter.count += 3;
        int digitMultiplicant = strMultiplicant.charAt(i) - '0';
        counter.count += 3;
        int product = digitMultiplicant * digitMultiplier;
        counter.count += 2; // Count multiplication and addition as primitive operations

        int tempSum = product + result[i + j + 1];
        counter.count += 5; 

        result[i + j + 1] = tempSum % 10;
        counter.count += 5; 
        result[i + j] += tempSum / 10;
        counter.count += 7; 

        System.out.println("Partial Product= " + (product % 10) + " Carriers= " + (product / 10) +
          " (Multiplicant digit: " + digitMultiplicant + ", Multiplier digit: " + digitMultiplier + ")");
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int num : result) {
      if (!(sb.length() == 0 && num == 0)) {
        sb.append(num);
      }
    }

    return new BigInteger(sb.toString());
  }

  // Helper class to use as a counter
  static class PrimitiveOperationCounter {
    public long count = 0;
  }
}
