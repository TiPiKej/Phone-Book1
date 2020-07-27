import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String sentence = scanner.nextLine();
        final String findingWord = scanner.nextLine();

        scanner.close();

        int counter = 0;
        for (int i = 0; i < sentence.length(); i++) {
            boolean isThere = true;

            if (findingWord.length() == 0) break;

            for (int j = 0; j < findingWord.length(); j++) {
                if (sentence.charAt(i + j) != findingWord.charAt(j)) {
                    isThere = false;
                    break;
                }
            }

            if (isThere) {
                counter++;
                i += findingWord.length() - 1;
            }
        }

        System.out.println(counter);
    }
}