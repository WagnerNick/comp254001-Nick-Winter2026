package ex1;

public class Concat {
    public static <E> DoublyLinkedList<E> concatenate(DoublyLinkedList<E> L, DoublyLinkedList<E> M) {

        DoublyLinkedList<E> result = new DoublyLinkedList<>();

        while (!L.isEmpty()) {
            result.addLast(L.removeFirst());
        }

        while (!M.isEmpty()) {
            result.addLast(M.removeFirst());
        }

        return result;
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> L = new DoublyLinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");

        DoublyLinkedList<String> M = new DoublyLinkedList<>();
        M.addLast("D");
        M.addLast("E");

        DoublyLinkedList<String> LPrime = concatenate(L, M);

        System.out.println("Concatenated list: " + LPrime);
    }
}
