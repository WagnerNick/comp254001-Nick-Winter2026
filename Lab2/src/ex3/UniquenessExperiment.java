package ex3;

import java.util.Random;

public class UniquenessExperiment {
    //Time limit set to 1 second as because I kept doubling n I hit the integer or memory limit
    private static final long TIME_LIMIT = 1_000;
    private static final Random rand = new Random();

    private static int[] generateArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt();
        }
        return array;
    }

    private static boolean measureTime(int n, boolean useUnique1) {
        int[] data = generateArray(n);
        long start = System.nanoTime();

        if (useUnique1)
            Uniqueness.unique1(data);
        else
            Uniqueness.unique2(data);

        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;

        return duration <= TIME_LIMIT;
    }

    private static int findMaxN(boolean useUnique1) {
        int low = 1;
        int high = 1;

        //Get a large n to start with by doubling while also setting a high low for binary search
        while (measureTime(high, useUnique1)) {
            low = high;
            high *= 2;

            System.out.println("Testing n = " + high);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            System.out.println("Search testing n = " + mid);

            if (measureTime(mid, useUnique1)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        System.out.println("Testing unique1...");
        int max1 = findMaxN(true);
        System.out.println("Largest n for unique1 under 60s: " + max1);

        System.out.println("Testing unique2...");
        int max2 = findMaxN(false);
        System.out.println("Largest n for unique2 under 60s: " + max2);
    }
}

/*
private static long measureTime(int n, boolean useUnique1) {
        int[] data = generateArray(n);
        long start = System.currentTimeMillis();

        if (useUnique1)
            Uniqueness.unique1(data);
        else
            Uniqueness.unique2(data);

        long end = System.currentTimeMillis();
        return end - start;
    }

    private static int findMaxN(boolean useUnique1) {
        int low = 1;
        int high = 100_000;
        int best = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            long time = measureTime(mid, useUnique1);
            System.out.println("n = " + mid + " took " + time + " ms");

            if (time <= TIME_LIMIT) {
                best = mid;
                low = mid + 1;
            } else  {
                high = mid - 1;
            }
        }
        return best;
    }
 */
