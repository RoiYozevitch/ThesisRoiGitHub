package Utils;

import Algorithm.LosAlgorithm;
import GNSS.Sat;
import Geometry.Building;
import Geometry.BuildingsFactory;
import Geometry.Point3D;
import Parsing.nmea.NMEAProtocolParser;
import Parsing.sirf.SirfProtocolParser;
import Parsing.stm.STMProtocolParser;
import dataStructres.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roi on 1/14/2016.
 */
public class ExampleMain {

    public static void main(String[] args) {

       // SirfExample();
        NMEA_Example();
        //STM_Example();


        }

    public static void STM_Example()
    {
        {
            {

                System.out.println("The program begins");
                String buildingFilePath = "EsriBuildingsBursaNoindentWithBoazBuilding.kml"; //Should be in src folder
                String STMFilePath = "routeABCDtwice11AM_NMEA.txt";

                BuildingsFactory fact = new BuildingsFactory();

                List<Building> buildings1 = null;
                    //Extracting building KML file to List of Buildings
                try {
                    buildings1 = fact.generateUTMBuildingListfromKMLfile(buildingFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Number of Buildings is " +buildings1.size());



                    //Parsing a NMEA file to List of GPS measurements

                    List<STMPeriodMeasurment> STM_meas = new ArrayList<STMPeriodMeasurment>();
                    STMProtocolParser parser = new STMProtocolParser();
                try {
                    STM_meas = parser.parse(STMFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //example of waht you can achieve from this class
                    for(int cnt=280; cnt < 300; cnt++)  // each cnt represent 1 second in the recording
                    {
                        System.out.println("Data for timestamp " + cnt);
                        System.out.print("Lat is " + STM_meas.get(cnt).getLat());
                        System.out.print(".  Long is " + STM_meas.get(cnt).getLon());
                        System.out.println();
                    }

                    //Example of extracting satttelite data for second 88 (e.g)
                    int cnt = 88;
                    STMPeriodMeasurment tmpSTM = STM_meas.get(cnt);

                    int numberOfSats = tmpSTM.getSVs().size();
                    for(int i=0; i<numberOfSats; i++)
                    {
                        System.out.println(tmpSTM.getSVs().get(i).getEcefXpos());
                        System.out.println(tmpSTM.getSVs().get(i).getEcefYpos());
                        System.out.println(tmpSTM.getSVs().get(i).getCorrectedPR());
                        System.out.println(tmpSTM.getSVs().get(i).getSnr());

                    }

                    }
                    //example of LOS/NLOS computation for time stamp 121;




        }
    }

    public static void NMEA_Example()
    {
        {

            System.out.println("The program begins");
            String buildingFilePath = "EsriBuildingsBursaNoindentWithBoazBuilding.kml"; //Should be in src folder
            String NMEAFilePath = "routeABCDtwice11AM_NMEA.txt";

            BuildingsFactory fact = new BuildingsFactory();

            List<Building> buildings1 = null;
            try {

                //Extracting building KML file to List of Buildings
                buildings1 = fact.generateUTMBuildingListfromKMLfile(buildingFilePath);
                System.out.println("Number of Buildings is " +buildings1.size());



                //Parsing a NMEA file to List of GPS measurements

                List<NMEAPeriodicMeasurement> NMEA_meas = new ArrayList<NMEAPeriodicMeasurement>();
                NMEAProtocolParser parser = new NMEAProtocolParser();
                NMEA_meas = parser.parse(NMEAFilePath);

                //example of waht you can achieve from this class
                int size = NMEA_meas.size();
                for(int cnt=0; cnt < 170; cnt++)  // each cnt represent 1 second in the recording
                {
                    System.out.print("Data for timestamp " + cnt);
                    System.out.print(" Lat is " + NMEA_meas.get(cnt).getLat());
                    System.out.print(".  Long is " + NMEA_meas.get(cnt).getLon());
                    System.out.print(" COG is " + NMEA_meas.get(cnt).getCogDegrees());
                    System.out.println(" speed is "+NMEA_meas.get(cnt).getSpeedKnots());
                    System.out.println();
                }

                //Example of extracting satttelite data for second 88 (e.g)
                int cnt = 88;
                NMEAPeriodicMeasurement tmpMEA = NMEA_meas.get(cnt);
                for (Integer PRN : tmpMEA.getMappedSvMeasurements().keySet()) {
                    NMEASVMeasurement sv = tmpMEA.getSvMeasurement(PRN);
                    System.out.println("Data for PRN "+ PRN+" :");
                    System.out.print("Azimuth : " + sv.getAz() + ". Elevetion is :" + sv.getEl());
                    System.out.print(".  SNR is " + sv.getSnr());
                    System.out.println();

                }
                //example of LOS/NLOS computation for time stamp 121;


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void SirfExample()
        {

        System.out.println("The program begins");
        String buildingFilePath = "EsriBuildingsBursaNoindentWithBoazBuilding.kml"; //Should be in src folder
        String SirfFilePath = "pointA_11_AM.gps";

        BuildingsFactory fact = new BuildingsFactory();

        List<Building> buildings1 = null;
        try {

            //Extracting building KML file to List of Buildings
            buildings1 = fact.generateUTMBuildingListfromKMLfile(buildingFilePath);
            System.out.println("Number of Buildings is " +buildings1.size());



            //Parsing a Sirf (NMEA) file to List of GPS measurements
            List<SirfPeriodicMeasurement> sirfMeas = new ArrayList<SirfPeriodicMeasurement>();
            SirfProtocolParser parser = new SirfProtocolParser(); //utility class
            sirfMeas = parser.parseFile(SirfFilePath);

            //example of waht you can achieve from this class
            for(int cnt=280; cnt < 300; cnt++)  // each cnt represent 1 second in the recording
            {
                System.out.println("Data for timestamp "+cnt);
                System.out.print("Lat is " + sirfMeas.get(cnt).getLat());
                System.out.print(".  Long is " + sirfMeas.get(cnt).getLon());
                System.out.print(".  ECEF y velocity is " + sirfMeas.get(cnt).getxV());
                System.out.println();
            }

            //Example of extracting satttelite data for second 88 (e.g)
            int cnt = 88;
            SirfPeriodicMeasurement tmpSirf = sirfMeas.get(cnt);
            for (Integer PRN : tmpSirf.getSatellites().keySet()) {
                SirfSVMeasurement sv = tmpSirf.getSatellites().get(PRN);
                System.out.println("Data for PRN "+ PRN+" :");
                System.out.print("Azimuth : " + sv.getAzimuth() + ". Elevetion is :" + sv.getElevation());
                System.out.print(".  X pod ECEF" + sv.getxPos() + ". Z velocity ECEF" + sv.getzV());
                System.out.println();

            }
                //example of LOS/NLOS computation for time stamp 121;
              cnt = 121;
             tmpSirf = sirfMeas.get(cnt);
            Point3D ReciverPos = new Point3D(tmpSirf.getxPos(), tmpSirf.getyPos(), tmpSirf.getzPos());
            ReciverPos = GeoUtils.convertECEFtoLATLON(ReciverPos);
            ReciverPos = GeoUtils.convertLATLONtoUTM(ReciverPos); // now the point is in UTM

            Sat sat =null;
            for (Integer Prn : tmpSirf.getSatellites().keySet()) {
                sat = tmpSirf.getSatellites().get(Prn).getSatClass(Prn);
                Boolean isLos = LosAlgorithm.ComputeLos(ReciverPos, buildings1, sat);
                System.out.println("Los State for PRN "+ Prn + " is :"+ isLos);
            }




                } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
