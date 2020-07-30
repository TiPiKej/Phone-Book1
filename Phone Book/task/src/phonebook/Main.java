package phonebook;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String folderLocation = "C:\\Users\\Tomek\\Downloads\\";

        File findFile = new File(folderLocation + "find.txt");
        File directoryFile = new File(folderLocation + "directory.txt");
        try {
//            getting scanner to move on files
            final Scanner scannerFind = new Scanner(findFile);
            final Scanner scannerDirectory = new Scanner(directoryFile);

//            System.out.println("W złej kolejności: Nowak, Antek: " + "Nowak".compareTo("Antek"));
//            System.out.println("W dobrej kolejności: Antek, Nowak: " + "Antek".compareTo("Nowak"));

//            creating array with size of directory file and also find file
            int directoryLength = getFileLength(directoryFile);
            int findLength = getFileLength(findFile);


            String[][] directory = new String[directoryLength][2];
            String[] find = new String[findLength];

//            directory
            for (int i = 0; i < directoryLength; i++) {
                String[] tempLine = scannerDirectory.nextLine().split("\\s");
                if (tempLine.length >= 1) directory[i][0] = tempLine[0];
                if (tempLine.length == 2) directory[i][1] = tempLine[1];
                if (tempLine.length == 3) directory[i][1] = tempLine[1] + " " + tempLine[2];
            }

//            find
            for (int i = 0; scannerFind.hasNextLine(); i++) {
                find[i] = scannerFind.nextLine();
            }

//            set new object containing directory file as array
            SearchAlgorithms algorithms = new SearchAlgorithms();

            /* ******************************************************* */
            /* ******************first stage************************** */
            /* ******************************************************* */

            firstStage(algorithms, find, directory);

            /* ******************************************************* */
            /* **********************second stage********************* */
            /* ******************************************************* */

            String[][] copiedDirectory = new String[directory.length][2];
            for (int i = 0; i < directory.length; i++) {
                copiedDirectory[i][0] = directory[i][0];
                copiedDirectory[i][1] = directory[i][1];
            }

            secondStage(algorithms, find, copiedDirectory);

            /* ******************************************************* */
            /* ***********************third stage********************* */
            /* ******************************************************* */

            String[][] copiedDirectory2 = new String[directory.length][2];
            for (int i = 0; i < directory.length; i++) {
                copiedDirectory2[i][0] = directory[i][0];
                copiedDirectory2[i][1] = directory[i][1];
            }

            thirdStage(algorithms, find, copiedDirectory2);

        } catch (IOException e) {
            System.out.println("Cannot load files!");
        }
    }

    private static int getFileLength(File file) {
        int directoryLength = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                directoryLength++;
                scanner.nextLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot load files!");
        }

        return directoryLength;
    }

    private static void firstStage(SearchAlgorithms algorithms, String[] find, String[][] directory) {
//            searching by linear search
        System.out.println("Start searching...");

        int count = 0;
        long timeLinearSearching = System.currentTimeMillis();
        for (String userName : find) {
            if (algorithms.linearSearch(userName, directory) != -1) {
                count++;
            } else {
                System.out.println(userName);
            }
        }
        timeLinearSearching = System.currentTimeMillis() - timeLinearSearching;

        System.out.printf("Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.\n\n", count, Time.msToMin(timeLinearSearching), Time.msToSec(timeLinearSearching), Time.msToMs(timeLinearSearching));

    }

    private static void secondStage(SearchAlgorithms algorithms, String[] find, String[][] directory) {
//            bubble sorting
        System.out.println("Start searching (bubble sort + jump search)...");
        long timeBubbleSort = System.currentTimeMillis();
        final boolean isCompletedSorted = algorithms.bubbleSort(directory, timeBubbleSort/*, timeLinearSearching*/);
        timeBubbleSort = System.currentTimeMillis() - timeBubbleSort;

//            searching by jump search or linearSearch
        long timeJumpSearching = System.currentTimeMillis();
        int count = 0;
        for (String userName : find) {
            if (isCompletedSorted) { // jump search
                if (algorithms.jumpSearch(userName, directory) != -1) {
                    count++;
                }
            } else { // linear sorting
                if (algorithms.linearSearch(userName, directory) != -1) {
                    count++;
                }
            }
        }
        timeJumpSearching = System.currentTimeMillis() - timeJumpSearching;

//            get total time of both operations (sorting and searching)
        long totalTime = timeBubbleSort + timeJumpSearching;

        System.out.printf("Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.\n", count, Time.msToMin(totalTime), Time.msToSec(totalTime), Time.msToMs(totalTime));
        System.out.printf("Sorting time: %d min. %d sec. %d ms.", Time.msToMin(timeBubbleSort), Time.msToSec(timeBubbleSort), Time.msToMs(timeBubbleSort));
        if (!isCompletedSorted) System.out.print(" - STOPPED, moved to linear search");
        System.out.printf("\nSearching time: %d min. %d sec. %d ms.\n\n", Time.msToMin(timeJumpSearching), Time.msToSec(timeJumpSearching), Time.msToMs(timeJumpSearching));
    }

    private static void thirdStage(SearchAlgorithms algorithms, String[] find, String[][] directory) {
//            quick sort
        System.out.println("Start searching (quick sort + binary search)...");
        long timeQuickSort = System.currentTimeMillis();
        algorithms.quickSort(directory);
        timeQuickSort = System.currentTimeMillis() - timeQuickSort;

//            searching by binary search
        long timeBinarySearching = System.currentTimeMillis();
        int count = 0;
        for (String userName : find) {
            if (algorithms.binarySearch(userName, directory) != -1) {
                count++;
            } else {
//                    System.out.println(algorithms.binarySearch(userName, copiedDirectory2));
            }

        }
        timeBinarySearching = System.currentTimeMillis() - timeBinarySearching;

//            get total time of both operations (sorting and searching)
        long totalTime2 = timeQuickSort + timeBinarySearching;

        System.out.printf("Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.\n", count, Time.msToMin(totalTime2), Time.msToSec(totalTime2), Time.msToMs(totalTime2));
        System.out.printf("Sorting time: %d min. %d sec. %d ms.", Time.msToMin(timeQuickSort), Time.msToSec(timeQuickSort), Time.msToMs(timeQuickSort));
//            if (!isCompletedSorted) System.out.print(" - STOPPED, moved to linear search");
        System.out.printf("\nSearching time: %d min. %d sec. %d ms.\n\n", Time.msToMin(timeBinarySearching), Time.msToSec(timeBinarySearching), Time.msToMs(timeBinarySearching));
    }
}