public interface Material {
    boolean scatter(Ray r_in, Hit_record rec, Color attenuation, Ray scattered);
}
