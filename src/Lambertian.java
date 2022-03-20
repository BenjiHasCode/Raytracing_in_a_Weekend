public class Lambertian implements Material{
    private Color albedo;

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

        // scattered = new Ray(rec.getP(), reflected);
        scattered.setOrigin(rec.getP());
        scattered.setDirection(scatter_direction);
        // attenuation = albedo;
        attenuation.setX(albedo.getX());
        attenuation.setY(albedo.getY());
        attenuation.setZ(albedo.getZ());
        return true;
    }
}
