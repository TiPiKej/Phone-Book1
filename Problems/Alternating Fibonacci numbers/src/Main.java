import java.util.Scanner;

public class Main {

    public static long fib(long n){
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n % 2 == 0 ? -fib(n) : fib(n));
    }
}