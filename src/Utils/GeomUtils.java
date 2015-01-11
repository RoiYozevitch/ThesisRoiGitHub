package Utils;

import Geometry.Line3D;
import Geometry.Point3D;
import Geometry.Wall;

/**
 * Created by Roi on 1/7/2015.
 */
public class GeomUtils {


    /*
    This function returns a boolean for line plane intersection.
    The wall's coordinates MUST be coplanar.

     */
    public static boolean intersectRayWithWall(Line3D ray,
                                                 Wall wall) {
        Point3D[] PointArray = wall.getPoint3dArray();
        Point3D dS21 = PointArray[1].sub2PointsReturnNewPoint(PointArray[0]);
        Point3D dS31 = PointArray[2].sub2PointsReturnNewPoint(PointArray[0]);
        Point3D n = dS21.cross(dS31);

        // 2.
        Point3D dR = ray.getP1().sub2PointsReturnNewPoint(ray.getP2());

        double ndotdR = n.dotProduct(dR);

        if (Math.abs(ndotdR) < 1e-6f) { // Choose your tolerance
            return false;
        }

        double t = -n.dotProduct(ray.getP1().sub2PointsReturnNewPoint(PointArray[0])) / ndotdR;
        Point3D M = ray.getP1().add2PointsReturnNewPoint(dR.scale(t));

        // 3.
        Point3D dMS1 = M.sub2PointsReturnNewPoint(PointArray[0]);
        double u = dMS1.dotProduct(dS21);
        double v = dMS1.dotProduct(dS31);

        // 4.
        return (u >= 0.0f && u <= dS21.dotProduct(dS21)
                && v >= 0.0f && v <= dS31.dotProduct(dS31));
    }

}
