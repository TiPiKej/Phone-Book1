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
        private int usedSize;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
            usedSize = 0;
        }

        public boolean put(int key, T value) {
            // put your code here
            if (usedSize == size) rehash();

            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            table[index] = new TableEntry(key, value);
            usedSize++;

            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();          
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            // put your code here
            final int scalingFactor = 2;

            TableEntry[] oldTable = table;

            size *= scalingFactor;
            this.table = new TableEntry[size];
            usedSize = 0;

            for (TableEntry entry : oldTable) {
                this.put(entry.getKey(), (T) entry.getValue());
            }
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                                                + ", value=" + table[i].getValue());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();

        HashTable<String> hashTable = new HashTable<>(5);

        for (int i = 0; i < N; i++) {
            int tempKey = scanner.nextInt();
            String tempName = scanner.next();

            hashTable.put(tempKey, tempName);
        }

        scanner.close();

        System.out.println(hashTable.toString());
    }
}