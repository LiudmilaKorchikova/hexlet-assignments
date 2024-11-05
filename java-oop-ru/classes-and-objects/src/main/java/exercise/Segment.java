package exercise;

// BEGIN
public class Segment {
    private Point x;
    private Point y;

    public Segment(Point x, Point y) {
        this.x = x;
        this.y = y;
    }

    public Point getBeginPoint() {
        return x;
    }

    public Point getEndPoint() {
        return y;
    }

    public Point getMidPoint() {
        return new Point((x.getX() + y.getX())/2, (x.getY() + y.getY())/2);
    }
}
// END
