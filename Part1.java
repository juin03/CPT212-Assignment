
import java.util.Stack;
import java.util.Random;

public class Part1 {
    public static void main(String[] args) {
        // Clear the console
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 1; i <= 10; i++) {
            int n = i; // number of digits, but the weakness is that it can only handle up to 10 digits
            Random rand = new Random();

            int lowerBound = (int) Math.pow(10, n - 1);
            int upperBound = (int) Math.pow(10, n) - 1;

            int multiplier = rand.nextInt(upperBound - lowerBound) + lowerBound;
            int multiplicand = rand.nextInt(upperBound - lowerBound) + lowerBound;
            System.out.println("==============================================================");
            System.out.println(n + " digits");
            System.out.println("Multiplier: " + multiplier);
            System.out.println("Multiplicand: " + multiplicand);
            System.out.println(
                    "\nMultiplication result of " + multiplier + " x " + multiplicand + " = "
                            + multiply(multiplier, multiplicand) + "\n");
        }

    }

    public static long multiply(int multiplier, int multiplicand) {
        long result = 0;
        StringBuilder sb = new StringBuilder();
        String partial = "";
        String carrier = "";
        Stack<Integer> stack_partial = new Stack();
        Stack<Integer> stack_carrier = new Stack();

        // Convert the numbers to strings
        String multiplicand_str = String.valueOf(multiplicand);
        String multiplier_str = String.valueOf(multiplier);

        int counter = 0;
        // Perform multiplication digit by digit
        for (int i = multiplicand_str.length() - 1; i >= 0; i--) {
            for (int j = multiplier_str.length() - 1; j >= 0; j--) {

                int product = (multiplicand_str.charAt(i) - '0') * (multiplier_str.charAt(j) - '0');

                if (product >= 10) {
                    stack_partial.push(product % 10);
                    stack_carrier.push(product / 10);
                } else {
                    stack_partial.push(product);
                    stack_carrier.push(0);
                }

            }
            System.out.println("\nPartial products for " + multiplier_str + " x " + multiplicand_str.charAt(i));
            // Pop stack_partial and append to a string
            while (!stack_partial.isEmpty()) {
                partial = String.valueOf(stack_partial.pop());
                sb.append(partial);
            }
            System.out.println("Partial Result: " + sb.toString());
            for (int k = 0; k < counter; k++) {
                sb.append("0");
            }
            result += Long.valueOf(sb.toString());

            sb.setLength(0); // Clear sb after printing

            System.out.println("\nCarrier products for " + multiplier_str + " x " + multiplicand_str.charAt(i));

            // Pop stack_carrier and append to a string
            while (!stack_carrier.isEmpty()) {
                carrier = String.valueOf(stack_carrier.pop());
                sb.append(carrier);
            }
            System.out.println("Carrier Result: " + sb.toString());
            for (int k = 0; k < counter + 1; k++) {
                sb.append("0");
            }
            result += Long.valueOf(sb.toString());

            sb.setLength(0); // Clear sb after printing

            // Clear the queues
            stack_partial.clear();
            stack_carrier.clear();
            counter++;
        }

        // Return the final result as a string
        return result;
    }
}

// /// Java Program to Implement Karatsuba Algorithm
 
// // Importing Random class from java.util packahge
// import java.util.Random;
 
// // MAin class 
// class Karatsuba {
 
//     // Main driver method 
//     public static long mult(long x, long y) {
 
//         // Checking only if input is within range  
//         if (x < 10 && y < 10) {
           
//             // Multiplying the inputs entered 
//             return x * y;
//         }
      
//         // Declaring variables in order to  
//         // Find length of both integer
//         // numbers x and y
//         int noOneLength = numLength(x);
//         int noTwoLength = numLength(y);
 
//         // Finding maximum length from both numbers
//         // using math library max function
//         int maxNumLength
//             = Math.max(noOneLength, noTwoLength);
 
//         // Rounding up the divided Max length
//         Integer halfMaxNumLength
//             = (maxNumLength / 2) + (maxNumLength % 2);
 
//         // Multiplier
//         long maxNumLengthTen
//             = (long)Math.pow(10, halfMaxNumLength);
 
//         // Compute the expressions
//         long a = x / maxNumLengthTen;
//         long b = x % maxNumLengthTen;
//         long c = y / maxNumLengthTen;
//         long d = y % maxNumLengthTen;
 
 
//         // Compute all mutilpying variables
//         // needed to get the multiplication    
//         long z0 = mult(a, c);
//         long z1 = mult(a + b, c + d);
//         long z2 = mult(b, d);
 
//         long ans = (z0 * (long)Math.pow(10, halfMaxNumLength * 2) + 
//                    ((z1 - z0 - z2) * (long)Math.pow(10, halfMaxNumLength) + z2));
 
//         return ans;
 
//     }
   
//       // Method 1
//     // To calculate length of the number
//     public static int numLength(long n)
//     {
//         int noLen = 0;
//         while (n > 0) {
//             noLen++;
//             n /= 10;
//         }
 
//         // Returning length of number n
//         return noLen;
//     }
 
//      // Method 2
//     // Main driver function
//     public static void main(String[] args)
//     {
//         // Showcasing karatsuba multiplication
         
//       // Case 1: Big integer lengths
//         long expectedProduct = 1234 * 5678;
//         long actualProduct = mult(1234, 5678);
 
//         // Printing the expected and corresponding actual product 
//         System.out.println("Expected 1 : " + expectedProduct);
//         System.out.println("Actual 1 : " + actualProduct + "\n\n");
       
//         assert(expectedProduct == actualProduct);
 
 
//         expectedProduct = 102 * 313;
//         actualProduct = mult(102, 313);
 
//         System.out.println("Expected 2 : " + expectedProduct);
//         System.out.println("Actual 2 : " + actualProduct + "\n\n");
         
//       assert(expectedProduct == actualProduct);
 
//         expectedProduct = 1345 * 63456;
//         actualProduct = mult(1345, 63456);
 
//         System.out.println("Expected 3 : " + expectedProduct);
//         System.out.println("Actual 3 : " + actualProduct + "\n\n");
         
//       assert(expectedProduct == actualProduct);        
     
//         Integer x = null;
//         Integer y = null;
//         Integer MAX_VALUE = 10000;
 
//         // Boe creating an object of random class
//         // inside main() method 
//         Random r = new Random();
 
//         for (int i = 0; i < MAX_VALUE; i++) {
//             x = (int) r.nextInt(MAX_VALUE);
//             y = (int) r.nextInt(MAX_VALUE);
 
//             expectedProduct = x * y;
 
//             if (i == 9999) {
               
//               // Prove assertions catch the bad stuff.
//                 expectedProduct = 1;    
//             }
//             actualProduct = mult(x, y);
 
//              // Again printing the expected and 
//             // corresponding actual product 
//             System.out.println("Expected: " + expectedProduct);
//             System.out.println("Actual: " + actualProduct + "\n\n");
 
//             assert(expectedProduct == actualProduct);        
//         }
//     }
// }