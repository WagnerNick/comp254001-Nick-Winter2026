package ex1;

import java.util.Random;

public class ExercisesAnalysis {

    private static final Random rand = new Random();

    public static int[] generateArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 4000};

        System.out.println("Running timing tests...\n");

        for (int n : sizes) {
            int[] arr1 = generateArray(n);
            int[] arr2 = generateArray(n);

            System.out.println("Array size n = " + n);

            //example1
            long start = System.nanoTime();
            Exercises.example1(arr1);
            long end = System.nanoTime();
            System.out.println("example1 time: " + (end - start) + " ns");

            //example2
            start = System.nanoTime();
            Exercises.example2(arr2);
            end = System.nanoTime();
            System.out.println("example2 time: " + (end - start) + " ns");

            //example3
            start = System.nanoTime();
            Exercises.example3(arr1);
            end = System.nanoTime();
            System.out.println("example3 time: " + (end - start) + " ns");

            //example4
            start = System.nanoTime();
            Exercises.example4(arr2);
            end = System.nanoTime();
            System.out.println("example4 time: " + (end - start) + " ns");

            //example5
            start = System.nanoTime();
            Exercises.example5(arr1, arr2);
            end = System.nanoTime();
            System.out.println("example5 time: " + (end - start) + " ns");
        }
    }

    /*
    example1
    Results:
    265200 ns, 6500 ns, 14000 ns, 28400 ns, 54900 ns

    When n doubles, time roughly doubles indicating linear growth
    O(n)

    example2
    Results:
    1700 ns, 3300 ns, 6600 ns, 14100 ns, 27600 ns

    When n doubles, time roughly doubles indicating linear growth
    O(n)

    example3
    Results:
    54300 ns, 882900 ns, 479900 ns, 469200 ns, 150400 ns

    The time fluctuates a lot unrelated to n suggesting time isn't strongly dependent on n
    O(1)

    example4
    Results:
    4400 ns, 8200 ns, 17100 ns, 34600 ns, 68200 ns

    When n doubles, time roughly doubles indicating linear growth
    O(n)

    example5
    Results:
    1410000 ns, 21499100 ns, 98804400 ns, 785317000 ns, 6227665600 ns

    Doubling n leads to roughly a 4-8x growth suggesting cubic growth
    O(n^3)
     */
}
