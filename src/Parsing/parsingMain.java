package Parsing;

import Parsing.sirf.SirfProtocolParser;
import Parsing.stm.STMProtocolParser;
import dataStructres.NMEAPeriodicMeasurement;
import dataStructres.STMPeriodMeasurment;
import Parsing.nmea.NMEAProtocolParser;
import dataStructres.SirfPeriodicMeasurement;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roi on 22/02/2015.
 */
public class parsingMain {

    public static void main(String[] args) throws IOException {
        String sirfFilePath  = "";
        String nmeaDilePath = "";
        String StmFilePath = " ";

        STMProtocolParser stmParser = new STMProtocolParser();
       // List<STMPeriodMeasurment> stmMeas = stmParser.parse(StmFilePath);
        //todo Ayal : what to do? rewrite the parse for STM again?

        NMEAProtocolParser nmeaParser  = new NMEAProtocolParser();
        List<NMEAPeriodicMeasurement>  nmeaMeas =  nmeaParser.parse(nmeaDilePath);

        SirfProtocolParser sirfParser = new SirfProtocolParser();
        List<SirfPeriodicMeasurement> sirfMeas  = sirfParser.parseFile(sirfFilePath);



    }
}
