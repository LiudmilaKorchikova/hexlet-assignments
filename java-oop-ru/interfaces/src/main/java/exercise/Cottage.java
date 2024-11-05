package exercise;

// BEGIN
public class Cottage implements Home, Comparable<Home> {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {
        if (another.getArea() < this.getArea()) {
            return 1;
        }
        if (another.getArea() > this.getArea()) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }
}
// END
