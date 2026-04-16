package ex2;

import common.*;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise2 {

    public static <K> void merge(LinkedQueue<K> S1, LinkedQueue<K> S2, Comparator<K> comp, LinkedQueue<K> S) {
        while (!S1.isEmpty() && !S2.isEmpty()) {
            if (comp.compare(S1.first(), S2.first()) <= 0) {
                S.enqueue(S1.dequeue());
            } else {
                S.enqueue(S2.dequeue());
            }
        }
        while (!S1.isEmpty()) S.enqueue(S1.dequeue());
        while (!S2.isEmpty()) S.enqueue(S2.dequeue());
    }

    public static <K> void mergeSort(LinkedQueue<K> S, Comparator<K> comp) {
        int n = S.size();
        if (n <= 1) return;

        LinkedQueue<LinkedQueue<K>> queueOfQueues = new LinkedQueue<>();

        while (!S.isEmpty()) {
            LinkedQueue<K> singleItem = new LinkedQueue<>();
            singleItem.enqueue(S.dequeue());
            queueOfQueues.enqueue(singleItem);
        }

        while (queueOfQueues.size() > 1) {
            int pairs = queueOfQueues.size() / 2;

            for (int i = 0; i < pairs; i++) {
                LinkedQueue<K> q1 = queueOfQueues.dequeue();
                LinkedQueue<K> q2 = queueOfQueues.dequeue();
                LinkedQueue<K> merged = new LinkedQueue<>();
                merge(q1, q2, comp, merged);
                queueOfQueues.enqueue(merged);
            }
        }

        LinkedQueue<K> sorted = queueOfQueues.dequeue();
        while (!sorted.isEmpty()) {
            S.enqueue(sorted.dequeue());
        }
    }

    private static <K> void printQueue(LinkedQueue<K> q) {
        int size = q.size();
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = q.dequeue();
        }
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            if (i < size - 1) System.out.print(", ");
            q.enqueue((K) arr[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        LinkedQueue<Integer> intQ = new LinkedQueue<>();
        int[] data = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        for (int x : data) intQ.enqueue(x);

        System.out.println("Before sort: ");
        printQueue(intQ);

        mergeSort(intQ, Comparator.naturalOrder());

        System.out.print("After sort: ");
        printQueue(intQ);

        System.out.println();

        LinkedQueue<String> strQ = new LinkedQueue<>();
        String[] words = {"banana", "apple", "cherry", "date", "fig", "blueberry"};
        for (String w : words) strQ.enqueue(w);

        System.out.println("Before sort: ");
        printQueue(strQ);

        mergeSort(strQ, Comparator.naturalOrder());

        System.out.print("After sort: ");
        printQueue(strQ);

        System.out.println();

        LinkedQueue<Integer> emptyQ = new LinkedQueue<>();
        mergeSort(emptyQ, Comparator.naturalOrder());
        System.out.println("Empty queue after sort: size = " + emptyQ.size());

        LinkedQueue<Integer> singleQ = new LinkedQueue<>();
        singleQ.enqueue(42);
        mergeSort(singleQ, Comparator.naturalOrder());
        System.out.print("Single queue after sort: size = ");
        printQueue(singleQ);

        LinkedQueue<Integer> sortedQ = new LinkedQueue<>();
        for (int x : new int[] {1, 2, 3, 4, 5}) sortedQ.enqueue(x);
        mergeSort(sortedQ, Comparator.naturalOrder());
        System.out.print("Already sorted: ");
        printQueue(sortedQ);

        LinkedQueue<Integer> reverseQ = new LinkedQueue<>();
        for (int x : new int[] {5, 4, 3, 2, 1}) reverseQ.enqueue(x);
        mergeSort(reverseQ, Comparator.naturalOrder());
        System.out.print("Reverse sorted: ");
        printQueue(reverseQ);
    }
}
