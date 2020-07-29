import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        final int K = scanner.nextInt();

        scanner.close();

        System.out.println(K / N);
    }
}