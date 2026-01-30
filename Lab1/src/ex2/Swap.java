package ex2;

public class Swap {
    public static <E> void swapNodes(SinglyLinkedList<E> list, SinglyLinkedList.Node<E> node1, SinglyLinkedList.Node<E> node2) {

        if(node1 == node2) return;

        SinglyLinkedList.Node<E> prev1 = null, prev2 = null;
        SinglyLinkedList.Node<E> current = list.head;

        while(current != null){
            if (current.next == node1) prev1 = current;
            if (current.next == node2)  prev2 = current;
            current = current.next;
        }

        if (list.head == node1) prev1 = null;
        if (list.head == node2) prev2 = null;

        if (prev1 != null) prev1.next = node2;
        else list.head = node2;

        if (prev2 != null) prev2.next = node1;
        else list.head = node1;

        SinglyLinkedList.Node<E> temp = node1.getNext();
        node1.next = node2.next;
        node2.next = temp;

        if (node1.next == null) list.tail = node1;
        if (node2.next == null) list.tail = node2;
    }

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");

        SinglyLinkedList.Node<String> nodeB = list.head.next;
        SinglyLinkedList.Node<String> nodeD = list.head.next.next.next;

        System.out.println("Before swap: " + list);

        swapNodes(list, nodeB, nodeD);

        System.out.println("After swap: " + list);
    }
}
