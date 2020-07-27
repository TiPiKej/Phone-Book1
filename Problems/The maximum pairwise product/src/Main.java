import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        scanner.close();

        int maxPairwise = 0;

        for (int i = 0, temp; i < N - 1; i++) {
            temp = arr[i] * arr[i+1];

            if (temp > maxPairwise) {
                maxPairwise = temp;
            }
        }

        System.out.println(maxPairwise);
    }
}