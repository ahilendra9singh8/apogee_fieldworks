package fieldworks.models;

public class Point implements Comparable<Point> {

    private double x, y, z;
    private int id;

    public Point(double x, double y, double z, int id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public int getId() { return id; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return Double.compare(p.x, x) == 0 &&
               Double.compare(p.y, y) == 0 &&
               Double.compare(p.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Double.valueOf(this.x).hashCode();
        hash = 71 * hash + Double.valueOf(this.y).hashCode();
        hash = 71 * hash + Double.valueOf(this.z).hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "P" + id + " (" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int compareTo(Point o) {
        return Integer.compare(this.id, o.id);
    }
}
