package Geometry;

/**
 * Created by Roi on 1/6/2015.
 */
public class Point3D  extends Point2D{


    private  double z;

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;

    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;

        Point3D point3D = (Point3D) o;

        if (Double.compare(point3D.z, z) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                " x="+ super.getX()+
                " y=" + super.getY()+
                " z=" + z +
                '}';
    }
}

