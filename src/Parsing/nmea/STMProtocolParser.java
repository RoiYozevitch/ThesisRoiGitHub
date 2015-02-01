package Parsing.nmea;

import dataStructres.NMEASVMeasurement;
import dataStructres.STMSVMeasurement;

import java.util.List;

/**
 * Created by Roi on 1/31/2015.
 */
public class STMProtocolParser extends NMEAProtocolParser{

    public static void addSVData2(List<NMEASVMeasurement> svList, String[] data) {
        STMSVMeasurement curr = makeMeas(data);
        svList.add(curr);
    }

    private static STMSVMeasurement makeMeas(String[] data) {

        int prn = Integer.parseInt(data[2]);
        double rawPR = Double.parseDouble(data[3]);
        double freq = Double.parseDouble(data[4]);
        boolean lockSignal = Boolean.parseBoolean(data[5]);
        int Cn0 = Integer.parseInt(data[6]);
        double trackedTime = Double.parseDouble(data[7]);
        boolean navigationData = Boolean.parseBoolean(data[8]);
        double EcefPosX = Double.parseDouble(data[9]);
        double EcefPosY = Double.parseDouble(data[10]);
        double EcefPosZ = Double.parseDouble(data[11]);
        double EcefVelX = Double.parseDouble(data[12]);
        double EceFVelY = Double.parseDouble(data[13]);
        double EceFVelz = Double.parseDouble(data[14]);
        double deltaPsv = Double.parseDouble(data[15]);
        double deltaPatm = Double.parseDouble(data[16]);
        STMSVMeasurement currentStmSVmesserment = new STMSVMeasurement(prn, rawPR, freq, lockSignal, Cn0, trackedTime, navigationData, EcefPosX, EcefPosY, EcefPosZ, EcefVelX, EceFVelY, EceFVelz, deltaPsv, deltaPatm);
        return  currentStmSVmesserment;

    }


}
