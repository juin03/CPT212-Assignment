import java.math.BigInteger;

public class Try2 {
  
  public static void main(String[] args) {
    for (int i = 5; i <= 10; i += 5) {
      BigInteger multiplicant = generateRandomBigInteger(i);
      BigInteger multiplier = generateRandomBigInteger(i);

      System.out.println("Multiplicant: " + multiplicant);
      System.out.println("Multiplier  : " + multiplier);

      BigInteger result = customMultiply(multiplicant, multiplier);

      System.out.println("----------------------------------------------");
      System.out.println(" Total:" + String.format("%19d", result));
      System.out.println("==============================================\n");
    }
  }

  public static BigInteger generateRandomBigInteger(int digits) {
    StringBuilder number = new StringBuilder();
    if (digits > 0) {
      int firstDigit = 1 + (int) (Math.random() * 9); // Generate a number between 1 and 9
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

  public static BigInteger customMultiply(BigInteger multiplicant, BigInteger multiplier) {
    String strMultiplicant = multiplicant.toString();
    String strMultiplier = multiplier.toString();
    int[] result = new int[strMultiplicant.length() + strMultiplier.length()];

    for (int j = strMultiplier.length() - 1; j >= 0; j--) {
      // Output the current operation before starting the loop
      System.out.println("\nCurrent operation: " + strMultiplicant + " X " + strMultiplier.charAt(j) +"\n");

      for (int i = strMultiplicant.length() - 1; i >= 0; i--) {
        int digitMultiplier = strMultiplier.charAt(j) - '0';
        int digitMultiplicant = strMultiplicant.charAt(i) - '0';

        int product = digitMultiplicant * digitMultiplier;
        int tempSum = product + result[i + j + 1];
        result[i + j + 1] = tempSum % 10;
        result[i + j] += tempSum / 10;

        System.out.println("Partial Product= " + (product % 10) + " Carriers= " + (product / 10) +
          " (Multiplicant digit: " + digitMultiplicant + ", Multiplier digit: " + digitMultiplier + ")");
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int num : result) {
      if (!(sb.length() == 0 && num == 0)) { // Skip leading zeros
        sb.append(num);
      }
    }

    return new BigInteger(sb.toString());
  }
}
