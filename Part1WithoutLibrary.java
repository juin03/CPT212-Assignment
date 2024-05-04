public class Part1WithoutLibrary {
  public static void main(String[] args) {
   
      //  Loop through 1 to 10 digits
      for (int n = 1; n <= 10; n++) {
          int lowerBound = (int) Math.pow(10, n - 1);
          int upperBound = (int) Math.pow(10, n) - 1;

          int multiplier = pseudoRandom(lowerBound, upperBound);
          int multiplicand = pseudoRandom(lowerBound, upperBound);

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
  public static int pseudoRandom(int lowerBound, int upperBound) {
      long currentTimeMillis = System.currentTimeMillis();
      int range = upperBound - lowerBound + 1;
      return (int) ((currentTimeMillis % range) + lowerBound);
  }

  public static void multiply(int multiplier, int multiplicand) {
      // Result container for final assembly
      long result = 0;
      int operationCount = 0; // Initialize operation counter

      String multiplierStr = Integer.toString(multiplier);
      String multiplicandStr = Integer.toString(multiplicand);
      operationCount += 4; // For parsing multiplier and multiplicand

      int partialCount = 0;
      int carrierCount = 1;
      operationCount+=2; // For the initialization of partialCount and carrierCount

      /*
      For loop:
       * Initialization = 3
       * Condition check = n+1
       * Decrement = 2n
       */
      operationCount+=3; // For initialization of i
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
              operationCount += 3; // For character access, digit conversion and assignment
              int product = digitM * digitN;
              operationCount += 2; // For multiplication and assignment

              partials.append(product % 10);
              carriers.append(product / 10);
              operationCount += 4; // For two append operations and arithmetic operations

              operationCount++; // For the loop condition check
              operationCount+=2; // For the decrement j--
          }
          operationCount++; // For the final condition check in the inner loop

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
          long partialValue = Long.parseLong(partials.toString()) * (long) Math.pow(10, multiplicandStr.length() - 1 - i);
          long carrierValue = Long.parseLong(carriers.toString()) * (long) Math.pow(10, multiplicandStr.length() - i);
          result += partialValue + carrierValue;
          operationCount += 18; // For partialValue and carrierValue calculation
          operationCount += 3; // For the addition and assignment

          operationCount++; // For the loop condition check
          operationCount+=2; // For the decrement i--
      }
      operationCount++; // For the final condition check in the outer loop

      System.out.println("_____________________________");
      System.out.println(String.format("%"+25+"s", result));
      System.out.println("=============================\n");
      System.out.println("Total primitive operations: " + operationCount + "\n");
  }
}
