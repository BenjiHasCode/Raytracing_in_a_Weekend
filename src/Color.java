public class Color extends Vec3 {
    public Color() {
        super();
    }

    public Color(double r, double g, double b) {
        super(r, g, b);
    }

    public Color(Vec3 v) {
        super(v);
    }

    public double r() { return this.x; }
    public void r(double r) { this.x = r; }
    public double g() { return this.y; }
    public void g(double g) { this.y = g; }
    public double b() { return this.z; }
    public void b(double b) { this.z = b; }

    public static double clamp(double x, double min, double max) {
        if (x < min)
            return min;
        else return Math.min(x, max);
    }

    public static Color write_color(Vec3 pixel_color, int samples_per_pixel) {
        // Divide the color by the number of samples and gamma-correct for gamma=2.0
        double scale = 1.0 / samples_per_pixel;
        double r = Math.sqrt(scale * pixel_color.x);
        double g = Math.sqrt(scale * pixel_color.y);
        double b = Math.sqrt(scale * pixel_color.z);

        double red = 256 * clamp(r, 0.0, 0.999);
        double green = 256 * clamp(g, 0.0, 0.999);
        double blue = 256 * clamp(b, 0.0, 0.999);

        return new Color(red, green, blue);
    }
}