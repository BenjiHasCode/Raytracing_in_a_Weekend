public class Random {
    public static double getDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}