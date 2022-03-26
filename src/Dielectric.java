public class Dielectric implements Material {
    private final double ir; // Index of Refraction

    public Dielectric(double index_of_refraction) {
        this.ir = index_of_refraction;
    }

    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Color attenuation, Ray scattered) {
        attenuation.clone(new Color(1.0, 1.0, 1.0));
        double refraction_ratio = rec.isFront_face() ? (1.0/ir) : ir;

        Vec3 unit_direction = r_in.direction().unit_vector();
        double cos_theta = Math.min(unit_direction.minus().dot(rec.getNormal()), 1.0);
        double sin_theta = Math.sqrt(1.0 - cos_theta*cos_theta);

        boolean cannot_refract = refraction_ratio * sin_theta > 1.0;
        Vec3 direction;

        if (cannot_refract || reflectance(cos_theta, refraction_ratio) > Math.random())
            direction = unit_direction.reflect(rec.getNormal());
        else
            direction = unit_direction.refract(rec.getNormal(), refraction_ratio);

        scattered.clone(new Ray(rec.getP(), direction));
        return true;
    }

    private static double reflectance(double cosine, double ref_idx) {
        // Use Schlick's approximation for reflectance
        double r0 = (1-ref_idx) / (1+ref_idx);
        r0 = r0*r0;
        return r0 + (1-r0) * Math.pow((1-cosine), 5);
    }
}
