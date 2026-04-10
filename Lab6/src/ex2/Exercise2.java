package ex2;

import common.*;

public class Exercise2 {

    public static void main(String[] args) {
        SortedTableMap<String, String> map = new SortedTableMap<>();

        map.put("apple", "red");
        map.put("banana", null);
        map.put("cherry", "dark red");

        System.out.println("=".repeat(55));
        System.out.println(" Exercise 2 - SortedTableMap.containKey() tests");
        System.out.println("=".repeat(55));

        String key1 = "apple";
        System.out.printf("%nKey: \"%s\"%n", key1);
        System.out.printf(" get()       = \"%s\"%n", map.get(key1));
        System.out.printf(" containKey() = %b   (expected: true)%n", map.containKey(key1));

        String key2 = "banana";
        System.out.printf("%nKey: \"%s\" (stored with null value)%n", key2);
        System.out.printf(" get()       = %s%n", map.get(key2));
        System.out.printf(" containKey() = %b   (expected: true - key IS present)%n", map.containKey(key2));

        String key3 = "mango";
        System.out.printf("%nKey: \"%s\" (never inserted)%n", key3);
        System.out.printf(" get()       = %s%n", map.get(key3));
        System.out.printf(" containKey() = %b   (expected: false - key NOT present)%n", map.containKey(key3));

        System.out.println();
        System.out.println("-".repeat(55));
        System.out.println("Conclusion");
        System.out.println("  get(\"banana\") and get (\"mango\") both return null,");
        System.out.println("  but containKey() correctly distinguishes them.");
        System.out.println("-".repeat(55));
    }
}
