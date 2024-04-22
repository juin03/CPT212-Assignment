public class WithoutStackLibrary {
    public static void main(String[] args) {
        for (int i = 1; i <= 10000; i++) {
            int lowerBound = (int) Math.pow(10, i - 1);
            int upperBound = (int) Math.pow(10, i) - 1;

            int multiplier = nextInt(lowerBound, upperBound);
            int multiplicand = nextInt(lowerBound, upperBound);

            System.out.println("==============================================================");
            System.out.println(i + " digits");
            System.out.println("Multiplier: " + multiplier);
            System.out.println("Multiplicand: " + multiplicand);
            multiply(multiplier, multiplicand);
        }
    }

    public static int nextInt(int min, int max) {
        return (int) (Math.abs(System.nanoTime()) % (max - min + 1)) + min;
    }

    public static void multiply(int multiplier, int multiplicand) {
        String multiplierStr = Integer.toString(multiplier);
        String multiplicandStr = Integer.toString(multiplicand);
        MyStack<Integer> partials = new MyStack<>();
        MyStack<Integer> carriers = new MyStack<>();
        StringBuilder sb = new StringBuilder();

        long result = 0;
        int operationCount = 0;

        for (int i = multiplicandStr.length() - 1; i >= 0; i--) {
            int digitM = Character.digit(multiplicandStr.charAt(i), 10);
            operationCount += 2;

            for (int j = multiplierStr.length() - 1; j >= 0; j--) {
                int digitN = Character.digit(multiplierStr.charAt(j), 10);
                int product = digitM * digitN;
                operationCount += 4;

                partials.push(product % 10);
                carriers.push(product / 10);
                operationCount += 2;
            }
            operationCount++;

            System.out.println("\nPartial products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
            while (!partials.isEmpty()) {
                sb.append(partials.pop());
                operationCount++;
            }
            System.out.println("Partial Result: " + sb.toString());
            result += Long.parseLong(sb.toString()) * Math.pow(10, multiplicandStr.length() - 1 - i);
            operationCount += 2;
            sb.setLength(0);

            System.out.println("\nCarrier products for " + multiplierStr + " x " + multiplicandStr.charAt(i));
            while (!carriers.isEmpty()) {
                sb.append(carriers.pop());
                operationCount++;
            }
            System.out.println("Carrier Result: " + sb.toString());
            result += Long.parseLong(sb.toString()) * Math.pow(10, multiplicandStr.length() - i);
            operationCount += 2;
            sb.setLength(0);
        }
        operationCount++;

        System.out.println("\nMultiplication result of " + multiplier + " x " + multiplicand + " = " + result);
        System.out.println("Total primitive operations: " + operationCount + "\n");
    }

    // Simple stack implementation
    private static class MyStack<T> {
        private java.util.ArrayList<T> list = new java.util.ArrayList<>();

        public void push(T value) {
            list.add(value);
        }

        public T pop() {
            return list.remove(list.size() - 1);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }
}
