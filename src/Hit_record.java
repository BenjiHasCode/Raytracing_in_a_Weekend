public class Hit_record {
    private Vec3 p;
    private Vec3 normal;
    private double t;
    private boolean front_face;

    public Hit_record(Vec3 point, Vec3 normal, double t, boolean front_face) {
        this.p = point;
        this.normal = normal;
        this.t = t;
        this.front_face = front_face;
    }

    public Hit_record() {
        this.p = new Vec3();
        this.normal = new Vec3();
        this.t = 0;
        this.front_face = false;
    }

    public void set_face_normal(Ray r, Vec3 outward_normal) {
        this.front_face = r.direction().dot(outward_normal) < 0;
        this.normal = this.front_face ? outward_normal : outward_normal.minus();
    }

    public Vec3 getP() {
        return p;
    }

    public void setP(Vec3 p) {
        this.p = p;
    }

    public Vec3 getNormal() {
        return normal;
    }

    public void setNormal(Vec3 normal) {
        this.normal = normal;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public boolean isFront_face() {
        return front_face;
    }

    public void setFront_face(boolean front_face) {
        this.front_face = front_face;
    }
}