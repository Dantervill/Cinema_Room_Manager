public class Test {


    public static void subtractTwoNumbers(long a, long b) {
        long c = a - b;
        System.out.println(c);
    }


    public static void sumTwoNumbers(long a, long b) {
        long c = a + b;
        System.out.println(c);
    }


    public static void divideTwoNumbers(long a, long b) {
        if (b == 0) {
            System.out.println("Division by 0!");
        } else {
            long c = a / b;
            System.out.println(c);
        }
    }


    public static void multiplyTwoNumbers(long a, long b) {
        long c = a * b;
        System.out.println(c);
    }
}
