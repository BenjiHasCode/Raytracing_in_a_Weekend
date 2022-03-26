public class Sphere implements Hittable {
    private final Vec3 center;
    private final double radius;
    private final Material material;

    public Sphere(Vec3 center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public boolean hit(Ray r, double t_min, double t_max, Hit_record rec) {
        Vec3 oc = r.origin().subtract(this.center);
        double a = r.direction().length_squared();
        double half_b = oc.dot(r.direction());
        double c = oc.length_squared() - this.radius*this.radius;

        double discriminant = half_b*half_b - a*c;
        if (discriminant < 0)
            return false;

        double sqrtd = Math.sqrt(discriminant);

        // Find the nearest root that lies in the acceptable range.
        double root = (-half_b - sqrtd) / a;

        if (root < t_min || root > t_max) {
            root = (-half_b + sqrtd) / a;
            if (root < t_min || root > t_max)
                return false;
        }

        rec.setT(root);
        rec.setP(r.at(rec.getT()));
        Vec3 outward_normal = rec.getP()
                .subtract(center)
                .divide(radius);
        rec.set_face_normal(r, outward_normal);
        rec.setMaterial(material);

        return true;
    }
}