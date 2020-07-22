package phonebook;

import java.util.Arrays;

class SearchAlgorithms {
    private String[][] arr;

    public SearchAlgorithms () {
        this.arr = new String[0][0];
    }

    public SearchAlgorithms (String[][] arr) {
        this.arr = arr;
    }

    public int linearSearch(String[] value) {
        for (String[] line : this.arr) {
            int number = -1;
            String name = null;
            String surname = null;

            switch (line.length) {
                case 3:
                    surname = line[2];
                case 2:
                    name = line[1];
                case 1:
                    number = Integer.parseInt(line[0]);
            }

            if (name.equals(value[0]) && surname == null && value[1] == null) {
                return number;
            }

            if (surname == null || value[1] == null) {
                continue;
            }

            if (name.equals(value[0]) && surname.equals(value[1])) {
                return number;
            }
        }

        return -1;
    }

    public boolean bubbleSort(long time, long notBiggerTenTimes) {
        String[] temp;
        for (int curLength = arr.length; curLength > 0; curLength--) {
            if ((System.currentTimeMillis() - time) > (notBiggerTenTimes * 10)) {
                return false;
            }

            for (int i = 1; i < curLength; i++) {
                if (
                        (
                                arr[i - 1].length == 2 && arr[i].length == 2 &&
                                arr[i - 1][1].compareTo(arr[i][1]) > 0
                        ) || (
                                arr[i - 1].length == 3 && arr[i].length == 3 &&
                                arr[i - 1][1].compareTo(arr[i][1]) == 0 &&
                                arr[i - 1][2].compareTo(arr[i][2]) > 0
                        )
                ) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            }
        }
        return true;
    }

    public int jumpSearch(String[] value) {
        int jump = (int) Math.sqrt(arr.length);

        int i = 0;
        while (
                (
                        arr[i].length == 2 && value.length == 1 &&
                        arr[i][1].compareTo(value[0]) <= 0
                ) || (
                        arr[i].length == 3 && value.length == 2 &&
                        arr[i][1].compareTo(value[0]) <= 0 &&
                        arr[i][2].compareTo(value[1]) <= 0
                )
        ) {
            i = Math.min(jump + i, arr.length - 1);
        }

        for (int j = i; j > i - jump; j--) {
            if (arr[i].length == 2 && value.length == 1) {
                if (arr[i][1].equals(value[0])) return Integer.parseInt(arr[i][0]);
            }

            if (arr[i].length == 3 && value.length == 2) {
                if (arr[i][1].equals(value[0]) && arr[i][2].equals(value[1]))
                    return Integer.parseInt(arr[i][0]);
            }

            if (
                    (
                            arr[i].length == 2 && value.length == 1 &&
                                    arr[i][1].compareTo(value[0]) < 0
                    ) || (
                            arr[i].length == 3 && value.length == 2 &&
                                    arr[i][1].compareTo(value[0]) < 0 &&
                                    arr[i][2].compareTo(value[1]) < 0
                    )
            ) {
                return -1;
            }
        }

        return -1;
    }
}