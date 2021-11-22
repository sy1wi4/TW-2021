public class Main {
    public static void main(String[] args) {
        // 8-core CPU

        // segment mode
        new Mandelbrot(1, 200).setVisible(true);
        new Mandelbrot(8, 200).setVisible(true);
        new Mandelbrot(16, 200).setVisible(true);
        new Mandelbrot(32, 200).setVisible(true);

        new Mandelbrot(50, 50).setVisible(true);
        new Mandelbrot(50, 500).setVisible(true);

        // pixel mode
        new Mandelbrot(10).setVisible(true);
        new Mandelbrot(16).setVisible(true);
        new Mandelbrot(50).setVisible(true);
    }
}
