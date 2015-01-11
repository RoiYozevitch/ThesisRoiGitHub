package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 * Collection of walls
 */
public class Building {

    String buildingName;
    List<Wall> walls;
    Integer numberOfWalls;
    double maxHeigth;

    public Building()
    {
        this.walls = new ArrayList<Wall>();
        this.numberOfWalls=0;
        this.maxHeigth = 0;
        this.buildingName = "";
    }

    public void generateBuildingFromPoint3dList(List<Point3D> buildingVertecies)
    {

        for(int i=1; i<buildingVertecies.size(); i++) {
            Wall tmp = new Wall(buildingVertecies.get(i), buildingVertecies.get(i - 1));
            walls.add(tmp);
        }
    }

    public Building(String buildingName, List<Wall> walls) {
        this.buildingName = buildingName;
        this.walls = walls;
    }

    public Building(List<Wall> walls) {
        this.walls = walls;
        this.buildingName=null;

    }

    public List<Wall> getWalls() {
        return walls;
    }



    public String getBuildingName() {
        return buildingName;
    }

    public Integer getNumberOfWalls() {
        return numberOfWalls;
    }

    public void setNumberOfWalls(Integer numberOfWalls) {
        this.numberOfWalls = numberOfWalls;
    }

    public double getMaxHeigth() {
        return maxHeigth;
    }

    public void setMaxHeigth(Integer maxHeigth) {
        this.maxHeigth = maxHeigth;
    }

    public void setMaxHeight()
    {
        double tmpMaxHeight =0;
        double max;
        for(Wall wall : walls)
        {
            max= wall.getMaxHeight();
            if(max> tmpMaxHeight)
                tmpMaxHeight = max;

        }
        this.maxHeigth = tmpMaxHeight;
    }
}
