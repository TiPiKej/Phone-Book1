import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        scanner.close();

        int[] arr = new int[N];
        Arrays.fill(arr, 1);

        decomposition(arr, N, 0, N - 1);
    }

    public static void decomposition(int[] arr, int N, int start, int end) {
//        if (start == 0) {
            printArr(arr);
            System.out.println();
//        }

        for (int i = 2; i <= N; i++) {
            int sum = 0;
            int[] newArr = new int[N - 1];

            for (int j = 0; j < start; j++) {
                newArr[j] = arr[j];
//                sum += arr[j];
            }

            newArr[start] = i;
            sum += i;

            for (int j = start + 1; sum < N; j++) {
                newArr[j] = arr[j + 1];
                sum += arr[j + 1];
            }

            if (start + 1 < end) decomposition(newArr, N - i, start + 1, end);

//            System.out.println();
//            printArr(newArr);
        }
    }

    public static void printArr(int[] arr) {
        for (int nbr : arr) {
            if (nbr == 0) continue;
            System.out.printf("%d ", nbr);
        }
    }
}