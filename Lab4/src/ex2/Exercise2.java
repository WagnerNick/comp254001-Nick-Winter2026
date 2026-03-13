package ex2;

public class Exercise2 {

    public static <E> void transfer(Stack<E> S, Stack<E> T) {
        while (!S.isEmpty()) {
            T.push(S.pop());
        }
    }

    public static void main(String[] args) {

        // Basic transfer test
        LinkedStack<Integer> S = new LinkedStack<>();
        for (int i = 1; i <= 5; i++) S.push(i);

        LinkedStack<Integer> T = new LinkedStack<>();

        System.out.println("Before transfer:");
        System.out.println("S = " + S + " (top is 5)");
        System.out.println("T = " + T + " (empty)");

        transfer(S, T);

        System.out.println("\nAfter transfer(S, T):");
        System.out.println("S = " + S + " (expected: empty)");
        System.out.println("T = " + T + " (expected: 1, 2, 3, 4, 5)");

        // Transfer to non-empty T test
        System.out.println();
        LinkedStack<String> S2 = new LinkedStack<>();
        S2.push("bottom");
        S2.push("middle");
        S2.push("top");

        LinkedStack<String> T2 = new LinkedStack<>();
        T2.push("existing");

        System.out.println("Before transfer:");
        System.out.println("S2 = " + S2);
        System.out.println("T2 = " + T2);

        transfer(S2, T2);

        System.out.println("\nAfter transfer(S2, T2):");
        System.out.println("S2 = " + S2 + " (expected: empty)");
        System.out.println("T2 = " + T2 + " (expected: bottom, middle, top, existing)");

        // Transfer from empty stack test
        System.out.println();
        LinkedStack<Integer> emptyS = new LinkedStack<>();
        LinkedStack<Integer> T3 = new LinkedStack<>();
        T3.push(88);
        transfer(emptyS, T3);
        System.out.println("Transfer from empty (S - T3) = " + T3 + " (expected: 88, unchanged)");

        // Pop order after transfer test
        System.out.println();
        System.out.println("Pop order for T (expected: 1 2 3 4 5):");
        while (!T.isEmpty()) System.out.print(T.pop() + " ");
        System.out.println();
    }
}
