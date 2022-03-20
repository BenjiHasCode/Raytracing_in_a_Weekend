public class Metal implements Material {
    private Color albedo;

    public Metal(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Color attenuation, Ray scattered) {
        // reflect(unit_vector(r_in.direction()), rec.normal)
        Vec3 reflected = r_in
                .direction()
                .unit_vector()
                .reflect(rec.getNormal());
        // scattered = new Ray(rec.getP(), reflected);
        scattered.setOrigin(rec.getP());
        scattered.setDirection(reflected);
        // attenuation = albedo;
        attenuation.setX(albedo.getX());
        attenuation.setY(albedo.getY());
        attenuation.setZ(albedo.getZ());
        // return (dot(scattered.direction(), rec.normal) > 0);
        return (scattered.direction().dot(rec.getNormal()) > 0);
    }
}
