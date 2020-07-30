package phonebook;

import java.util.Arrays;

class SearchAlgorithms {
    public int linearSearch(String findingName, String[][] findingArr) {
        int i = 0;
        for (String[] line : findingArr) {
            int number = -1;
            String name = null;

            switch (line.length) {
                case 2:
                    name = line[1];
                case 1:
                    number = Integer.parseInt(line[0]);
            }

            if (name.equals(findingName)) {
                return number;
//                return i;
            }
            i++;
        }

        return -1;
    }

    public boolean bubbleSort(String[][] arr, long time, long notBiggerTenTimes) {
        String[] temp;
        for (int curLength = arr.length; curLength > 0; curLength--) {
            if ((System.currentTimeMillis() - time) > (notBiggerTenTimes * 10)) {
                return false;
            }

            for (int i = 1; i < curLength; i++) {
                if (arr[i - 1][1].compareTo(arr[i][1]) > 0) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            }
        }
        return true;
    }

    public int jumpSearch(String findingName, String[][] findingArr) {
        int jump = (int) Math.sqrt(findingArr.length);

        int i = 0;
        while (findingArr[i][1].compareTo(findingName) < 0) {
            i = Math.min(jump + i, findingArr.length - 1);
        }

        for (int j = i; j > i - jump; j--) {
            if (findingArr[i][1].equals(findingName)) return Integer.parseInt(findingArr[i][0]);

            if (findingArr[i][1].compareTo(findingName) < 0) {
                return -1;
            }
        }

        return -1;
    }

    public void quickSort(String[][] arr) {
        this.quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(String[][] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivot = this.partition(arr, startIndex, endIndex);

//            quickSort(arr, startIndex, pivot - 1);
//            quickSort(arr, pivot + 1, endIndex);
        }
    }

    private int partition(String[][] arr, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i = (startIndex - 1);

        for (int j = startIndex; j <= endIndex - 1; j++) {
            if (arr[j][1].compareToIgnoreCase(arr[pivotIndex][1]) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, endIndex);
        return (i + 1);
    }

    private void swap(String[][] arr, int fIndex, int sIndex) {
        String[] temp = new String[2];
        temp[0] = arr[fIndex][0];
        temp[1] = arr[fIndex][1];
        arr[fIndex][0] = arr[sIndex][0];
        arr[fIndex][1] = arr[sIndex][1];
        arr[sIndex][0] = temp[0];
        arr[sIndex][1] = temp[1];
    }

    public int binarySearch(String findingName, String[][] findingArr) {
        return this.binarySearch(findingName, findingArr, 0, findingArr.length - 1);
    }

    public int binarySearch(String findingName, String[][] findingArr, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = left + (right - left) / 2;

        if (findingName.equals(findingArr[middle][1])) {
            return Integer.parseInt(findingArr[middle][0]);
        } else if (findingName.compareTo(findingArr[middle][1]) < 1) {
            return binarySearch(findingName, findingArr, left, middle - 1);
        } else {
            return binarySearch(findingName, findingArr, middle + 1, right);
        }
    }
}