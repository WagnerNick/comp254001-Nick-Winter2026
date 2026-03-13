package ex1;

public class Exercise1 {

    public static <E> int indexOf(PositionalList<E> list, Position<E> p) {
        int index = 0;
        Position<E> walk = list.first();

        while (walk != null) {
            if (walk == p)
                return index;
            walk = list.after(walk);
            index++;
        }
        throw new IllegalArgumentException("Position is not in list");
    }

    public static void main(String[] args) {
        // multi element list test
        PositionalList<String> list = new LinkedPositionalList<>();
        Position<String> pA = list.addLast("A");
        Position<String> pB = list.addLast("B");
        Position<String> pC = list.addLast("C");
        Position<String> pD = list.addLast("D");

        System.out.println("List: " + list);
        System.out.println("indexOf(A) = " + indexOf(list, pA) + " (expected 0)");
        System.out.println("indexOf(B) = " + indexOf(list, pB) + " (expected 1)");
        System.out.println("indexOf(C) = " + indexOf(list, pC) + " (expected 2)");
        System.out.println("indexOf(D) = " + indexOf(list, pD) + " (expected 3)");

        // single element list test
        System.out.println();
        PositionalList<Integer> single = new LinkedPositionalList<>();
        Position<Integer> only = single.addFirst(38);
        System.out.println("Single Element list: " + single);
        System.out.println("indexOf(38) = " + indexOf(single, only) + " (expected 0)");

        // index shift test
        System.out.println();
        list.remove(pA);
        System.out.println("A removed: " + list);
        System.out.println("indexOf(B) = " + indexOf(list, pB) + " (expected 0)");
        System.out.println("indexOf(C) = " + indexOf(list, pC) + " (expected 1)");
        System.out.println("indexOf(D) = " + indexOf(list, pD) + " (expected 2)");

        // invalid argument test
        System.out.println();
        try {
            indexOf(list, pA);
            System.out.println("ERROR: should throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly threw IllegalArgumentException");
        }
    }
}
