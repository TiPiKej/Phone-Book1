import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        scanner.close();

        System.out.println(binarySearch(arr, 0, arr.length - 1));
    }

    public static boolean binarySearch(int[] array, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid == array[mid]) {
                return true;
            } else if (mid < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}