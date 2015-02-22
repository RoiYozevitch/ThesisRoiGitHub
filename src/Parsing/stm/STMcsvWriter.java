package Parsing.stm;

import dataStructres.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Roi on 22/02/2015.
 */
public class STMcsvWriter {

    private static String header = "UTC Time, Latitude, Longitude, Altitude, Height over elipsoid, HDOP, SVs";
    private static String newLine = "\r\n";

    static {
        for (int i = 0; i < 20; i++) {
            header += ", PRN, Azimuth, Elevation, S/Nr,Xpos, Ypos, Zpos, Xvel, Yvel, Zvel, freq, PR";
        }

    }
    public static void printStmToFile(List<STMPeriodMeasurment> measurements, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + "parsed.csv"));

        writer.write(header);
        writer.write(newLine);
        Set<Integer> allPrns = new TreeSet<Integer>();

        for (STMPeriodMeasurment m : measurements){
            allPrns.addAll(m.getAllPRNs());
        }
        for (int i = 0; i<measurements.size(); i++){
            String csvString = toCsvString(measurements.get(i), allPrns);
            writer.write(csvString);
        }
        writer.flush();
        writer.close();



    }

    private static String toCsvString(STMPeriodMeasurment meas, Set<Integer> allPrns) {
        String res = "";
        if (meas.getTime() == 0l){
            return res;
        }
        res += meas.getTime()+ ",";
        res += meas.getLat() + ",";
        res += meas.getLon() + ",";
        res += meas.getAlt() + ",";
        res += meas.getAltElip() + ",";
        res += meas.getHDOP() + ",";
        res += meas.getAllSvMeasurements().size()+", ";
        Map<Integer, STMSVMeasurement> mappedSvMeasurements = meas.getMappedSvMeasurement();
        for (Integer prn : allPrns){
            STMSVMeasurement SvMeasurement = mappedSvMeasurements.get(prn);
            if (SvMeasurement == null){
                SvMeasurement = SvMeasurement.nullMeas;
            }
            res += SvMeasurement.getPrn() + ",";
            res += SvMeasurement.getAz() + ",";
            res += SvMeasurement.getEl() + ",";
            res += SvMeasurement.getSnr() + ",";
            res += SvMeasurement.getEcefXpos()+",";
            res += SvMeasurement.getEcefYpos()+",";
            res += SvMeasurement.getEcefZpos()+",";
            res += SvMeasurement.getEcefXvel()+",";
            res += SvMeasurement.getEcefYvel()+",";
            res += SvMeasurement.getEcefZpos()+",";
            res += SvMeasurement.getFrequncy()+",";
            res += SvMeasurement.getCorrectedPR()+",";


        }
        res += "\r\n";
        return res;

    }
}


