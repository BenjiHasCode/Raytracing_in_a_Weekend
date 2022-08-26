public class Metal implements Material {
    private final Color albedo;
    private final double fuzz;

    public Metal(Color albedo, double f) {
        this.albedo = albedo;
        this.fuzz = f < 1 ? f : 1;
    }

    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Color attenuation, Ray scattered) {
        Vec3 reflected = r_in.direction()
                .unit_vector()
                .reflect(rec.getNormal());
        scattered.clone(new Ray(rec.getP(), reflected.add(Vec3.random_in_unit_sphere().scale(fuzz)), r_in.time()));
        attenuation.clone(albedo);

        return (scattered.direction().dot(rec.getNormal()) > 0);
    }
}
