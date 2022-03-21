public class Metal implements Material {
    private Color albedo;
    private double fuzz;

    public Metal(Color albedo, double f) {
        this.albedo = albedo;
        this.fuzz = f < 1 ? f : 1;
    }

    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Color attenuation, Ray scattered) {
        // reflect(unit_vector(r_in.direction()), rec.normal)
        Vec3 reflected = r_in
                .direction()
                .unit_vector()
                .reflect(rec.getNormal());
        // scattered = new Ray(rec.getP(), reflected + fuzz*random_in_unit_sphere());
        scattered.setOrigin(rec.getP());
        scattered.setDirection(reflected.add(Vec3.random_in_unit_sphere().scale(fuzz)));
        // attenuation = albedo;
        attenuation.setX(albedo.getX());
        attenuation.setY(albedo.getY());
        attenuation.setZ(albedo.getZ());
        // return (dot(scattered.direction(), rec.normal) > 0);
        return (scattered.direction().dot(rec.getNormal()) > 0);
    }
}
