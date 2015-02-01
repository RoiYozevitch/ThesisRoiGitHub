import Geometry.Building;
import Geometry.BuildingsFactory;
import Parsing.nmea.NMEAProtocolParser;
import Parsing.nmea.STMProtocolParser;
import dataStructres.NMEAPeriodicMeasurement;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roi on 1/6/2015.
 */
public class TestClass {

    public static void main(String[] args) throws IOException {


      //  BuildingFactoryTest();
        STMParserTest();




    }

    public static void STMParserTest() throws IOException {
        String NMEAFilePath = "STMsampleFile.txt";
        List<NMEAPeriodicMeasurement> meas = STMProtocolParser.parse(NMEAFilePath);
        System.out.println("End of parsing.");

    }

    public static void BuildingFactoryTest()
    {
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
