import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        double x = scanner.nextDouble();

        scanner.close();

        System.out.println(Math.pow(x, 3) + Math.pow(x, 2) + x + 1);
    }
}