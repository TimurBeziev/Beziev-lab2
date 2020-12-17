package Data;

public class Cylinder extends Shape {
    public Cylinder(double r, double h) {
        this.volume = Math.PI * r * r * h;
    }

    @Override
    public double getVolume() {
        return this.volume;
    }

    @Override
    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Cylinder";
    }

    private double volume;
}
