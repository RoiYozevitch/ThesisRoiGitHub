package Algorithm;

import GNSS.Sat;
import Geometry.Building;
import Geometry.Point3D;
import Geometry.Wall;

import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 * Those set of functions return true for a LOS sattelite and false for NLOS satelite.
 */
public class LosAlgorithm {

    public static boolean ComputeLos(Point3D pos, Wall wall, Sat sat)
    {
        return true;

    }

    public static boolean ComputeLos(Point3D pos, Building building, Sat sat)
    {

        for(Wall wall : building.getWalls())
        {
            ComputeLos(pos, wall, sat);
        }
        return true;
    }

    public static boolean ComputeLos(Point3D pos,List<Building> buildings, Sat sat)
    {

        for(Building building : buildings)
        {
            ComputeLos(pos, building, sat);
        }
        return true;
    }

    public static boolean ComputeLos(Point3D pos, List<Building> buildings, List<Sat> sattelites)
    {

        for(Sat sat : sattelites)
        {
            ComputeLos(pos, buildings, sat);
        }
        return true;
    }




}
