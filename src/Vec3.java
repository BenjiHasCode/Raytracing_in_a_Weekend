public class Vec3 {
    private double x;
    private double y;
    private double z;

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

    public static Vec3 random_vec3() {
        return new Vec3(Math.random(), Math.random(), Math.random());
    }

    public static Vec3 random_vec3(double min, double max) {
        return new Vec3(Random.getDouble(min, max), Random.getDouble(min, max), Random.getDouble(min, max));
    }

    public static Vec3 random_in_unit_sphere() {
        while (true) {
            Vec3 p = random_vec3(-1, 1);
            if (!(p.length_squared() >= 1)) {
                return p;
            }
        }
    }

    public static Vec3 random_unit_vector() {
        return random_in_unit_sphere().unit_vector();
    }


    public String toString() {
        return String.format("%f %f %f", this.x, this.y, this.z);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}