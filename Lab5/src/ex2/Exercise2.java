package ex2;

import common.*;
import ex1.Exercise1;

public class Exercise2 extends LinkedBinaryTree<Integer>{

    public int printHeights(Position<Integer> p) {
        int leftHeight = -1;
        int rightHeight = -1;

        if (left(p) != null) leftHeight = printHeights(left(p));
        if (right(p) != null) rightHeight = printHeights(right(p));

        int height;
        if (isExternal(p)) {
            height = 0;
        } else {
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        System.out.println("Element: " + p.getElement() + " | Subtree height: " + height);

        return height;
    }

    public static void main(String[] args) {

        System.out.println("=== Tree 1 ===");
        Exercise2 tree1 = new Exercise2();
        Position<Integer> p1 = tree1.addRoot(1);
        Position<Integer> p2 = tree1.addLeft(p1, 2);
        Position<Integer> p3 = tree1.addRight(p1, 3);
        Position<Integer> p4 = tree1.addLeft(p2, 4);
        Position<Integer> p5 = tree1.addRight(p2, 5);
        Position<Integer> p6 = tree1.addRight(p3, 6);
        Position<Integer> p7 = tree1.addLeft(p4, 7);

        tree1.printHeights(tree1.root());

        System.out.println("\n=== Tree 2 === (right-skewed chain)");
        Exercise2 tree2 = new Exercise2();
        Position<Integer> c1 = tree2.addRoot(10);
        Position<Integer> c2 = tree2.addRight(c1, 20);
        Position<Integer> c3 = tree2.addRight(c2, 30);
        Position<Integer> c4 = tree2.addRight(c3, 40);

        tree2.printHeights(tree2.root());

        System.out.println("\n=== Tree 3 === (single node)");
        Exercise2 tree3 = new Exercise2();
        tree3.addRoot(99);
        tree3.printHeights(tree3.root());
    }
}
