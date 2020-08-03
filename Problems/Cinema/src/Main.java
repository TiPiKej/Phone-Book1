import java.util.Scanner;

class Main {
    public static void main(String[] args) {
//        catch vars
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        final int M = scanner.nextInt();

        int[][] seats = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                seats[i][j] = scanner.nextInt();
            }
        }

        final int K = scanner.nextInt();

        scanner.close();

//        finding result
        int res;

        for (res = 1; res <= seats.length; res++) {
            int possibleSeats = 0;
            for (int seat : seats[res - 1]) {
                if (possibleSeats == K) {
                    break;
                }

                if (seat == 1) {
                    possibleSeats = 0;
                } else {
                    possibleSeats++;
                }
            }

            if (possibleSeats == K) break;
            if (res == seats.length) {
                res = 0;
                break;
            }
        }

        System.out.println(res);
    }
}