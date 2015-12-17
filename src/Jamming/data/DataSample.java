package Jamming.data;

import Geometry.Point3D;

/**
 * Created by Roi on 8/4/2015.
 */
public class DataSample {



    private Point3D pos;
    private long COG;
    private long SOG;
    private long time;
    private double SNREventProb =0;
    private double maxSNR;
    private long imei;

    public DataSample(Point3D pos, long COG, long SOG, long time, double maxSNR, long imei) {
        this.pos = pos;
        this.COG = COG;
        this.SOG = SOG;
        this.time = time;
        this.maxSNR = maxSNR;
        this.imei = imei;
    }

    public double getSNREventProb() {
        return SNREventProb;
    }

    public void setSNREventProb(double SNREventProb) {
        this.SNREventProb = SNREventProb;
    }



    public Point3D getPos() {
        return pos;
    }

    public long getTime() {
        return time;
    }

    public double getMaxSNR() {
        return maxSNR;
    }

    public long getImei() {
        return imei;
    }

    public long getCOG() {
        return COG;
    }

    public long getSOG() {
        return SOG;
    }
}
