package Data;

public class Parallelepiped extends Shape {
    public Parallelepiped(double l, double w, double h) {
        this.height = h;
        this.width = w;
        this.length = l;
    }

    @Override
    public double getVolume() {
        return height * width * length;
    }

    @Override
    public String toString() {
        String volume = String.format("%.1f",getVolume());
        return "Parallelepiped   " + "\t\t\t" + volume;
    }

    private final double width;
    private final double height;
    private final double length;

}
