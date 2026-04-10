package ex1;

import common.*;

import java.util.Random;

public class Exercise1 {
    private static final int NUM_ENTRIES = 10_000;
    private static final int NUM_LOOKUPS = 10_000;
    private static final int INITIAL_CAP = 17;
    private static final int KEY_RANGE = 100_000;

    private static final double[] LOAD_FACTORS = { 0.25, 0.5, 0.75, 0.9 };

    private static void runExperiment(String label, AbstractHashMap<Integer,
            Integer> map, double load, Random rand) {

        int[] keys = new int[NUM_ENTRIES];
        long start = System.nanoTime();
        for (int i = 0; i < NUM_ENTRIES; i++) {
            keys[i] = rand.nextInt(KEY_RANGE);
            map.put(keys[i], i);
        }
        long insetMs = (System.nanoTime() - start) / 1_000_000;

        start = System.nanoTime();
        for (int i = 0; i < NUM_LOOKUPS; i++) {
            map.get(rand.nextInt(KEY_RANGE));
        }
        long lookupMs = (System.nanoTime() - start) / 1_000_000;

        System.out.printf("%-18s %-8.2f %-14d %-14d %-10d%n",
            label, load, insetMs, lookupMs, map.size());
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println(" Hash Map Load Factor Experiment");
        System.out.printf(" Entries: %,d | Lookups: %,d | Key range: 0..%,d%n",
                NUM_ENTRIES, NUM_LOOKUPS, KEY_RANGE);
        System.out.println("=".repeat(70));

        System.out.printf("%-18s %-8s %-14s %-14s %-10s%n",
                "Map Type", "MaxLoad", "Insert (ms)", "Lookup (ms)", "Final Size");
        System.out.println("-".repeat(70));

        Random rand = new Random(42);

        for (double load : LOAD_FACTORS) {
            runExperiment("ChainHashMap", new ChainHashMap<>(INITIAL_CAP, load), load, rand);
            runExperiment("ProbeHashMap", new ProbeHashMap<>(INITIAL_CAP, load), load, rand);
            System.out.println("-".repeat(70));
        }

        System.out.println();
        System.out.println("Notes:");
        System.out.println(" - Lower load factor = more resizing = more memory, but fewer collisions.");
        System.out.println(" - Higher load factor = fewer resizing = denser table, but more collisions.");
        System.out.println(" - ProbeHashMap degrades faster at high loads due to clustering.");
        System.out.println(" - ChainHashMap handles high loads more gracefully via chaining.");
    }
}
