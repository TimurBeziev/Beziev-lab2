package Data;

public class Sphere extends Shape {
    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return 4 * Math.PI * radius * radius * radius / 3;
    }

    @Override
    public String toString() {
        String volume = String.format("%.1f",getVolume());
        return "Sphere              " + "\t\t\t" + volume;
    }


    private final double radius;
}
