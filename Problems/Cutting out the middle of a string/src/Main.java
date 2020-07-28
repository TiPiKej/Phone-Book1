import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        scanner.close();

        StringBuilder output = new StringBuilder();

        boolean isEven = input.length() % 2 == 0;
        int middleIndex = input.length() / 2;

        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);

            if (isEven && (i == middleIndex || i == middleIndex - 1)) continue;
            else if (!isEven && i == middleIndex) continue;

            output.append(temp);
        }

        System.out.println(output);
    }
}