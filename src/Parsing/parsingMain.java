package Parsing;

import Algorithm.PseudoRangeComp;
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

      //  SirfParsingML();
        PseudoRangeCompute();


    }




    public static void SirfParsingML() {

        String SirfFilePath = "POINT_A_STATIONARY.txt";
        String outputFile = "POINT_A_STATIONARY_ML";

        SirfProtocolParser parser = new SirfProtocolParser();
        try {
            List<SirfPeriodicMeasurement> sirfMeas  = parser.parseFile(SirfFilePath);
            SirfMLCsvWriter.printToFile(sirfMeas, outputFile);

        } catch (IOException e) {
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
                double correctedPR = stmSvMeasurement.getCorrectedPR();
                // System.out.println(key + "\t " + correctedPR);
            }
        }

        System.out.println(" number of meas is " + stmMeas.size());

            PseudoRangeComp.computePseudoRangeFromStmMessurment(stmMeas.get(2));



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
