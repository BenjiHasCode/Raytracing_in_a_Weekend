public class Ray {
    private Vec3 orig;
    private Vec3 dir;
    private double time;

    public Ray() {
        this.orig = new Vec3();
        this.dir = new Vec3();
    }

    public Ray(Vec3 origin, Vec3 direction, double time) {
        this.orig = origin;
        this.dir = direction;
        this.time = 0.0;
    }

    public void clone(Ray r) {
        this.orig = r.orig;
        this.dir = r.dir;
    }

    public Vec3 origin() { return this.orig; }
    public Vec3 direction() { return this.dir; }
    public double time() { return this.time; }

    public Vec3 at(double t) {
        return this.orig.add(this.dir.scale(t));
    }
}