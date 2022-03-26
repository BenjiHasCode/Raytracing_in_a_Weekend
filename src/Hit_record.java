public class Hit_record {
    private Vec3 p;
    private Vec3 normal;
    private Material material;
    private double t;
    private boolean front_face;

    public Hit_record() {
        this.p = new Vec3();
        this.normal = new Vec3();
        this.t = 0;
        this.front_face = false;
    }

    public void clone(Hit_record rec) {
        this.p = rec.p;
        this.normal = rec.normal;
        this.material = rec.material;
        this.t = rec.t;
        this.front_face = rec.front_face;
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

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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
}