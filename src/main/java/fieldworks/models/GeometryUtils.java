package fieldworks.models;

public class GeometryUtils {

    /**
     * Checks if a given point (pt) lies inside the circumcircle formed by three vertices (v1, v2, v3).
     *
     * @param pt the point to check
     * @param v1 first vertex of the triangle
     * @param v2 second vertex of the triangle
     * @param v3 third vertex of the triangle
     * @return true if pt lies inside the circumcircle of (v1,v2,v3), false otherwise
     */
    public static boolean inCircle(Point pt, Point v1, Point v2, Point v3) {

        double ax = v1.getX();
        double ay = v1.getY();
        double bx = v2.getX();
        double by = v2.getY();
        double cx = v3.getX();
        double cy = v3.getY();
        double dx = pt.getX();
        double dy = pt.getY();

        double ax_ = ax - dx;
        double ay_ = ay - dy;
        double bx_ = bx - dx;
        double by_ = by - dy;
        double cx_ = cx - dx;
        double cy_ = cy - dy;

        double det = ((ax_ * ax_ + ay_ * ay_) * (bx_ * cy_ - cx_ * by_)
                - (bx_ * bx_ + by_ * by_) * (ax_ * cy_ - cx_ * ay_)
                + (cx_ * cx_ + cy_ * cy_) * (ax_ * by_ - bx_ * ay_));

        if (ccw(ax, ay, bx, by, cx, cy)) {
            return det > 0;
        } else {
            return det < 0;
        }
    }

    /**
     * Checks if the sequence of three points (A, B, C) is counterclockwise.
     *
     * @param ax x-coordinate of A
     * @param ay y-coordinate of A
     * @param bx x-coordinate of B
     * @param by y-coordinate of B
     * @param cx x-coordinate of C
     * @param cy y-coordinate of C
     * @return true if counter-clockwise, false if clockwise
     */
    public static boolean ccw(double ax, double ay, double bx, double by, double cx, double cy) {
        return (bx - ax) * (cy - ay) - (cx - ax) * (by - ay) > 0;
    }

}

