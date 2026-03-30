package ex1;

import common.*;

public class Exercise1 extends LinkedBinaryTree<Integer> {

    public Position<Integer> preorderNext(Position<Integer> p) {

        if (left(p) != null) return left(p);

        if(right(p) != null) return right(p);

        Position<Integer> cur = p;
        while (!isRoot(cur)) {
            Position<Integer> par = parent(cur);
            Position<Integer> parLeft = left(par);
            if (parLeft != null && parLeft.getElement().equals(cur.getElement()) && right(par) != null)
                return right(par);
            cur = par;
        }
        return null;
    }

    public static void main(String[] args) {
        Exercise1 tree = new Exercise1();

        Position<Integer> p1 = tree.addRoot(1);
        Position<Integer> p2 = tree.addLeft(p1, 2);
        Position<Integer> p3 = tree.addRight(p1, 3);
        Position<Integer> p4 = tree.addLeft(p2, 4);
        Position<Integer> p5 = tree.addRight(p2, 5);
        Position<Integer> p6 = tree.addRight(p3, 6);
        Position<Integer> p7 = tree.addLeft(p4, 7);

        System.out.println("=== preorderNext ===");

        Position<Integer> cur = tree.root();
        while (cur != null) {
            System.out.println(cur.getElement() + " ");
            cur = tree.preorderNext(cur);
        }
        System.out.println();
    }
}
