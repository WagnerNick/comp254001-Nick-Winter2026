package ex3;

import java.io.File;

public class FileFinder {

    public static void find(String path, String filename) {
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Path does not exist");
            return;
        }

        if (file.isFile() && file.getName().equals(filename)) {
            System.out.println("Found: " + file.getAbsolutePath());
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files != null) {
                for (File f : files) {
                    find(f.getAbsolutePath(), filename);
                }
            }
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\nickw\\Documents";
        String filename = "test.txt";

        find(path, filename);
    }
}
