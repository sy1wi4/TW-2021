import java.awt.image.BufferedImage;

public class MandelbrotSegment {
    private final int from;
    private final BufferedImage I;

    public MandelbrotSegment(int height, int from, int to) {
        this.from = from;
        I = new BufferedImage(to - from, height, BufferedImage.TYPE_INT_RGB);
    }

    public int getStartX() {
        return from;
    }

    public void setRGB(int x, int y, int rgb) {
        I.setRGB(x, y, rgb);
    }

    public BufferedImage getI() {
        return I;
    }
}
