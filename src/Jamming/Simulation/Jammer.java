package Jamming.Simulation;

import Geometry.Point3D;

/**
 * Created by Roi on 8/23/2015.
 */

enum Pattern {OMNI, ANGLEWISE, OTHER};
enum AttenutationFunction{exp, linear, xminus1};
public class Jammer {
    Point3D jamPos;
    Double jamRadiusInMeter;
    Pattern pat;
    AttenutationFunction attfunc = null;

    public Jammer(Point3D jamPos, Double jamRadiusInMeter, Pattern pat) {
        this.jamPos = jamPos;
        this.jamRadiusInMeter = jamRadiusInMeter;
        this.pat = pat;

    }

    public void setAttfunc(AttenutationFunction attfunc) {
        this.attfunc = attfunc;
    }

    public Double computeAtt(double dist, double maxSNR) {
        if (attfunc == null) {
            double ratio = (this.jamRadiusInMeter - dist) / this.jamRadiusInMeter;
            double snrAtt = ratio * maxSNR;
            if (ratio> 0.95)
                return null;
            else
            return maxSNR-snrAtt;
        }
        return maxSNR;

    }

    public Double singlePointAttenuation(Point3D pos, double maxSNR) {
        double dist = this.jamPos.distance(pos);
        if (dist >= this.jamRadiusInMeter)
            return maxSNR;
        else
            return this.computeAtt(dist, maxSNR);
    }
}
