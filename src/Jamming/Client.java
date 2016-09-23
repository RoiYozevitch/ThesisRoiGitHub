package Jamming;

import Geometry.Point2D;

/**
 * Created by Roi on 9/23/2016.
 */
public class Client {
    Point2D loc;
    double recivedSNR;
    double maximumSNR;
    double COG,SOG;




    public Client(Point2D loc, double recivedSNR, double maximumSNR) {
        this.loc = loc;
        this.recivedSNR = recivedSNR;
        this.maximumSNR = maximumSNR;
    }


    public Client(double x1, double y1, double x2, double y2)
    {
        this.loc=null;
    }
    public void moveByCOGSOG()
    {}

    public Point2D getLoc() {
        return loc;
    }

    public void setLoc(Point2D loc) {
        this.loc = loc;
    }

    public double getRecivedSNR() {
        return recivedSNR;
    }

    public void setRecivedSNR(double recivedSNR) {
        this.recivedSNR = recivedSNR;
    }

    public double getMaximumSNR() {
        return maximumSNR;
    }

    public void setMaximumSNR(double maximumSNR) {
        this.maximumSNR = maximumSNR;
    }

    public void moveClient(double dx, double dy)
    {
        this.loc.offset(dx, dy);
    }

    public void senseNoise(JammerParticle jammer) {

        this.recivedSNR = jammer.computeSNRofReceiver(this);


    }
}

