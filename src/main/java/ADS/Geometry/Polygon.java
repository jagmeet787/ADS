package ADS.Geometry;

public class Polygon {
    public Point[];

    public Triangle(Point a, Point b, Point c) {
        this.a = a; this.b = b; this.c = c;
    }

    public double area() {
        return Math.abs(Point.cross(new Point(a.x - b.x, a.y - b.y), new Point(c.x - a.x, c.y - a.y))) / 2.0;        
    }
}
