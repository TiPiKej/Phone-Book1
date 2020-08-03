import java.util.Map;
import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

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

            if (table[index] == null) {
                table[index] = new TableEntry(key, value);
            } else {
                table[index] = new TableEntry(key, String.format("%s %s", table[index].getValue(), value));
            }
            return true;
        }

//        public T get(int key) {
//            // put your code here
//            int index = findKey(key);
//
//            if (index == -1 || table[index] == null) {
//                return null;
//            }
//
//            return (T) table[index].getValue();
//        }

        public String[] entrySet() {
            // put your code here
            StringBuilder res = new StringBuilder();

            for (int i = 0; i < size; i++) {
                if (table[i] == null) continue;

                res.append(String.format("%d: %s\n", table[i].getKey(), table[i].getValue()));

//                System.out.println(String.format("%d %s\n", table[i].getKey(), table[i].getValue()));
            }

            return res.toString().split("\\n");
        }

        private int findKey(int key) {
            // put your code here
            int hash = 0;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == 0) {
                    return -1;
                }
            }

            return hash;
        }

//        private void rehash() {
//            // put your code here
//            boolean isFull = true;
//            for (int i = 0; i < this.size; i++) {
//                if (table[i] == null) {
//                    isFull = false;
//                    return;
//                }
//            }
//
//            TableEntry[] prevTable = table;
//            table = new TableEntry[++this.size];
//            for (TableEntry entry : prevTable) {
//                put(entry.getKey(), (T) entry.getValue());
//            }
//        }
    }

    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();

        HashTable<String> hashTable = new HashTable<>(N);

        for (int i = 0; i < N; i++) {
//            String[] tempLine = scanner.nextLine().split("\\s");
//            int tempKey = tempLine.length > 0 ? Integer.parseInt(tempLine[0]) : 0;
//            String tempName = tempLine.length > 1 ? tempLine[1] : null;
            int tempKey = scanner.nextInt();
            String tempName = scanner.next();

            hashTable.put(tempKey, tempName);
        }

        scanner.close();

        for (String z : hashTable.entrySet()) {
            System.out.println(z);
        }
    }
}