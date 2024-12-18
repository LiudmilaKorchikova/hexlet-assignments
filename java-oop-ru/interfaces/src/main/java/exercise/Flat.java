package exercise;

// BEGIN
public class Flat implements Home, Comparable<Home>{
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
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
        return "Квартира площадью " + this.getArea() + " метров на " + floor + " этаже";
    }
}
// END
