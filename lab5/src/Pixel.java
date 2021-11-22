import java.awt.image.BufferedImage;

public class Pixel implements ImageElement {
    private final int x, y;
    private final BufferedImage I;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        I = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public BufferedImage getI() {
        return I;
    }

    public void setRGB(int rgb) {
        I.setRGB(0, 0, rgb);
    }
}
