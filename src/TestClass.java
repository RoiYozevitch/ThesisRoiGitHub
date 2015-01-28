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
        BuildingsFactory fact = new BuildingsFactory();
        try {
            List<Building> buildings1 = fact.generateUTMBuildingListfromKMLfile(buildingFilePath);
            System.out.println(buildings1.size());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
