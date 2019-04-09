package ADS.Geometry;

import Utility.Utility;

public class Line {
    public double a, b, c;
    public Line(){this(0, 0, 0);};
    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public static Line pointsToLine(Point p1, Point p2) {
        Line l = new Line();
        if (p1.x == p2.x) {
            l.a = 1.0; l.b = 0.0; l.c = -p1.x;
        } else {
            l.a = -(double) (p1.y - p2.y) / (p1.x - p2.x);
            l.b = 1.0;
            l.c = -(double)(l.a * p1.x) - p1.y;
        }
        return l;
    }

    public static boolean areParallel(Line l1, Line l2) {
        return Utility.isEqual(l1.a, l2.a) && Utility.isEqual(l1.b, l2.b);
    }

    public static boolean areSame(Line l1, Line l2) {
        return areParallel(l1, l2) && Utility.isEqual(l1.c, l2.c);
    }

    // if lines are parralel then they don't intersect
    public static Point areIntersect(Line l1, Line l2) {
        if(areParallel(l1, l2)) return null;
        Point p = new Point();
        p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
        if(Math.abs(l1.b) > Utility.EPS) p.y = -(l1.a * p.x + l1.c);
        else 					 p.y = -(l2.a * p.x + l2.c);
        return p;
    }

    @Override
    public String toString() {
        return "Line{" + a + "x + " +  b + "y + " + c + '}';
    }

    public static void main(String[] args) {
        Line l1 = pointsToLine(new Point(1,0), new Point(1,5));
        System.out.println(l1);
        Line l = pointsToLine(new Point(0, 1), new Point(4, 5));
        System.out.println(l);
        System.out.println(areIntersect(l1, l));
    }
}
