package ex2;

import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        if (s.length() <= 1) {
            return true;
        }

        if(s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }

        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        boolean result = isPalindrome(input);

        if(result) {
            System.out.println("The string is palindrome");
        } else {
            System.out.println("The string is not a palindrome");
        }
        scanner.close();
    }
}
