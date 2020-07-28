import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        scanner.close();

        int[] arr = new int[N];
        Arrays.fill(arr, 1);

        decomposition(arr, N);
    }

    public static void decomposition(int[] arr, int N) {
        printArr(arr);
        int[] newArr = new int[N - 1];

        int i = N;
        boolean swapped = false;
        while (i > 0) {
            if (arr[i] == arr[i - 1] && !swapped) {
                newArr[i - 1] = arr[i] + arr[i - 1];

                swapped = true;
                i--;
            }
            i--;
        }

        System.out.println();
        printArr(newArr);
    }

    public static void printArr(int[] arr) {
        for (int nbr : arr) {
            if (nbr == 0) continue;
            System.out.printf("%d ", nbr);
        }
    }
}