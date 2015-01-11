import Geometry.Building;
import Geometry.BuildingsFactory;

import java.util.List;

/**
 * Created by Roi on 1/6/2015.
 */
public class TestClass {

    public static void main(String[] args) {

        String buildingFilePath = "bursa_mapping_v0.3.kml";
        System.out.println("The program begins");
        List<Building> buildings;
        BuildingsFactory fact = new BuildingsFactory();
        fact.generateUTMBuildingListfromKMLfile(buildingFilePath, 9);
        buildings = fact.getBuildingList();
        System.out.println(buildings.size());



    }
}
