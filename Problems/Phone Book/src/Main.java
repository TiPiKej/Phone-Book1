import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
             removed = true;   
        }

        public boolean isRemoved() {
             return removed;   
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            table[index] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            // put your code here
            int index = findKey(key);

            if (index == -1 || table[index] == null) {
                return null;
            }

            return table[index].removed ? null : (T) table[index].getValue();
        }

        public void remove(int key) {
            // put your code here
            int index = findKey(key);

            if (index == -1 || table[index] == null) return;

            table[index].remove();
        }

        private int findKey(int key) {
            // put your code here
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }
    }

    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);

        final int N = Integer.parseInt(scanner.nextLine());
        HashTable<String> hashTable = new HashTable<>(N);

        for (int i = 0; i < N; i++) {
            String[] line = scanner.nextLine().split("\\s");

            switch (line[0]) {
                case "get":
                    String temp = hashTable.get(Integer.parseInt(line[1]));
                    System.out.println(temp == null ? -1 : temp);
                    break;
                case "put":
                    hashTable.put(Integer.parseInt(line[1]), line[2]);
                    break;
                case "remove":
                    hashTable.remove(Integer.parseInt(line[1]));
                    break;
            }
        }

        scanner.close();
    }

    private static String formatNumber(int nbr) {
        return formatNumber(nbr, 4);
    }

    private static String formatNumber(String nbr) {
        return formatNumber(Integer.parseInt(nbr), 4);
    }

    private static String formatNumber(int nbr, int formatCount) {
        String res = Integer.toString(nbr);

        if (nbr == -1) return res;

        while (res.length() < formatCount) {
            res = "0" + res;
        }

        return res;
    }
}