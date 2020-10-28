package Data;

public class Sphere extends Shape {
    public Sphere(double radius) {
        this.volume = 4 * Math.PI * radius * radius * radius / 3;
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
        return "Sphere";
    }


    private double volume;
}
