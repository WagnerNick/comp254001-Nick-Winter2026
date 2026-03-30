package ex3;

import common.*;
import java.util.Comparator ;

public class Exercise3<K, V> extends HeapPriorityQueue<K, V> {

    public Exercise3() {super();}

    public Exercise3(Comparator<K> comp) { super(comp); }

    @Override
    protected void upheap(int j){
        if (j == 0) return;

        int p = parent(j);

        if (compare(heap.get(j), heap.get(p)) >= 0) return;

        swap(j, p);
        upheap(p);
    }

    public static void main(String[] args){
        Exercise3<String, String> pq = new Exercise3<>();

        System.out.println("=== Inserting entries ===");
        pq.insert("47", "A");
        pq.insert("75", "C");
        pq.insert("28", "B");
        pq.insert("51", "D");
        pq.insert("31", "F");
        pq.insert("22", "G");
        pq.insert("15", "H");

        System.out.println("Heap array after inserts:");
        for (int i = 0; i < pq.size(); i++)
            System.out.println(" [" + i + "] (" +pq.heap.get(i).getKey() + ", " +pq.heap.get(i).getValue() + ")");

        System.out.println("\n=== Removing entries in order ===");
        while (!pq.isEmpty()) {
            Entry<String, String> e = pq.removeMin();
            System.out.println(" removeMin -> (" + e.getKey() + ", " + e.getValue() + ")");
        }

        System.out.println("\n=== Numeric key test ===");
        Exercise3<Integer, String> numPQ = new Exercise3<>();
        int[] keys = {5, 3, 8, 1, 4, 2, 7};
        String[] vals = {"five", "three", "eight", "one", "four", "two", "seven"};
        for (int i = 0; i < keys.length; i++) numPQ.insert(keys[i], vals[i]);

        System.out.println("Removing in sorted order:");
        while (!numPQ.isEmpty()) {
            Entry<Integer, String> e = numPQ.removeMin();
            System.out.println(" (" + e.getKey() + ", " + e.getValue() + ")");
        }
    }
}
