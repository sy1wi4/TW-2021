import java.util.concurrent.Callable;

public class PixelTask implements Callable<ImageElement> {
    private final int x, y;
    private final int maxIter;
    private final double zoom;
    private final Pixel pixel;

    public PixelTask(int x, int y, int maxIter, double zoom) {
        this.x = x;
        this.y = y;
        this.maxIter = maxIter;
        this.zoom = zoom;
        pixel = new Pixel(x, y);
    }

    @Override
    public Pixel call() {
//        System.out.println(toString());
        return generateMandelbrotSet();
    }

    private Pixel generateMandelbrotSet() {
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
        pixel.setRGB(iter | (iter << 8));
        return pixel;
    }

    @Override
    public String toString() {
        return "Pixel x:  " + x + " y: " + y + ", thread ID: " + Thread.currentThread().getId();
    }
}
