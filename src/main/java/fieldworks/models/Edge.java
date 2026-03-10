package fieldworks.models;

public class Edge {

    private Point p1, p2;
    private Triangle triangle;

    public Edge(Point p1, Point p2, Triangle triangle) {
        this.triangle = triangle;
        if (p1.getId() < p2.getId()) {
            this.p1 = p1;
            this.p2 = p2;
        } else if (p1.getId() > p2.getId()) {
            this.p1 = p2;
            this.p2 = p1;
        } else {
            throw new IllegalArgumentException("Duplicate Point IDs: " + p1 + ", " + p2);
        }
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public Triangle getTriangle() { return triangle; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;
        Edge other = (Edge) obj;
        return p1.equals(other.p1) && p2.equals(other.p2);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + p1.hashCode();
        hash = 71 * hash + p2.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "E." + p1 + "," + p2 + "(" + triangle + ")";
    }
}
