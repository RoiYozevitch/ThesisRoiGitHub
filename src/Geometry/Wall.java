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
    Point3D[]  point3dArray;
    Integer wallType;

    public Point3D[] getPoint3dArray() {
        return point3dArray;
    }

    public void setPoint3dArray(Point3D[] point3dArray) {
        this.point3dArray = point3dArray;
    }

    public Integer getWallType() {
        return wallType;
    }

    public void setWallType(Integer wallType) {
        this.wallType = wallType;
    }
}



