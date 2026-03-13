package ex3;

public class Exercise3<E> extends LinkedQueue<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public Exercise3() { }

    @Override public int size() { return list.size(); }
    @Override public boolean isEmpty() { return list.isEmpty(); }
    @Override public void enqueue(E e) {list.addLast(e);}
    @Override public E first() {return list.first();}
    @Override public E dequeue() { return list.removeFirst();}
    @Override public String toString() { return list.toString(); }

    public void concatenate(Exercise3<E> Q2) {
        list.transferFrom(Q2.list);
    }

    public static void main(String[] args) {

        // basic concatenation test
        Exercise3<Integer> Q1 = new Exercise3<>();
        Exercise3<Integer> Q2 = new Exercise3<>();
        for (int i = 1; i <= 4; i++) Q1.enqueue(i);
        for (int i = 1; i <= 8; i++) Q2.enqueue(i);

        System.out.println("Before concatenate:");
        System.out.println("Q1 = " + Q1 + "size = " + Q1.size());
        System.out.println("Q2 = " + Q2 + "size = " + Q2.size());

        Q1.concatenate(Q2);

        System.out.println("\nAfter concatenate(Q1 - Q2):");
        System.out.println("Q1 = " + Q1 + " expected: 1,2,3,4,5,6,7,8");
        System.out.println("Q2 = " + Q2 + " expected: empty");
        System.out.println("Q1 size = " + Q1.size() + " expected: 8");
        System.out.println("Q2 size = " + Q2.size() + " expected: 0");

        // concatenate onto empty test
        System.out.println();
        Exercise3<String> emptyQ = new Exercise3<>();
        Exercise3<String> fullQ = new Exercise3<>();
        fullQ.enqueue("X");
        fullQ.enqueue("Y");
        fullQ.enqueue("Z");
        emptyQ.concatenate(fullQ);
        System.out.println("Concatenate full to empty:");
        System.out.println("emptyQ = " + emptyQ + " expected: x,y,z");
        System.out.println("fullQ = " + fullQ + " expected: empty");

        // concatenate empty Q2 test
        System.out.println();
        Exercise3<String> Q3 = new Exercise3<>();
        Q3.enqueue("Hello");
        Q3.concatenate(new Exercise3<>());
        System.out.println("Concatenate empty to non-empty");
        System.out.println("Q3 = " + Q3 + " expected: Hello");

        // FIFO order test
        System.out.println();
        System.out.println("Dequeue Q1 (expected: 1 2 3 4 5 6 7 8):");
        while (!Q1.isEmpty()) System.out.print(Q1.dequeue() + " ");
        System.out.println();
    }
}
