public class Ray {
    private Vec3 orig;
    private Vec3 dir;

    public Ray() {
        this.orig = new Vec3();
        this.dir = new Vec3();
    }

    public Ray(Vec3 origin, Vec3 direction) {
        this.orig = origin;
        this.dir = direction;
    }

    public Vec3 origin() { return this.orig; }
    public Vec3 direction() { return this.dir; }
    public void setOrigin(Vec3 origin) {
        this.orig = origin;
    }
    public void setDirection(Vec3 direction) {
        this.dir = direction;
    }

    public Vec3 at(double t) {
        return this.orig.add(this.dir.scale(t));
    }
}