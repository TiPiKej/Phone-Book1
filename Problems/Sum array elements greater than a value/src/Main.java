import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        final int n = scanner.nextInt();

        scanner.close();

        int sum = 0;

        for (int x : arr) {
            if (x > n) {
                sum += x;
            }
        }

        System.out.println(sum);
    }
}