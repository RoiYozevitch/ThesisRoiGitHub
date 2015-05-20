package Parsing;

import Algorithm.LosAlgorithm;
import Algorithm.PseudoRangeComp;
import GNSS.Sat;
import Geometry.Building;
import Geometry.BuildingsFactory;
import Geometry.Point3D;
import Parsing.sirf.SirfCsvWriter;
import Parsing.sirf.SirfMLCsvWriter;
import Parsing.sirf.SirfProtocolParser;
import Parsing.stm.STMProtocolParser;
import Parsing.stm.STMcsvWriter;
import Utils.KMLgenerator;
import dataStructres.*;
import Parsing.nmea.NMEAProtocolParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by Roi on 22/02/2015.
 */
public class parsingMain {

    public static void main(String[] args) throws Exception {

       SirfParsingML();
      //  TestLosNlosAlgorithm();
        //PseudoRangeCompute();
    /*    try {
            TestLosNlosAlgorithm();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }

    private static void TestLosNlosAlgorithm() throws Exception {

        String BuildingPath = "SingleWall2.kml";
        System.out.println("The program begins");
        BuildingsFactory fact = new BuildingsFactory();
        List<Building> buildings1 = null;

        buildings1 = fact.generateUTMBuildingListfromKMLfile(BuildingPath);
        System.out.println("Number of Buildings is " + buildings1.size());

        Point3D tmpPointInUTM =  new Point3D(670123.4, 3551171.47, 4);
        Sat tmpSat = new Sat(248, 79, 1);
        String KmlFilePath ="test.kml";
        SirfSVMeasurement sirfMeas= new SirfSVMeasurement();

        KMLgenerator.generateSatLinesFromSat(tmpSat, tmpPointInUTM, KmlFilePath);

            boolean los = LosAlgorithm.ComputeLos(tmpPointInUTM, buildings1, tmpSat);
            System.out.println("Azimut:" + tmpSat.getAzimuth() + ". Elev:" + tmpSat.getElevetion() + " status of computation is " + los);

            //tmpSat.setElevetion(tmpSat.getElevetion()+5);



    }


    public static void SirfParsingML()  {

        String[] SirfFilePath={"POINT_A_STATIONARY.txt","POINT_B_STATIONARY.txt","POINT_C_STATIONARY.txt","POINT_D_STATIONARY.txt"};

       /* String SirfFilePathA = ;
        String SirfFilePathC = "POINT_C_STATIONARY.txt";
        String SirfFilePathD = "POINT_D_STATIONARY.txt";
        String SirfFileRouteABCD  = "route_abcd_twice.txt";
*/


        String[] OutputFile ={"PointA_new2","PointB_new2","PointC_new2","PointD_New2"};

        String outputFileRouteSirf = "route_abcd_twice_ML_ClassificationWrong.txt";

        String buildingFilePath = "EsriBuildingsBursaNoindentWithBoazBuilding.kml";
        System.out.println("The program begins");

        Point3D[] receiverPosition = new Point3D[4];
        receiverPosition[0] = new Point3D(670114.15, 3551135.3, 1.8); //according to Boaz file bursa-a-d.kml point a
        receiverPosition[1] = new Point3D(670126.5, 3551136.25, 1.8); //according to Boaz file bursa-a-d.kml point b
        receiverPosition[2] = new Point3D(670123.4, 3551171.47, 1.8); //according to Boaz file bursa-a-d.kml point c
        receiverPosition[3] =  new Point3D(670111.6, 3551170.62, 1.8); //according to Boaz file bursa-a-d.km point d

        BuildingsFactory fact = new BuildingsFactory();

        List<Building> buildings1 = null;
        try {
            buildings1 = fact.generateUTMBuildingListfromKMLfile(buildingFilePath);
            System.out.println(buildings1.size());
            String KmlFilePath = "pointC.kml";
            SirfProtocolParser parser = new SirfProtocolParser();
            for(int cnt=0; cnt<4; cnt++) {
                List<SirfPeriodicMeasurement> sirfMeas = parser.parseFile(SirfFilePath[cnt]);
              //  KMLgenerator.generateSatLinesFromSirfSvMesserment(sirfMeas, receiverPosition[2], KmlFilePath);


                sirfMeas.get(0).computeCorrectPseudoRangeForAllSats();
                sirfMeas.get(1).computeCorrectPseudoRangeForAllSats();
                sirfMeas.get(1).setExtremeSnrValuesForAllSats();
                for (int i = 2; i < sirfMeas.size(); i++) {
                    sirfMeas.get(i).computeCorrectPseudoRangeForAllSats();
                    sirfMeas.get(i).computePseudoRangeResidualsForAllSats();
                    sirfMeas.get(i).computePreviousValues(sirfMeas.get(i - 1), sirfMeas.get(i - 2));

                }
                parser.ComputeLosNLOSFromStaticPoint(sirfMeas, buildings1, receiverPosition[cnt]);

                SirfMLCsvWriter.printToFileSpecificValues(sirfMeas, OutputFile[cnt]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public static void PseudoRangeCompute()
    {
        String StmFilePath = "route_ABCD_STM_1Hz_twice.txt";
        STMProtocolParser stmParser = new STMProtocolParser();
        List<STMPeriodMeasurment> stmMeas = null;
        try {
            stmMeas = stmParser.parse(StmFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //todo roi: learn this code.
      stmMeas = stmParser.ClearBadSattelitesOnlyGps(stmMeas);

        for (STMPeriodMeasurment meas : stmMeas) {
            Map<Integer, STMSVMeasurement> mappedSvMeasurements = meas.getMappedSvMeasurements();
            for (Integer key : mappedSvMeasurements.keySet()) {
                STMSVMeasurement stmSvMeasurement = mappedSvMeasurements.get(key);

            }
        }

        System.out.println(" number of meas is " + stmMeas.size());

            PseudoRangeComp.computePseudoRangeFromStmMessurment(stmMeas.get(1));



        System.out.println(" end of main");




    }

    private static void main2() throws IOException, ParseException {
        //String sirfFilePath  = "POINT_A_STATIONARY.txt";
        String nmeaDilePath = "";
        String StmFilePath = "route_ABCD_STM_1Hz_twice.txt";

        // String sirfCsvFilePath  = "PointA_ParsedCsv.csv";
        String nmeaCsvFilePath = "";
        String StmCsvFilePath = "route_ABCD_STM_1Hz_twice";


        STMProtocolParser stmParser = new STMProtocolParser();
        List<STMPeriodMeasurment> stmMeas = stmParser.parse(StmFilePath);
        //todo roi: learn this code.
        for (STMPeriodMeasurment meas : stmMeas){
            Map<Integer, STMSVMeasurement> mappedSvMeasurements =meas.getMappedSvMeasurements();
            for (Integer key : mappedSvMeasurements.keySet()){
                STMSVMeasurement stmSvMeasurement =mappedSvMeasurements.get(key);
                double correctedPR = stmSvMeasurement.getCorrectedPR();
                // System.out.println(key + "\t " + correctedPR);
            }
        }
      //  STMcsvWriter.printStmToFile(stmMeas, StmCsvFilePath );
        // NMEAProtocolParser nmeaParser  = new NMEAProtocolParser();
        // List<NMEAPeriodicMeasurement>  nmeaMeas =  nmeaParser.parse(nmeaDilePath);

        //  SirfProtocolParser sirfParser = new SirfProtocolParser();
        //  List<SirfPeriodicMeasurement> sirfMeas  = sirfParser.parseFile(sirfFilePath);
        //  SirfCsvWriter.printToFile(sirfMeas, sirfCsvFilePath);
        System.out.println("Parsed O.k");


    }
}
