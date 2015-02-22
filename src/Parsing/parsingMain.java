package Parsing;

import Parsing.sirf.SirfProtocolParser;
import Parsing.stm.STMProtocolParser;
import dataStructres.*;
import Parsing.nmea.NMEAProtocolParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Roi on 22/02/2015.
 */
public class parsingMain {

    public static void main(String[] args) throws IOException {
        String sirfFilePath  = "";
        String nmeaDilePath = "";
        String StmFilePath = " ";

        STMProtocolParser stmParser = new STMProtocolParser();
        List<NMEAPeriodicMeasurement> stmMeas = stmParser.parse(StmFilePath);
        //todo roi: learn this code.
        for (NMEAPeriodicMeasurement meas : stmMeas){
            Map<Integer, ? extends NMEASVMeasurement> mappedSvMeasurements = ((STMPeriodMeasurment) meas).getMappedSvMeasurements();
            for (Integer key : mappedSvMeasurements.keySet()){
                STMSVMeasurement stmSvMeasurement = (STMSVMeasurement)mappedSvMeasurements.get(key);
                double correctedPR = stmSvMeasurement.getCorrectedPR();
                System.out.println(key + "\t " + correctedPR);
            }
        }

        NMEAProtocolParser nmeaParser  = new NMEAProtocolParser();
        List<NMEAPeriodicMeasurement>  nmeaMeas =  nmeaParser.parse(nmeaDilePath);

        SirfProtocolParser sirfParser = new SirfProtocolParser();
        List<SirfPeriodicMeasurement> sirfMeas  = sirfParser.parseFile(sirfFilePath);



    }
}
