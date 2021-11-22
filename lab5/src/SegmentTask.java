import java.util.concurrent.Callable;

public class SegmentTask implements Callable<ImageElement> {
    private final int from, to, height;
    private final int maxIter;
    private final double zoom;
    private final MandelbrotSegment segment;

    public SegmentTask(int height, int from, int to, int maxIter, double zoom) {
        this.from = from;
        this.to = to;
        this.height = height;
        this.maxIter = maxIter;
        this.zoom = zoom;
        segment = new MandelbrotSegment(height, from, to);
    }

    @Override
    public MandelbrotSegment call() {
//        System.out.println(toString());
        return generateMandelbrotSet();
    }

    private MandelbrotSegment generateMandelbrotSet() {
        for (int y = 0; y < height; y++) {
            for (int x = from; x < to; x++) {
                double zy;
                double zx = zy = 0;
                double cX = (x - 400) / zoom;
                double cY = (y - 300) / zoom;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                segment.setRGB(x - from, y, iter | (iter << 8));
            }
        }
        return segment;
    }

    @Override
    public String toString() {
        return "Segment from " + from + " to " + to + ", thread ID: " + Thread.currentThread().getId();
    }
}
