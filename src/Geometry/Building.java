package Geometry;

import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 * Collection of walls
 */
public class Building {

    String buildingName;
    List<Wall> walls;
    Integer numberOfWalls;
    Integer maxHeigth;


    public Building(String buildingName, List<Wall> walls) {
        this.buildingName = buildingName;
        this.walls = walls;
    }

    public Building(List<Wall> walls) {
        this.walls = walls;
        this.buildingName=null;

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

    public Integer getMaxHeigth() {
        return maxHeigth;
    }

    public void setMaxHeigth(Integer maxHeigth) {
        this.maxHeigth = maxHeigth;
    }
}
