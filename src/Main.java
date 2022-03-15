import view.ImageFrame;

import java.awt.*;
import util.Image;

public class Main {
    // Image
    private static final double aspect_ratio = 16.0 / 9.0;
    private static final int image_width = 1280 /2 ;
    private static final int image_height = (int) (image_width / aspect_ratio);
    private static final int samples_per_pixel = 100;
    private static final int max_depth = 50;

    // Setup frame
    private static final ImageFrame frame = new ImageFrame(image_width, image_height);

    // World
    private static final Hittable_list world = new Hittable_list();

    // Camera
    private static final Camera cam = new Camera();

    public static void main(String[] args) {
        // Populate world
        world.add(new Sphere(new Vec3(0, 0, -1), 0.5));
        world.add(new Sphere(new Vec3(0, -100.5, -1), 100));

        Graphics g = frame.getImageComponent().getBufferedImage().getGraphics();

        render(g);
    }

    private static void render(Graphics g) {
        System.out.println("Starting render:");
        for (int j = image_height-1; j >= 0; j--) {
            System.out.println("Lines remaining: " + (j + 1));
            for (int i = 0; i < image_width; i++) {
                Vec3 pixel_color = new Color(0, 0, 0);
                for (int s = 0; s < samples_per_pixel; s++) {
                    double u = (i + Math.random()) / (image_width-1);
                    double v = (j + Math.random()) / (image_height-1);
                    Ray ray = cam.get_ray(u, v);
                    pixel_color = pixel_color.add(ray_color(ray, world, max_depth));
                }
                Color color = Color.write_color(pixel_color, samples_per_pixel);
                g.setColor(new java.awt.Color((int)color.r(), (int)color.g(), (int)color.b()));
                g.fillRect(i, image_height - j, 1, 1);
            }
            frame.repaint();
        }
        System.out.println("Done!");
        // save
        Image.save(frame.getImageComponent().getBufferedImage());
    }

    public static Vec3 ray_color(Ray r, Hittable_list world, int depth) {
        Hit_record rec = new Hit_record();

        // If we've exceeded the ray bounce limit, no more light is gathered
        if (depth <= 0) {
            return new Color(0, 0, 0);
        }

        if (world.hit(r, 0.001, Double.POSITIVE_INFINITY, rec)) {
            Vec3 target = rec.getP()
                    .add(Vec3.random_in_hemisphere(rec.getNormal()));
            //  0.5 * ray_color(ray(rec.p, target - rec.p), world);
            return ray_color(new Ray(rec.getP(), target.subtract(rec.getP())), world, depth - 1)
                    .scale(0.5);
        }

        Vec3 unit_direction = r.direction().unit_vector();
        double t = 0.5 * (unit_direction.getY() + 1.0);
        return new Color(1.0, 1.0, 1.0).scale(0.5)
                .add(new Color(0.5, 0.7, 1.0).scale(t));
    }
}