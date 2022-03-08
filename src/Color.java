public class Color extends Vec3 {
    public Color() {
        super();
    }

    public Color(double r, double g, double b) {
        super(r, g, b);
    }

    public double r() { return super.getX(); }
    public void r(double r) { this.setX(r); }
    public double g() { return super.getY(); }
    public void g(double g) { this.setY(g); }
    public double b() { return this.getZ(); }
    public void b(double b) { this.setZ(b); }

    public static double clamp(double x, double min, double max) {
        if (x < min) {
            return min;
        } else if (x > max) {
            return max;
        } else {
            return x;
        }
    }

    public static Color write_color(Vec3 pixel_color, int samples_per_pixel) {
        double r = pixel_color.getX();
        double g = pixel_color.getY();
        double b = pixel_color.getZ();

        // Divide the color by the number of samples and gamma-correct for gamma=2.0
        double scale = 1.0 / samples_per_pixel;
        r = Math.sqrt(scale * r);
        g = Math.sqrt(scale * g);
        b = Math.sqrt(scale * b);

        double red = 256 * clamp(r, 0.0, 0.999);    // TODO see if we need to parse to int
        double green = 256 * clamp(g, 0.0, 0.999);
        double blue = 256 * clamp(b, 0.0, 0.999);
        return new Color(red, green, blue);
    }
}