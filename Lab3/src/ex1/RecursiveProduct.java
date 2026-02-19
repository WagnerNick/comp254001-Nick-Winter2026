package ex1;

import java.util.Scanner;

public class RecursiveProduct {

    public static int product(int m, int n) {
        if (n == 0) {
            return 0;
        }
        return m + product(m, n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first positive integer (m): ");
        int m = scanner.nextInt();

        System.out.print("Enter second positive integer (n): ");
        int n = scanner.nextInt();

        int result = product(m, n);

        System.out.println("Product is: " + result);

        scanner.close();
    }

}
