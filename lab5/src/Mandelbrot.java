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
    List<Future<MandelbrotSegment>> resultList;

    public Mandelbrot(int threadsNumber, int tasksNumber) {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        resultList = new ArrayList<>();

        int segmentWidth = getWidth() / tasksNumber;
        for (int i = 0; i < tasksNumber; i++) {
            int from = i * segmentWidth;
            int to = from + segmentWidth;
            Task task = new Task(getHeight(), from, to, MAX_ITER, ZOOM);
            Future<MandelbrotSegment> result = executor.submit(task);
            resultList.add(result);
        }
    }

    @Override
    public void paint(Graphics g) {
        for (Future<MandelbrotSegment> s : resultList) {
            MandelbrotSegment segment = null;
            try {
                segment = s.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            assert segment != null;
            g.drawImage(segment.getI(), segment.getStartX(), 0, this);
        }
    }

    public static void main(String[] args) {
        new Mandelbrot(10, 5).setVisible(true);
    }
}
