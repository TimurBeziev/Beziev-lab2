package Data;

public class Cylinder extends Shape {
    public Cylinder(double r, double h) {
        this.radius = r;
        this.height = h;
    }

    @Override
    public double getVolume() {
        return Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return "Cylinder";
    }

    private final double radius;
    private final double height;
}
