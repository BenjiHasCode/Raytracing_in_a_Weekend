import util.Random;

public class Vec3 {
    double x;
    double y;
    double z;

    public Vec3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vec3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public void clone(Vec3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vec3 minus() {
        return new Vec3(-this.x, -this.y, -this.z);
    }

    public Vec3 add(Vec3 vec3) {
        return new Vec3(this.x + vec3.x, this.y + vec3.y, this.z + vec3.z);
    }

    public Vec3 subtract(Vec3 vec3) {
        return new Vec3(this.x - vec3.x, this.y - vec3.y, this.z - vec3.z);
    }

    public Vec3 multiply(Vec3 vec3) {
        return new Vec3(this.x * vec3.x, this.y * vec3.y, this.z * vec3.z);
    }

    public Vec3 scale(double factor) {
        return new Vec3(this.x * factor, this.y * factor, this.z * factor);
    }

    public Vec3 divide(double factor) {
        return this.scale(1.0 / factor);
    }

    public double length_squared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double length() {
        return Math.sqrt(this.length_squared());
    }

    public double dot(Vec3 vec3) {
        return this.x * vec3.x + this.y * vec3.y + this.z * vec3.z;
    }

    public Vec3 cross(Vec3 vec3) {
        return new Vec3(
                this.y * vec3.z - this.z * vec3.y,
                this.z * vec3.x - this.x * vec3.z,
                this.x * vec3.y - this.y * vec3.x);
    }

    public Vec3 unit_vector() {
        return this.divide(this.length());
    }

    public boolean near_zero() {
        double s = 1e-8;
        return (Math.abs(x) < s) && (Math.abs(y) < s) && (Math.abs(z) < s);
    }

    public Vec3 reflect(/*Vec3 v, */Vec3 n) {
        // v - 2*dot(v,n)*n
        return this.subtract(n.scale(2*this.dot(n)));
    }

    public Vec3 refract(Vec3 n, double etai_over_etat) {
        double cos_theta = Math.min(-this.dot(n), 1.0);
        Vec3 r_out_perp = this
                .add(n.scale(cos_theta))
                .scale(etai_over_etat);
        Vec3 r_out_parallel = n.scale(-Math.sqrt(Math.abs(1.0 - r_out_perp.length_squared())));

        return r_out_perp.add(r_out_parallel);
    }

    public static Vec3 random() {
        return new Vec3(Math.random(), Math.random(), Math.random());
    }

    public static Vec3 random(double min, double max) {
        return new Vec3(Random.getDouble(min, max), Random.getDouble(min, max), Random.getDouble(min, max));
    }

    public static Vec3 random_in_unit_sphere() {
        while (true) {
            Vec3 p = random(-1, 1);
            if (!(p.length_squared() >= 1)) {
                return p;
            }
        }
    }

    public static Vec3 random_unit_vector() {
        return random_in_unit_sphere().unit_vector();
    }

    public static Vec3 random_in_hemisphere(Vec3 normal) {
        Vec3 in_unit_sphere = random_in_unit_sphere();
        return in_unit_sphere.dot(normal) > 0.0 ? in_unit_sphere : in_unit_sphere.minus();
    }

    public static Vec3 random_in_unit_disk() {
        while (true) {
            Vec3 p = new Vec3(Random.getDouble(-1, 1), Random.getDouble(-1, 1), 0);

            if (p.length_squared() < 1)
                return p;
        }
    }

    public String toString() {
        return String.format("%f %f %f", this.x, this.y, this.z);
    }
}