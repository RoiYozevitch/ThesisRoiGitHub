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

    public Line3D(Line3D l) {
        this.p1 = l.getP1();
        this.p2 = l.getP2();
    }

    public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;

    }

    public double dx()
    {
        return p1.getX()-p2.getX();
    }

    public double dy()
    {
        return p1.getY()-p2.getY();
    }
    public double dz()
    {
        return p1.getZ()-p2.getZ();
    }

    @Override
    public String toString() {
        return "Line3D{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line3D)) return false;

        Line3D line3D = (Line3D) o;

        if (p1 != null ? !p1.equals(line3D.p1) : line3D.p1 != null) return false;
        if (p2 != null ? !p2.equals(line3D.p2) : line3D.p2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = p1 != null ? p1.hashCode() : 0;
        result = 31 * result + (p2 != null ? p2.hashCode() : 0);
        return result;
    }

    public Point3D getCenterPoint()
    {
        return new Point3D((this.p1.getX()+this.p2.getX())/2, (this.p1.getY()+this.p2.getY())/2, (this.p1.getZ()+this.p2.getZ())/2);
    }

    /** this method checks if the Point3D p falls on the this (Line3D) */
    public boolean line3DContains(Point3D p) {return false;}

    public Point3D intersectionPoint(Line3D l)
    {
        return null;
    }
    public double distanceFromPoint(Point3D p)
    {return 0;}

}
