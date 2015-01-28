package Geometry;

import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 * This class represents a surface (wall).
 * The main restriction is that the entire wall "sits" in a signle plane.
 * Since the wall is not restricted to 2.5D, we represent ALL of the wall's vertices.
 *
 * wall Type :
 * 1- square;
 * 2 - Polygon
 *
 */

public class Wall {
    public enum wallType{square, polygon};//todo Ayal: chaeck Roi
    public wallType typeOfwall;

    double maxHeight;
    Line3D wallAsLine; //this Line3D represent a wall relative to ground.
    Point3D[] point3dArray;

    public Line3D getWallAsLine() {
        return wallAsLine;
    }

    public void setWallAsLine(Line3D wallAsLine) {
        this.wallAsLine = wallAsLine;
    }

    public Wall(Point3D a, Point3D b)
    {
        wallAsLine = new Line3D(a, b);
        typeOfwall=wallType.square;
        this.maxHeight = Math.max(a.getZ(), b.getZ());

    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public wallType  getWallType() {
        return typeOfwall;
    }


    public Point3D[] getPoint3dArray() {
        return point3dArray;
    }
}



