public class Camera {
    private Vec3 origin;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 lower_left_corner;
    private Vec3 u, v, w;
    private double lens_radius;

    public Camera(
            Vec3 lookFrom,
            Vec3 lookAt,
            Vec3 vup,
            double vfov,
            double aspect_ratio,
            double aperture,
            double focus_dist) {
        double theta = Math.toRadians(vfov);
        double h = Math.tan(theta/2);
        double viewport_height = 2.0 * h;
        double viewport_width = aspect_ratio * viewport_height;

        this.w = lookFrom.subtract(lookAt).unit_vector();
        this.u = vup.cross(w).unit_vector();
        this.v = w.cross(u);

        this.origin = lookFrom;
        this.horizontal = u.scale(focus_dist * viewport_width);
        this.vertical = v.scale(focus_dist * viewport_height);
        this.lower_left_corner = origin
                .subtract(horizontal.divide(2))
                .subtract(vertical.divide(2))
                .subtract(w.scale(focus_dist));

        this.lens_radius = aperture / 2;
    }

    public Ray get_ray(double s, double t) {
        Vec3 rd = Vec3.random_in_unit_disk().scale(lens_radius);
        Vec3 offset = u.scale(rd.getX())
                .add(v.scale(rd.getY()));

        Vec3 direction = lower_left_corner
                .add(horizontal.scale(s))
                .add(vertical.scale(t))
                .subtract(origin)
                .subtract(offset);
        return new Ray(this.origin.add(offset), direction);
    }

    public Vec3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vec3 origin) {
        this.origin = origin;
    }

    public Vec3 getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Vec3 horizontal) {
        this.horizontal = horizontal;
    }

    public Vec3 getVertical() {
        return vertical;
    }

    public void setVertical(Vec3 vertical) {
        this.vertical = vertical;
    }

    public Vec3 getLower_left_corner() {
        return lower_left_corner;
    }

    public void setLower_left_corner(Vec3 lower_left_corner) {
        this.lower_left_corner = lower_left_corner;
    }
}