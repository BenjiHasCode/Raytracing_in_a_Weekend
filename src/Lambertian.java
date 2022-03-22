public class Lambertian implements Material{
    private final Color albedo;

    public Lambertian(Color albedo) {
        this.albedo = albedo;
    }


    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Color attenuation, Ray scattered) {
        Vec3 scatter_direction = rec.getNormal().add(Vec3.random_unit_vector());

        // Catch degenerate scatter direction
        if (scatter_direction.near_zero()) {
            scatter_direction = rec.getNormal();
        }

        scattered.clone(new Ray(rec.getP(), scatter_direction));
        attenuation.clone(albedo);
        return true;
    }
}
