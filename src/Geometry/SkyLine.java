package Geometry;

import dataStructres.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roi on 18/02/2015.
 */
public class SkyLine {
    Point3D pos;
    List<Pair<Double, Double>> Skys;

    public void setPos(Point3D pos) {
        this.pos = pos;
    }

    public void setCurrentSkyline()
    {
        List<Pair<Double, Double>> newList = new ArrayList<Pair<Double, Double>>();
        Pair<Double, Double> tmp = new Pair(335, -104);
        newList.add(tmp);
        tmp = new Pair(345, -105);
        newList.add(tmp);
        tmp = new Pair(357, -128);
        newList.add(tmp);
        tmp = new Pair(356, -98);
        newList.add(tmp);
        tmp = new Pair(359, -98);
        newList.add(tmp);
        tmp = new Pair(359, -120);
        newList.add(tmp);
        tmp = new Pair(1, -125);
        newList.add(tmp);
        tmp = new Pair(10, -124);
        newList.add(tmp);
        tmp = new Pair(11, -150);
        newList.add(tmp);
        tmp = new Pair(60, -165);
        newList.add(tmp);
        tmp = new Pair(62, -153);
        newList.add(tmp);
        tmp = new Pair(67, -104);
        newList.add(tmp);
        tmp = new Pair(79, -105);
        newList.add(tmp);
        tmp = new Pair(77, -154);
        newList.add(tmp);
        tmp = new Pair(92, -156);
        newList.add(tmp);
        tmp = new Pair(103, -103);
        newList.add(tmp);
        tmp = new Pair(123, -102);
        newList.add(tmp);
        tmp = new Pair(130, -150);
        newList.add(tmp);
        tmp = new Pair(203, -140);
        newList.add(tmp);
        tmp = new Pair(213, -134);
        newList.add(tmp);
        tmp = new Pair(243, -134);
        newList.add(tmp);
        tmp = new Pair(252, -135);
        newList.add(tmp);
        tmp = new Pair(251, -126);
        newList.add(tmp);
        tmp = new Pair(253, -118);
        newList.add(tmp);
        tmp = new Pair(262, -117);
        newList.add(tmp);
        tmp = new Pair(260, -102);
        newList.add(tmp);
        tmp = new Pair(270, -102);
        newList.add(tmp);
        tmp = new Pair(272, -153);
        newList.add(tmp);
        tmp = new Pair(279, -173);
        newList.add(tmp);
        tmp = new Pair(300, -166);
        newList.add(tmp);
        Skys = newList;

    }
    public void SortArrayByAzimuth()
    {

    }

    public boolean isAboveSkyLine(Pair tmp)
    {
        return false; //false means the point is below (hence NLOS)
    }


/*
 class Pair<Azimuth, Elevetion>{
     double az;
     double el;

     public Pair(double az, double el) {
         this.az = az;
         this.el = el;
     }

     public double getAz() {
         return az;
     }

     public double getEl() {
         return el;
     }

     public void setAz(double az) {
         this.az = az;
     }

     public void setEl(double el) {
         this.el = el;
     }
*/
 }
