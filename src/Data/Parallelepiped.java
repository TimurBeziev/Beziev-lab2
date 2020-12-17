package Data;

public class Parallelepiped extends Shape {
    public Parallelepiped(double l, double w, double h) {
        this.volume = h * w * l;
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
        return "Parallelepiped";
    }

    private double volume;

}
