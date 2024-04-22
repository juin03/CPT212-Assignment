public class Part1WithoutLibrary {
  public static void main(String[] args) {
      int operationCount = 0; // Initialize operation counter

      for (int i = 1; i <= 10; i++) {
          operationCount++; // For the loop condition check

          int lowerBound = (int) Math.pow(10, i - 1);
          int upperBound = (int) Math.pow(10, i) - 1;
          operationCount += 4; // For two power calculations and two subtractions

          int multiplier = pseudoRandom(lowerBound, upperBound);
          int multiplicand = pseudoRandom(lowerBound, upperBound);
          operationCount += 2; // For two method calls

          System.out.println("==============================================================");
          System.out.println(i + " digits");
          System.out.println("Multiplier: " + multiplier);
          System.out.println("Multiplicand: " + multiplicand);
          operationCount += 4; // For four print operations

          multiply(multiplier, multiplicand);
          operationCount++; // For calling the multiply method

          operationCount++; // For the loop increment (i++)
      }
      operationCount++; // For the final condition check in the loop

      System.out.println("Total primitive operations: " + operationCount);
  }

  public static int pseudoRandom(int lowerBound, int upperBound) {
      long currentTimeMillis = System.currentTimeMillis();
      int range = upperBound - lowerBound + 1;
      return (int) ((currentTimeMillis % range) + lowerBound);
  }

  public static void multiply(int multiplier, int multiplicand) {
      String multiplierStr = Integer.toString(multiplier);
      String multiplicandStr = Integer.toString(multiplicand);

      // Result container for final assembly
      long result = 0;
      int operationCount = 0; // Initialize operation counter

      for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
          int digitM = Character.digit(multiplicandStr.charAt(i), 10);
          operationCount += 2; // For character access and digit conversion

          StringBuilder partials = new StringBuilder();
          StringBuilder carriers = new StringBuilder();

          for (int j = multiplierStr.length() - 1; j >= 0; j--) {
              int digitN = Character.digit(multiplierStr.charAt(j), 10);
              int product = digitM * digitN;
              operationCount += 4; // For character access, digit conversion, multiplication, and condition check

              partials.append(product % 10);
              carriers.append(product / 10);
              operationCount += 2; // For two append operations

              operationCount++; // For the loop increment (j--)
          }
          operationCount++; // For the final condition check in the inner loop

          // Print partial results
          System.out.println("\nPartial products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
          System.out.println("Partial Result: " + partials.reverse().toString());
          result += Long.parseLong(partials.toString()) * Math.pow(10, multiplicandStr.length() - 1 - i);
          operationCount += 3; // For parsing, multiplication, and power calculation

          // Print carrier results
          System.out.println("\nCarrier products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
          System.out.println("Carrier Result: " + carriers.reverse().toString());
          result += Long.parseLong(carriers.toString()) * Math.pow(10, multiplicandStr.length() - i);
          operationCount += 3; // For parsing, multiplication, and power calculation

          operationCount++; // For the loop increment (i--)
      }
      operationCount++; // For the final condition check in the outer loop

      System.out.println("\nMultiplication result of " + multiplier + " x " + multiplicand + " = " + result);
      System.out.println("Total primitive operations: " + operationCount + "\n");
  }
}
