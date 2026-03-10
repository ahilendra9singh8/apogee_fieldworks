package fieldworks.models;

import java.util.Arrays;

public class Triangle {

    private Point p1, p2, p3;
    public Edge getE1() {
		return e1;
	}

	public void setE1(Edge e1) {
		this.e1 = e1;
	}

	public Edge getE2() {
		return e2;
	}

	public void setE2(Edge e2) {
		this.e2 = e2;
	}

	public Edge getE3() {
		return e3;
	}

	public void setE3(Edge e3) {
		this.e3 = e3;
	}

	private Edge e1, e2, e3;
    private int id;

    public Triangle(Point p1, Point p2, Point p3, int id) {
        this.id = id;

        Point[] pointsArray = new Point[] { p1, p2, p3 };
        Arrays.sort(pointsArray);

        this.p1 = pointsArray[0];
        this.p2 = pointsArray[1];
        this.p3 = pointsArray[2];

        this.e1 = new Edge(p1, p2, this);
        this.e2 = new Edge(p2, p3, this);
        this.e3 = new Edge(p3, p1, this);
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public Point getP3() { return p3; }
    public int getId() { return id; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Triangle)) return false;
        Triangle tr = (Triangle) obj;
        return tr.p1.equals(this.p1) && tr.p2.equals(this.p2) && tr.p3.equals(this.p3);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "T." + p1 + "," + p2 + "," + p3;
    }
}

