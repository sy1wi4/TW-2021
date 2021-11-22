import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mandelbrot extends JFrame {
    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    List<Future<ImageElement>> resultList;

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // pixel mode - only threads number needed
    public Mandelbrot(int threadsNumber) {
        this();

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        resultList = new ArrayList<>();

        long start = System.nanoTime();

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                PixelTask task = new PixelTask(x, y, MAX_ITER, ZOOM);
                Future<ImageElement> result = executor.submit(task);
                resultList.add(result);
            }
        }

        long stop = System.nanoTime();
        System.out.println("Pixel mode, threads: " + threadsNumber);
        System.out.println("TIME: " + (stop - start) + "\n");
    }

    // segment mode - also number of tasks needed (vertical segments)
    public Mandelbrot(int threadsNumber, int tasksNumber) {
        this();

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        resultList = new ArrayList<>();

        long start = System.nanoTime();

        int segmentWidth = getWidth() / tasksNumber;
        for (int i = 0; i < tasksNumber; i++) {
            int from = i * segmentWidth;
            int to = from + segmentWidth;
            SegmentTask task = new SegmentTask(getHeight(), from, to, MAX_ITER, ZOOM);
            Future<ImageElement> result = executor.submit(task);
            resultList.add(result);
        }

        long stop = System.nanoTime();
        System.out.println("Segment mode, threads: " + threadsNumber + ", tasks: " + tasksNumber);
        System.out.println("TIME: " + (stop - start) + "\n");
    }

    @Override
    public void paint(Graphics g) {
        for (Future<ImageElement> r : resultList) {
            ImageElement result = null;
            try {
                result = r.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            assert result != null;
            g.drawImage(result.getI(), result.getX(), result.getY(), this);
        }
    }
}
