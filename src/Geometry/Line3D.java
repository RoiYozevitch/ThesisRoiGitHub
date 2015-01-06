package Geometry;

/**
 * Created by Roi on 1/7/2015.
 */
public class Line3D {

    private Point3D p1,p2;

    public Line3D(Point3D p1, Point3D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;

    }

    @Override
    public String toString() {
        return "Line3D{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }


}
