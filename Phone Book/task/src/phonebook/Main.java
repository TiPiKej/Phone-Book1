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
            String[][] directory = new String[getFileLength(directoryFile)][3];
            String[][] find = new String[getFileLength(findFile)][2];

//            insert directory and file file into arrays
            String[] tempLine;
//            directory
            for (int i = 0; scannerDirectory.hasNextLine(); i++) {
                tempLine = scannerDirectory.nextLine().split(" ");
                if (tempLine.length >= 1) directory[i][0] = tempLine[0];
                if (tempLine.length >= 2) directory[i][1] = tempLine[1];
                if (tempLine.length >= 3) directory[i][2] = tempLine[2];
            }
//            find
            for (int i = 0; scannerFind.hasNextLine(); i++) {
                tempLine = scannerFind.nextLine().split("\\s");
                if (tempLine.length >= 1) find[i][0] = tempLine[0];
                if (tempLine.length >= 2) find[i][1] = tempLine[1];
            }

//            set new object containing directory file as array
            SearchAlgorithms algorithms = new SearchAlgorithms(directory);

//            searching by linear search
            System.out.println("Start searching...");

            int count = 0;
            long timeLinearSearching = System.currentTimeMillis();
            for (String[] user : find) {
                if (algorithms.linearSearch(user) != -1) {
                    count++;
                } else {
                    System.out.println(user[0] + " " + user[1]);
                }
            }
            timeLinearSearching = System.currentTimeMillis() - timeLinearSearching;

            System.out.printf("Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.\n\n", count, Time.msToMin(timeLinearSearching), Time.msToSec(timeLinearSearching), Time.msToMs(timeLinearSearching));

//            bubble sorting
            System.out.println("Start searching (bubble sort + jump search)...");
            long timeBubbleSort = System.currentTimeMillis();
            final boolean isCompletedSorted = algorithms.bubbleSort(timeBubbleSort, timeLinearSearching);
            timeBubbleSort = System.currentTimeMillis() - timeBubbleSort;

//            searching by jump search or linearSearch
            long timeJumpSearching = System.currentTimeMillis();
            count = 0;
            for (String[] user : find) {
                if (isCompletedSorted) { // jump search
                    if (algorithms.jumpSearch(user) != -1) {
                        count++;
                    }
                } else { // linear sorting
                    if (algorithms.linearSearch(user) != -1) {
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
            System.out.printf("\nSearching time: %d min. %d sec. %d ms.\n", Time.msToMin(timeJumpSearching), Time.msToSec(timeJumpSearching), Time.msToMs(timeJumpSearching));

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
}