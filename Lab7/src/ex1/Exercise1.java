package ex1;

import common.*;
import java.util.Comparator;

public class Exercise1<K, V> extends TreeMap1<K, V> {

    public Exercise1(){
        super();
    }

    public Exercise1(Comparator<K> comp){
        super(comp);
    }

    @Override
    protected Position<Entry<K, V>> iterativeTreeSearch(Position<Entry<K, V>> p, K key) {
        while(isInternal(p)) {
            int comp = compare(key, p.getElement());
            if (comp == 0) {
                return p;
            } else if (comp < 0) {
                p = left(p);
            } else {
                p = right(p);
            }
        }
        return p;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> p = iterativeTreeSearch(root(), key);
        if (isExternal(p)) return null;
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K, V>> p = iterativeTreeSearch(root(), key);
        if (isExternal(p)){
            expandExternal(p, newEntry);
            return null;
        } else {
            V old = p.getElement().getValue();
            set(p, newEntry);
            return old;
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> p = iterativeTreeSearch(root(), key);
        if (isExternal(p)) {
            return null;
        } else {
            V old = p.getElement().getValue();
            if (isInternal(left(p)) && isInternal(right(p))) {
                Position<Entry<K, V>> replacement = treeMax(left(p));
                set(p, replacement.getElement());
                p = replacement;
            }
            Position<Entry<K, V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
            remove(leaf);
            remove(p);
            return old;
        }
    }

    protected void dump(){
        dumpRecurse(root(), 0);
    }

    private void dumpRecurse(Position<Entry<K, V>> p, int depth){
        String indent = (depth == 0 ? "" : String.format("%" + (2 * depth) + "s", ""));
        if (isExternal(p))
            System.out.println(indent + "leaf");
        else {
            System.out.print(indent + p.getElement());
            dumpRecurse(left(p), depth + 1);
            dumpRecurse(right(p), depth + 1);
        }
    }

    public static void main(String[] args) {
        Exercise1<Integer, String> map = new Exercise1<>();

        map.put(6, "A");
        map.put(2, "B");
        map.put(4, "C");
        map.put(1, "D");
        map.put(9, "E");
        map.put(8, "F");

        System.out.println("get(4)          : " + map.get(4));
        System.out.println("higherEntry(2)  : " + map.higherEntry(2));

        System.out.println("\nAll values (in order):");
        for (String value : map.values()) {
            System.out.println("  " + value);
        }

        System.out.println("\nAll entries: " + map.entrySet());

        System.out.println("\nTree structure:");
        map.dump();

        map.remove(1);
        System.out.println("\nAfter remove(1):");
        map.dump();

        map.put(4, "COMP");
        map.put(11, "SET");
        System.out.println("\nAfter put(4,COMP) and put(11,SET):");
        map.dump();

        System.out.println("get(11): "  + map.get(11));
    }

}
