import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int year = scanner.nextInt();
        scanner.close();

        boolean isLeap = false;

        if (year % 100 != 0 || year % 400 == 0) {
            isLeap = year % 4 == 0 ? true : false;
        }

        System.out.println(isLeap ? "Leap" : "Regular");
    }
}