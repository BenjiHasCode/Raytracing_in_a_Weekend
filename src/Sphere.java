public class Sphere implements Hittable {
    private final Vec3 center;
    private final double radius;

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean hit(Ray ray, double t_min, double t_max, Hit_record rec) {
        Vec3 oc = ray.origin().subtract(this.center);
        double a = ray.direction().length_squared();
        double half_b = oc.dot(ray.direction());
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
        rec.setP(ray.at(rec.getT()));
        Vec3 outward_normal = rec.getP()
                .subtract(center)
                .divide(radius);

        rec.set_face_normal(ray, outward_normal);
        return true;
    }
}