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
        String volume = String.format("%.1f",getVolume());
        return "Cube                 " + "\t\t\t" + volume;
    }

    private final double length;
}
