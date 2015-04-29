package Parsing.sirf;

import dataStructres.SirfPeriodicMeasurement;
import dataStructres.SirfSVMeasurement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Roi on 13/04/2015.
 */
public class SirfMLCsvWriter {
    private static String newLine = "\r\n";
    private static String header = "UTC Time, X Position, Y Position, Z Position, X Velocity, Y Velocity, Z Velocity, HDOP, Lat, Lon, Alt (Elipsoide), Alt (AMSL), Speed, Course, Estimated Horizontal Positioning Error, Estimated Vertical Positioning Error, Estimated Horizontal Velocity Error, Clock Bias, Clock Bias Error, Clock Drift, Clock Drift Error, EXTENDED GPS Week, GPS TOW, SVs number, Clock Drift 7, Clock Bias 7, Estimated GPS Time, PRN, GPS Software Time, Azimuth, Elevation, State, X Position, Y Position, Z Position, X Velocity, Y Velocity, Z Velocity, Pseudorange, residuals, C/No Max, C/No Min, C/No Delta, Carrier Frequency, Carrier Phase, Delta Range Interval, Mean Delta Range Time, Clock Bias, Clock Drift, Ionospheric Delay, Filtered C/No Max, Filtered C/No Min, Filtered C/No Delta, Los Value" + newLine;

    public static void printToFile(List<SirfPeriodicMeasurement> measurements, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + ".csv"));
        writer.write(header);
        writer.write(newLine);

        for (int i = 0; i<measurements.size(); i++){
            String csvString = toCsvString(measurements.get(i));
            if (!csvString.equals("0,0,0,0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0,0,0,0,0,0,0,0,0,")){
                writer.write(csvString);
            }
        }
        writer.flush();
        writer.close();

    }

    private static String toCsvString(SirfPeriodicMeasurement meas) {
        String res = "";
        //if (meas.getTime() == 0l){
        //	return res;
        //}
        for (Integer PRN : meas.getSatellites().keySet()){
            res += (meas.getTime()) + ",";
            res += meas.getxPos() + ",";
            res += meas.getyPos() + ",";
            res += meas.getzPos() + ",";
            res += meas.getxV() + ",";
            res += meas.getyV() + ",";
            res += meas.getzV() + ",";
            res += meas.getHdop() + ",";
            res += meas.getLat() + ",";
            res += meas.getLon() + ",";
            res += meas.getAltEllipsoid() + ",";
            res += meas.getAltMSL() + ",";
            res += meas.getSpeed() + ",";
            res += meas.getCourse() + ",";
            res += meas.getHorizontalPosError() + ",";
            res += meas.getVerticalPosError() + ",";
            res += meas.getHorizontalVelocityError() + ",";
            res += meas.getClockBias() + ",";
            res += meas.getClockBiasError() + ",";
            res += meas.getClockDrift() + ",";
            res += meas.getClockDriftError() + ",";
            res += meas.getxExGPSWeek() +  ",";
            res += meas.getGPSTOW() + ",";
            res += meas.getSVNum() + ",";
            res += meas.getClockDrift7()+ ",";
            res += meas.getClockBias7() + ",";
            res += meas.getEstimatedGPSTime() + ",";
            //for (Integer PRN : meas.getSatellites().keySet()){
                SirfSVMeasurement sv = meas.getSatellites().get(PRN);
                if(sv==null)
                    // for(int cnt=0;cnt<PRN;cnt++)
                    //  {
                    res +=",,,,,,,,,,,,,,,,,,,,,"; //21 spaces
                    //  }
                else
                {
                    res += PRN + ",";
                    res += sv.getGpsSoftwareTime() +",";
                    res += sv.getAzimuth()+",";
                    res += sv.getElevation()+",";
                    res += sv.getState()+",";
                    res += sv.getxPos()+",";
                    res += sv.getyPos()+",";
                    res += sv.getzPos()+",";
                    res += sv.getxV()+",";
                    res += sv.getyV()+",";
                    res += sv.getzV()+",";
                    res += sv.getPseudorange()+",";
                    res += meas.getResidual(PRN)+"," ;
                    res += sv.getMaxCn0()+",";
                    res += sv.getMinCn0()+",";
                    res += sv.getCNo()[9] - sv.getCNo()[0]+",";
                    res += sv.getCarrierFreq()+",";
                    res += sv.getCarrierPhase()+",";
                    res += sv.getDeltaRangeInterval()+",";
                    res += sv.getMeanDeltaRangeTime()+",";
                    res += sv.getClockBias()+",";
                    res += sv.getClockDrift()+",";
                    res += sv.getIonosphericDelay()+",";
                    res += sv.getMaxFilterCn0()+",";
                    res += sv.getMinFilterCn0()+",";
                    res += sv.getFilteredCNo()[9] - sv.getFilteredCNo()[0]+",";
                    Boolean los = sv.getLOS();
                    if (los != null){
                        res += (los ? "LOS" : "NLOS") +",";
                    }
                    else{
                        res += "null,";
                    }
                }
            res += "\r\n";
        }
        return res;
    }





}
