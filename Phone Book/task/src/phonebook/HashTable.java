package phonebook;

public class HashTable {
    private int size;
    private TableEntry[] table;
    private int usedSize;

    public HashTable(int size) {
        this.size = size;
        table = new TableEntry[size];
        usedSize = 0;
    }

    public boolean put(String key, int value) {
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

    public int get(String key) {
        int idx = findKey(key);

        if (idx == -1 || table[idx] == null) {
            return -1;
        }

        return table[idx].getNumber();
    }

    private int findKey(String name) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < name.toCharArray().length && i < 3; i++) {
            key.append((int) name.toCharArray()[i]);
        }

        int hash = Integer.parseInt(key.toString()) % size;

        while (!(table[hash] == null || table[hash].getKey().equals(name))) {
            hash = (hash + 1) % size;

            if (hash == Integer.parseInt(key.toString()) % size) {
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
            if (entry == null) continue;
            this.put(entry.getKey(), entry.getNumber());
        }
    }
}
