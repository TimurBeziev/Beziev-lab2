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
        return "Parallelepiped";
    }

    private final double width;
    private final double height;
    private final double length;

}
