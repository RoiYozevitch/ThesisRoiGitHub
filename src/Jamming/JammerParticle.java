package Jamming;

import Geometry.Point2D;

/**
 * Created by Roi on 9/23/2016.
 */


public class JammerParticle {

    double JamPowe; //
    Point2D jamLoc;
    double minmalDistancetoLooseFix;
    double maximalDistancetoSense;
    double errorFigure=0;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getJamPowe() {
        return JamPowe;
    }

    public double getMaximalDistancetoSense() {
        return maximalDistancetoSense;
    }

    public double getMinmalDistancetoLooseFix() {
        return minmalDistancetoLooseFix;
    }

    double weight;


    public JammerParticle(double x1, double y1, double x2, double y)
    {}


    public void EvalJammer(ClientList list)
    {}

    public void Update(JammerParticle tmp)
    {
        JamPowe = tmp.getJamPowe();
        this.jamLoc = tmp.getJamLoc();
        this.minmalDistancetoLooseFix = tmp.getMinmalDistancetoLooseFix();
        this.maximalDistancetoSense = tmp.getMaximalDistancetoSense();
        this.weight = 1;
    }

    public JammerParticle(JammerParticle tmp)
    {
        JamPowe = tmp.getJamPowe();
        this.jamLoc = tmp.getJamLoc();
        this.minmalDistancetoLooseFix = tmp.getMinmalDistancetoLooseFix();
        this.maximalDistancetoSense = tmp.getMaximalDistancetoSense();
    }


    public JammerParticle(double jamPowe, Point2D jamLoc, double minmalDistancetoLooseFix) {
        JamPowe = jamPowe;
        this.jamLoc = jamLoc;
        this.minmalDistancetoLooseFix = minmalDistancetoLooseFix;
    }

    public Point2D getJamLoc() {
        return jamLoc;
    }

    public double computeSNRofReceiver(Client reciever)
    {
        double dist = this.jamLoc.distance(reciever.getLoc();
        if(dist<this.minmalDistancetoLooseFix)
            return -1;
        else if(dist>this.maximalDistancetoSense)
        {
            return reciever.maximumSNR+errorFigure;
            //todo Roi fill it
        }
        else
            return 8;

    }

    public void evalWeight(ClientList receiverList) {

        this.weight = 8;
    }

    public void PrintResults(JammerParticle realJammer) {
        double dist = this.jamLoc.distance(realJammer.getJamLoc());
        System.out.println("Error[m]:"+dist+". Weight:"+this.weight);
    }
}

