package Geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 * Collection of walls
 */
public class Building {

    String buildingName;
    List<Wall> walls;
    double maxHeigth;

    Building(List<Point3D> buildingVertecies)
    {
        this.walls = new ArrayList<Wall>();
        this.maxHeigth = 0;
        this.buildingName = "";
        init(buildingVertecies);
    }

    private void generateBuildingFromPoint3dList(List<Point3D> buildingVertecies)
    {
        for(int i=1; i<buildingVertecies.size(); i++) {
            Wall tmp = new Wall(buildingVertecies.get(i), buildingVertecies.get(i - 1));
            walls.add(tmp);
        }
    }


    public boolean isContain(Point3D pos) // if the point lies in the building, return true;
    {
        return false;
    }
    private void init(List<Point3D> buildingVertecies){
        generateBuildingFromPoint3dList(buildingVertecies);
        setMaxHeight();
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Integer getNumberOfWalls() {
        return walls.size();
    }

    public double getMaxHeight() {
        return maxHeigth;
    }

    private void setMaxHeight()
    {
        maxHeigth = Collections.max(walls, new Comparator<Wall>() {
            @Override
            public int compare(Wall o1, Wall o2) {
                return Double.compare(o1.getMaxHeight(), o2.getMaxHeight());
            }
        }).getMaxHeight();
    }
}
