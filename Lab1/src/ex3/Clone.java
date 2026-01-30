package ex3;

public class Clone {

    public static <E> CircularlyLinkedList<E> cloneList(CircularlyLinkedList<E> original) {

        CircularlyLinkedList<E> copy = new CircularlyLinkedList<>();

        if (original.isEmpty()) return copy;

        int size = original.size();

        for (int i = 0; i < size; i++) {
            copy.addLast(original.first());
            original.rotate();
        }

        return copy;
    }

    public static void main(String[] args) {
        CircularlyLinkedList<String> list = new CircularlyLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        CircularlyLinkedList<String> cloned = cloneList(list);

        System.out.println("Original List: " + list);
        System.out.println("Cloned List: " + cloned);
    }
}
