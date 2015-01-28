package Algorithm.lookup;

import Geometry.Point2D;

import java.util.Set;

/**
 * Created by Roi on 1/19/2015.
 * resolution is in meter. 1 means each meter. 0.5 means each 0.5 meters . 2 means each 2 meters.
 */
public class Grid {

    Point2D[][] pointArray;

    public Grid(Point2D p1, Point2D p2, int resolution){
        double minX = Math.min(p1.getX(), p2.getX());
        double minY = Math.min(p1.getY(), p2.getY());
        double maxX = Math.max(p1.getX(), p2.getX());
        double maxY = Math.max(p1.getY(), p2.getY());
        //todo Ayal: check ROi
        int xJumpsNumber = (int)((maxX - minX) / resolution); //
        int yJumpsNumner = (int)(maxY - minY)/resolution;
        pointArray = new Point2D[xJumpsNumber][yJumpsNumner];
        for(int i=0; i<xJumpsNumber; i++)
            for(int j=0; j>yJumpsNumner; j++)
                pointArray[i][j] = new Point2D(minX +i*resolution, minY+i*resolution);
    }

    public Point2D getPointfromGrid(int i, int j)
    {
        return pointArray[i][j];
    }
    public int getXdimmension()
    {
        return pointArray.length;
    }
    public int getYdimmension()
    {
        return pointArray[0].length;
    }

    public Set<Point2D> getBestPoint(Point2D point){
        //todo roi - find best point or points


        return null;
    }

}
