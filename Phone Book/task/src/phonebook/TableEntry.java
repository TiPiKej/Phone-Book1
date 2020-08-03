package phonebook;

public class TableEntry {
    private final String key;
    private final int number;

    public TableEntry(String key, int number) {
        this.key = key;
        this.number = number;
    }

    public String getKey() {
        return key;
    }

    public int getNumber() {
        return number;
    }
}