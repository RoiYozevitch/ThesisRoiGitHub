package Geometry;

/**
 * Created by Roi on 1/7/2015.
 */
public class Point2D {
    private double x,y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Point2D p)
    {
        double ans = Math.sqrt(Math.pow(this.x-p.getX(),2)+Math.pow(this.y-p.getY(),2));
        return ans;
    }

    public double angle2D(Point2D secondPoint)
    {
        if((secondPoint.getX() > this.x)) {//above 0 to 180 degrees

            return (Math.atan2((secondPoint.getX() - this.x), (this.y - secondPoint.getY())) * 180 / Math.PI);

        }
        else if((secondPoint.getX() < this.x)) {//above 180 degrees to 360/0

            return 360 - (Math.atan2((this.x - secondPoint.getX()), (this.y - secondPoint.getY())) * 180 / Math.PI);

        }//End if((secondPoint.x > firstPoint.x) && (secondPoint.y <= firstPoint.y))

        return Math.atan2(0 ,0);

    }//End public float getAngleFromPoint(Point firstPoint, Point secondPoint)


    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2D)) return false;

        Point2D point2D = (Point2D) o;

        if (Double.compare(point2D.x, x) != 0) return false;
        if (Double.compare(point2D.y, y) != 0) return false;

        return true;
    }

    public void movePoint(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

