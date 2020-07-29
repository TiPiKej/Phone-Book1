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
        printArr(arr);
        System.out.println();

        for (int i = 2; i <= N; i++) {
            int sum = 0;
            int[] newArr = new int[N];

//            copying numbers behind actually added
            for (int j = 0; j < start; j++) {
                newArr[j] = arr[j];
                sum += arr[j];
            }

////            adding numbers
            newArr[start] = i;
            sum += i;

            for (int j = start + 1; j < N - 1; j++) {
                if (sum + arr[j + 1] <= N) {
                    newArr[j] = arr[j + 1];
                    sum += arr[j + 1];
                }
            }

            if (sum > N) return;
            if (start > 0 && newArr[start] > newArr[start - 1]) return;

            if (start < newArr.length) decomposition(newArr, N, start + 1, end);
        }
    }

    public static void printArr(int[] arr) {
        for (int nbr : arr) {
            if (nbr == 0) continue;
            System.out.printf("%d ", nbr);
        }
    }
}