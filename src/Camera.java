public class Camera {
    private double aspect_ratio;
    private double viewport_height;
    private double viewport_width;
    private double focal_length;
    private Vec3 origin;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 lower_left_corner;

    public Camera() {
        this.aspect_ratio = 16.0 / 9.0;
        this.viewport_height = 2.0;
        this.viewport_width = this.aspect_ratio * this.viewport_height;
        this.focal_length = 1.0;
        this.origin = new Vec3(0, 0, 0);
        this.horizontal = new Vec3(this.viewport_width, 0.0, 0.0);
        this.vertical = new Vec3(0.0, this.viewport_height, 0.0);
        this.lower_left_corner = this.origin
                .subtract(this.horizontal.divide(2))
                .subtract(this.vertical.divide(2))
                .subtract(new Vec3(0, 0, this.focal_length));

    }

    public Camera(double aspect_ratio, double viewport_height, double viewport_width, double focal_length, Vec3 origin, Vec3 horizontal, Vec3 vertical, Vec3 lower_left_corner) {
        this.aspect_ratio = aspect_ratio;
        this.viewport_height = viewport_height;
        this.viewport_width = viewport_width;
        this.focal_length = focal_length;
        this.origin = origin;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.lower_left_corner = lower_left_corner;
    }

    public Ray get_ray(double u, double v) {
        Vec3 direction = lower_left_corner
                .add(horizontal.scale(u))
                .add(vertical.scale(v))
                .subtract(origin);
        return new Ray(this.origin, direction);
    }

    public double getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(double aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public double getViewport_height() {
        return viewport_height;
    }

    public void setViewport_height(double viewport_height) {
        this.viewport_height = viewport_height;
    }

    public double getViewport_width() {
        return viewport_width;
    }

    public void setViewport_width(double viewport_width) {
        this.viewport_width = viewport_width;
    }

    public double getFocal_length() {
        return focal_length;
    }

    public void setFocal_length(double focal_length) {
        this.focal_length = focal_length;
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