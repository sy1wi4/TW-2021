import java.awt.image.BufferedImage;

public class MandelbrotSegment implements ImageElement {
    private final int from;
    private final BufferedImage I;

    public MandelbrotSegment(int height, int from, int to) {
        this.from = from;
        I = new BufferedImage(to - from, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getX() {
        return from;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public BufferedImage getI() {
        return I;
    }

    public void setRGB(int x, int y, int rgb) {
        I.setRGB(x, y, rgb);
    }

}
