import util.Random;
import view.ImageFrame;

import java.awt.image.BufferedImage;

import util.Image;

public class Main {
    public static void main(String[] args) {
        // Image
        double aspect_ratio = 16.0 / 9.0;
        int image_width = 1200;
        int image_height = (int) (image_width / aspect_ratio);
        int samples_per_pixel = 30;
        int max_depth = 50;

        // Setup frame
        ImageFrame frame = new ImageFrame(image_width, image_height, new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB));

        // World
        Hittable_list world = random_scene();

        // Camera
        Vec3 lookFrom = new Vec3(13,2,3);
        Vec3 lookAt = new Vec3(0,0,0);
        Vec3 vup = new Vec3(0,1,0);
        double dist_to_focus = 10;
        double aperture = 0.1;
        Camera cam = new Camera(lookFrom, lookAt, vup, 20, aspect_ratio, aperture, dist_to_focus);

        render(image_width, image_height, samples_per_pixel, max_depth, world, cam, frame);
    }

    private static void render(int image_width, int image_height, int samples_per_pixel, int max_depth, Hittable_list world, Camera cam, ImageFrame frame) {
        System.out.println("Starting render:");
        for (int j = image_height; j > 0; j--) {
            System.out.println("Lines remaining: " + j);
            for (int i = 0; i < image_width; i++) {
                Vec3 pixel_color = new Color(0, 0, 0);
                for (int s = 0; s < samples_per_pixel; s++) {
                    double u = (i + Math.random()) / (image_width-1);
                    double v = (j + Math.random()) / (image_height-1);
                    Ray ray = cam.get_ray(u, v);
                    pixel_color = pixel_color.add(ray_color(ray, world, max_depth));
                }
                Color c = Color.write_color(pixel_color, samples_per_pixel);
                frame.setRGB(i, image_height - j, c.r(), c.g(), c.b());
            }
            frame.repaint();
        }
        System.out.println("Done!");
        Image.save(frame.getImage());
    }

    public static Vec3 ray_color(Ray r, Hittable_list world, int depth) {
        Hit_record rec = new Hit_record();

        // If we've exceeded the ray bounce limit, no more light is gathered
        if (depth <= 0) {
            return new Color(0, 0, 0);
        }

        if (world.hit(r, 0.001, Double.POSITIVE_INFINITY, rec)) {
            Ray scattered = new Ray();
            Color attenuation = new Color();
            if (rec.getMaterial().scatter(r, rec, attenuation, scattered))
                return attenuation.multiply(ray_color(scattered, world, depth-1));
            return new Color(0, 0, 0);
        }

        Vec3 unit_direction = r.direction().unit_vector();
        double t = 0.5 * (unit_direction.getY() + 1.0);
        return new Color(1.0, 1.0, 1.0).scale(1.0-t)
                .add(new Color(0.5, 0.7, 1.0).scale(t));
    }

    public static Hittable_list random_scene() {
        Hittable_list world = new Hittable_list();

        Material ground_material = new Lambertian(new Color(0.5, 0.5, 0.5));
        world.add(new Sphere(new Vec3(0, -1000, 0), 1000, ground_material));

        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double choose_mat = Math.random();
                Vec3 center = new Vec3(a + 0.9*Math.random(), 0.2, b+0.9*Math.random());

                if (center.subtract(new Vec3(4, 0.2, 0)).length() > 0.9) {
                    Material sphere_material;

                    if (choose_mat < 0.8) {
                        // diffuse
                        Vec3 albedo = Vec3.random().multiply(Vec3.random());
                        sphere_material = new Lambertian(new Color(albedo));
                        world.add(new Sphere(center, 0.2, sphere_material));
                    } else if (choose_mat < 0.95) {
                        // metal
                        Vec3 albedo = Vec3.random(0.5, 1);
                        double fuzz = Random.getDouble(0, 0.5);
                        sphere_material = new Metal(new Color(albedo), fuzz);
                        world.add(new Sphere(center, 0.2, sphere_material));
                    } else {
                        // glass
                        sphere_material = new Dielectric(1.5);
                        world.add(new Sphere(center, 0.2, sphere_material));
                    }
                }
            }
        }

        Material material1 = new Dielectric(1.5);
        world.add(new Sphere(new Vec3(0, 1, 0), 1.0, material1));

        Material material2 = new Lambertian(new Color(0.4, 0.2, 0.1));
        world.add(new Sphere(new Vec3(-4, 1, 0), 1.0, material2));

        Material material3 = new Metal(new Color(0.7, 0.6, 0.5), 0.0);
        world.add(new Sphere(new Vec3(4, 1, 0), 1.0, material3));

        return world;
    }
}