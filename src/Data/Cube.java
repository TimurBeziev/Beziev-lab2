package Data;

public class Cube extends Shape {
    public Cube(double length) {
        this.volume = length * length * length;
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
        return "Cube";
    }

    private double volume;
}
