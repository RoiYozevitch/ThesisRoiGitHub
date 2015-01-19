package Algorithm.lookup;

import GNSS.Sat;
import Geometry.Building;
import Geometry.Point2D;
import Utils.Relation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Roi on 1/19/2015.
 */
public class LookupTable {

    Relation<Point2D, Integer, Set<Building>> relation;

    public LookupTable(List<Building> allBuildings, Grid grid){
        relation = new Relation<Point2D, Integer, Set<Building>>();
        fillTable(allBuildings, grid);
    }

    private void fillTable(List<Building> allBuildings, Grid grid) {
        //todo roi- get all point2Ds from grid, put in relation with possible azimuths and correct buildings
        //example :
        //Point2D point = new Point2D(0, 0);
        //int az = 180;
        //relation.setValue(point, az, LosAlgorithm.findBuildings(point, az, buildings));
    }

    public Set<Building> getBuildings(Point2D me, Sat sat){
        //todo roi- use grid to find best point or points, find azimuths from points to sat, get buildings from relation.
        return null;
    }
}
