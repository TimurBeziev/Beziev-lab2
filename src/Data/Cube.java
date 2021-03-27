package Data;

public class Cube extends Shape {
    public Cube(double length) {
        this.length = length;
    }

    @Override
    public double getVolume() {
        return length * length * length;
    }

    @Override
    public String toString() {
        return "Cube";
    }

    private final double length;
}
