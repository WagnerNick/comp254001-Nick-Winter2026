package ex2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrefixAverageExperiment extends JPanel {

    public static final List<Double> inputSizes = new ArrayList<>();
    public static final List<Double> times1 = new ArrayList<>();
    public static final List<Double> times2 = new ArrayList<>();

    private static final Random rand = new Random();

    public static void main(String[] args) {
        int[] testSizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000};

        for (int n : testSizes) {
            double[] data = new double[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextDouble();
            }

            long start1 = System.nanoTime();
            PrefixAverage.prefixAverage1(data);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            PrefixAverage.prefixAverage2(data);
            long end2 = System.nanoTime();

            double time1 = (end1 - start1) / 1e3;
            double time2 = (end2 - start2) / 1e3;

            inputSizes.add((double) n);
            times1.add(time1);
            times2.add(time2);

            System.out.println("n = " + n + " | prefixAverage1: " + time1 + " μs | prefixAverage2: " + time2 + " μs");
        }

        JFrame frame = new JFrame("Prefix Average Performance (Log-Log Plot)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new PrefixAverageExperiment());
        frame.setVisible(true);
    }

    //Code for the graph was from a tutorial online not my own creation

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int margin = 80;

        g2.drawLine(margin, height - margin, width - margin, height - margin); // X axis
        g2.drawLine(margin, margin, margin, height - margin); // Y axis

        g2.drawString("Input Size", width / 2, height - 30);
        g2.drawString("Time (μs)", 20, height /2);

        double minLogN = Double.MAX_VALUE;
        double maxLogN = Double.MIN_VALUE;

        for (double n : inputSizes) {
            double logN = Math.log(n);
            minLogN = Math.min(minLogN, logN);
            maxLogN = Math.max(maxLogN, logN);
        }

        double maxLogTime = Math.log(Math.max(times1.get(times1.size() - 1), times2.get(times2.size() - 1)));

        g2.setColor(Color.RED);
        drawCurve(g2, inputSizes, times1, maxLogN, minLogN, maxLogTime, margin, width, height);

        g2.setColor(Color.BLUE);
        drawCurve(g2, inputSizes, times2, maxLogN, minLogN, maxLogTime, margin, width, height);

        g2.setColor(Color.BLACK);
        g2.drawString("Red = prefixAverage1", 100, 40);
        g2.drawString("Blue = prefixAverage2", 100, 60);
    }

    private void drawCurve(Graphics2D g2, List<Double> nValues, List<Double> timeValues,
                           double maxLogN, double minLogN, double maxLogTime, int margin, int width, int height) {

        int prevX = -1;
        int prevY = -1;

        for (int i = 0; i < nValues.size(); i++) {
            double logN = Math.log(nValues.get(i));
            double logTime = Math.log(timeValues.get(i));

            int x = (int) (margin + (logN - minLogN) / (maxLogN - minLogN) * (width - 2 * margin));
            int y = (int) (height - margin - (logTime / maxLogTime) * (height - 2 * margin));

            g2.fillOval(x - 4, y - 4, 8, 8);

            if(prevX != -1)
                g2.drawLine(prevX, prevY, x, y);

            prevX = x;
            prevY = y;
        }
    }
}