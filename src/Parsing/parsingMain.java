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

    public static void main(String[] args) throws IOException, ParseException {

       SirfParsingML();
        //PseudoRangeCompute();
    /*    try {
            TestLosNlosAlgorithm();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }

    private static void TestLosNlosAlgorithm() throws Exception {

        String BuildingPath = "SingleWall.kml";
        System.out.println("The program begins");
        BuildingsFactory fact = new BuildingsFactory();
        List<Building> buildings1 = null;

        buildings1 = fact.generateUTMBuildingListfromKMLfile(BuildingPath);
        System.out.println("Number of Buildings is " + buildings1.size());

        Point3D tmpPointInUTM = new Point3D(670053, 3551191, 3);
        Sat tmpSat = new Sat(30, 25, 0);
        for (int i = 0; i < 72; i++)
        {

            boolean los = LosAlgorithm.ComputeLos(tmpPointInUTM, buildings1, tmpSat);
        System.out.println("Azimut:" + tmpSat.getAzimuth() + ". Elev:" + tmpSat.getElevetion() + " status of computation is " + los);
            tmpSat.setAzimuth(tmpSat.getAzimuth()+1);
            //tmpSat.setElevetion(tmpSat.getElevetion()+5);
    }


    }


    public static void SirfParsingML()  {

        String SirfFilePath = "POINT_B_STATIONARY.txt";
        String outputFile = "POINT_B_STATIONARY_ML_New";
        String buildingFilePath = "bursa_mapping_v0.3.kml";
        System.out.println("The program begins");
        BuildingsFactory fact = new BuildingsFactory();

        List<Building> buildings1 = null;
        try {
            buildings1 = fact.generateUTMBuildingListfromKMLfile(buildingFilePath);
            System.out.println(buildings1.size());

            SirfProtocolParser parser = new SirfProtocolParser();
            List<SirfPeriodicMeasurement> sirfMeas  = parser.parseFile(SirfFilePath);

            sirfMeas.get(15).computeCorrectPseudoRangeForAllSats();
            sirfMeas.get(14).computeCorrectPseudoRangeForAllSats();
           for(int i=16; i<sirfMeas.size(); i++)
           {
               sirfMeas.get(i).computeCorrectPseudoRangeForAllSats();
               sirfMeas.get(i).computePreviousValues(sirfMeas.get(i-1), sirfMeas.get(i-2));

           }

            parser.ComputeLosNLOS(sirfMeas, buildings1);
            SirfMLCsvWriter.printToFile(sirfMeas, outputFile);
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
