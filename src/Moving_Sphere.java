public class Moving_Sphere implements Hittable {
    private Vec3 center0, center1;
    private double time0, time1;
    private double radius;
    private Material material;

    public Moving_Sphere(
            Vec3 cen0,
            Vec3 cen1,
            double _time0,
            double _time1,
            double r,
            Material material) {
        this.center0 = cen0;
        this.center1 = cen1;
        this.time0 = _time0;
        this.time1 = _time1;
        this.radius = r;
        this.material = material;
    }

    @Override
    public boolean hit(Ray r, double t_min, double t_max, Hit_record rec) {
        Vec3 oc = r.origin().subtract(center(r.time()));
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
        Vec3 outward_normal = rec.getP().subtract(center(r.time())).divide(radius);
        rec.set_face_normal(r, outward_normal);
        rec.setMaterial(material);

        return true;
    }

    public Vec3 center(double time) {
        return center0.add(
                center1.subtract(center0)
                        .scale((time - time0) / (time1 - time0))
        );
    }
}
