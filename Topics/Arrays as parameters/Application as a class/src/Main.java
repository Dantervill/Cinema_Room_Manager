import java.lang.reflect.Method;

class Application {

   String name;

    public static void main(String[] args) {
        method(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 });
    }

    void run(String[] args) {
        String[] strArray = new String[] {name};
        System.out.println(strArray[0]);

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

    }

    public static int method(int[] array, int... vararg) { return 1; }
}