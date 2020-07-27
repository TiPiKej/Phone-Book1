import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();

        int[] sortedArr = new int[N];
        for (int i = 0; i < N; i++) {
            sortedArr[i] = scanner.nextInt();
        }

        final int K = scanner.nextInt();

        int[] numbers = new int[K];
        for (int i = 0; i < K; i++) {
            numbers[i] = scanner.nextInt();
        }

        scanner.close();

        for (int nbr : numbers) {
            System.out.printf("%d ", binarySearch(sortedArr, nbr, 0, sortedArr.length - 1));
        }
    }

    public static int binarySearch(int[] array, int elem, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (elem == array[mid]) {
                return mid + 1;
            } else if (elem < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}