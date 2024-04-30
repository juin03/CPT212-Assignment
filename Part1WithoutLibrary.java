public class Part1WithoutLibrary {
  public static void main(String[] args) {
    /*
     * For loop primitive operations count:
     * Initialization = 1
     * Condition check = n+1 [11]
     * Increment = 2n [20]
     * Function call = n [10]
     */
      for (int n = 2; n <= 5; n++) {

          int lowerBound = (int) Math.pow(10, n - 1);
          int upperBound = (int) Math.pow(10, n) - 1;

          int multiplier = pseudoRandom(lowerBound, upperBound);
          int multiplicand = pseudoRandom(lowerBound, upperBound);

          System.out.println("==============================================================");
          System.out.println(n + " digits");
          System.out.println(String.format("%"+15+"s", multiplier));
          System.out.print("x");
          System.out.println(String.format("%"+14+"s", multiplicand));
          System.out.println("_________________");

          multiply(multiplier, multiplicand);
      }
  }

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
      operationCount += 2; // For parsing multiplier and multiplicand

      /*
      For loop:
       * Initialization = 3
       * Condition check = n+1
       * Decrement = 2n
       * length(n) = 2, i=1, run 2 times, condition check = 3, decrement =2
       */
      int partialCount = 0;
      int carrierCount = 1;
      operationCount+=3; // For initialization of i
      for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
          int digitM = Character.digit(multiplicandStr.charAt(i), 10);
          operationCount += 3; // For character access, digit conversion and assignment

          StringBuilder partials = new StringBuilder();
          StringBuilder carriers = new StringBuilder();

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
          result += Long.parseLong(partials.toString()) * Math.pow(10, multiplicandStr.length() - 1 - i);
          operationCount += 9; // For the line above


          System.out.print(String.format("%"+15+"s",partials.reverse().toString()+ (" ".repeat(partialCount))));
          System.out.println("\t\t"+"partial products for (=" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
          partialCount++;

          // Print carrier results
          result += Long.parseLong(carriers.toString()) * Math.pow(10, multiplicandStr.length() - i);
          operationCount += 9; // For the line above

          if (i==0){
            System.out.print("+");
            System.out.print(String.format("%"+14+"s",carriers.reverse().toString()+ (" ".repeat(carrierCount))));
            System.out.println("\t\t"+"carriers for (" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
          }else{
            System.out.print(String.format("%"+15+"s",carriers.reverse().toString()+ (" ".repeat(carrierCount))));
            System.out.println("\t\t"+"carriers for (" + multiplicand + " x " + multiplicandStr.charAt(i) + ")");
          }
          carrierCount++;


          operationCount++; // For the loop condition check
          operationCount+=2; // For the decrement i--
      }
      operationCount++; // For the final condition check in the outer loop

      System.out.println("_________________");
      System.out.println(String.format("%"+15+"s", result));
      System.out.println("=================");
      System.out.println("Total primitive operations: " + operationCount + "\n");
  }
}
