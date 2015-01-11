package Utils;

import GeoConvertUtils.Coordinates;
import GeoConvertUtils.Geographic;
import GeoConvertUtils.UTM;
import Geometry.Point3D;

/**
 * Created by Roi on 1/7/2015.
 */
public class GeoUtils {


    public static Point3D convertECEFtoLATLON(Point3D p1)
    {
        double a = 6378137;
        double f = 0.0034;
        double b = 6.3568e6;
        final double e = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(a, 2));
        double e2 = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(b, 2));

        double x = p1.getX();
        double y = p1.getY();
        double z = p1.getZ();

        double[] lla = { 0, 0, 0 };
        double lat, lon, height, N , theta, p;

        p = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        theta = Math.atan((z * a) / (p * b));

        lon = Math.atan(y / x);

        lat = Math.atan(((z + Math.pow(e2, 2) * b * Math.pow(Math.sin(theta), 3)) / ((p - Math.pow(e, 2) * a * Math.pow(Math.cos(theta), 3)))));
        N = a / (Math.sqrt(1 - (Math.pow(e, 2) * Math.pow(Math.sin(lat), 2))));

        double m = (p / Math.cos(lat));
        height = m - N;


        lon = lon * 180 / Math.PI;
        lat = lat * 180 / Math.PI;
        lla[0] = lat;
        lla[1] = lon;
        lla[2] = height;
        return new Point3D(lat, lon, height);
    }

    public static Point3D convertLATLONtoECEF(Point3D p)
    {return null;}

    public static Point3D convertUTMtoLATLON(Point3D p, int zone)
    {
        Coordinates utm = new UTM(zone,p.getX(), p.getY(), p.getZ(), true);
        double x = utm.toWGS84().latitude()*180/Math.PI;
        double y = utm.toWGS84().longitude()*180/Math.PI;
        return new Point3D(x, y, p.getZ());
    }


    public static Point3D convertLATLONtoUTM(Point3D p)
    {
        Geographic g = Geographic.createGeographic(p.getX(), p.getY(), p.getZ());
        UTM utm  = g.toUTM();//.toString();

        double east = utm.getEast();//   new Double(aa[3]).doubleValue();
        double north = utm.getNorth();//new Double(aa[4]).doubleValue();
        Point3D ans = new Point3D(east, north, p.getZ());
        return ans;
    }

    // This function returns the azimuth to the SV in degrees.
    public double computeAzimuthECEF(Point3D pos, Point3D sat)
    {
        return 0;
    }

    //This fnction returns the elevation to the SV in Degrees
    public double computeElevetionECEF(Point3D pos, Point3D sat)
    {return 0;}

    public static Point3D convertAnyCordToLATLON(Point3D p)
    {
        return null;
    }


}
