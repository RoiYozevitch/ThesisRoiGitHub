package Algorithm.lookup;

import Geometry.Point2D;

import java.util.Set;

/**
 * Created by Roi on 1/19/2015.
 */
public class Grid {

    Point2D[][] pointArray;

    public Grid(Point2D p1, Point2D p2, int resolution){
        double minX = Math.min(p1.getX(), p2.getX());
        double minY = Math.min(p1.getY(), p2.getY());
        double maxX = Math.max(p1.getX(), p2.getX());
        double maxY = Math.max(p1.getY(), p2.getY());
        //todo roi: init pointarray with size with correct resolution and put in each [i,j] the point2d
    }

    public Set<Point2D> getBestPoint(Point2D point){
        //todo roi - find best point or points
        return null;
    }

}
