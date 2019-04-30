package ADS.Geometry;

import Utility.Utility;

import java.util.Comparator;

// Integer
class Point {
    public int x;
    public int y;
    public Point(int _x, int _y){
        x = _x; y = _y;
    }


    public double euclideanDistance(Point p) {
        return Math.hypot(Math.abs(this.x - p.x), Math.abs(this.y - p.y));
    }

    public manhattanDistance(Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }

    /*
    * orientation of 3 Points
    * return 0 - Collinear
    * return 1 - Clockwise
    * return 2 - Counter Clockwise
    */
    public static int orientation(Point p1, Point p2, Point p3) {
        long p1x = p1.x, p1y = p1.y;
        long p2x = p2.x, p2y = p2.y;
        long p3x = p3.x, p3y = p3.y;
        long val = ((p2y - p1y) * (p3x - p2x) - (p3y - p2y) * (p2x - p1x));
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    // Given three collinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    public static boolean onSegment(Point p, Point q, Point r)
    {
        // check on x if q is in middle of p and r and same way for way
        // if yes for both then it is on segment else not
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;
        return false;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject instanceof Point) {
            Point p = (Point) anObject;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
/*
public class Point implements Comparator<Point> {
    public double x, y;
    public Point() {}
    public Point(double _x, double _y) {this.x = _x; this.y = _y;}
    // slope of line from two points
    static double slope(Point p1, Point p2) {
        if(Utility.isEqual(p1.x, p2.x)) return Double.POSITIVE_INFINITY;
        return (p2.y - p1.y)/(p2.x - p2.x);
    }
    // eucledian distance between two points
    double dist(Point p1, Point p2) {
        // hypot = Math.sqrt(x^2, y^2);
        return Math.hypot(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
    }

    public static Point rotatePoint(Point p, double theta) {
        double rad = Utility.degreeToRadin(theta);
        return new Point(p.x * Math.cos(rad) - p.y * Math.sin(rad),
                p.x*Math.sin(rad) + p.y*Math.cos(rad));
    }

    public static int orientation(Point p1, Point p2, Point p3) {
//        double slope1 = slope(p1, p2);
//        double slope2 = slope(p2, p3);
//        // collinear points
//        if (Utility.isEqual(slope1, slope2)) return 0;
//        if (slope1 > slope2) return 1; // clockwise
//        return 2; // ccw
        int val = (int)((p2.y - p1.y) * (p3.x - p2.x) - (p3.y - p2.y) * (p2.x - p1.x));
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    // Given three collinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    public static boolean onSegment(Point p, Point q, Point r)
    {
        // check on x if q is in middle of p and r and same way for way
        // if yes for both then it is on segment else not
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;
        return false;
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Point other = (Point) obj;
        return Utility.isEqual(this.x, other.x) && Utility.isEqual(this.y, other.y);
    }

    @Override
    public int compare(Point o1, Point o2) {
        if (this.equals(o2)) return 0;
        if (!Utility.isEqual(o1.x, o2.x)) {
            if (o1.x > o2.x) return 1;
            else return -1;
        }
        if (o1.y > o2.y) return 1;
        return -1;
    }

    @Override
    public String toString() {
        return "Point("  + x + ", " + y + ')';
    }
}
*/