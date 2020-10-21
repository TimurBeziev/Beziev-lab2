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
        return "Sphere";
    }


    private final double radius;
}
