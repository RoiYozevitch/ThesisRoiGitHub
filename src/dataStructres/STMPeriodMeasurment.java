package dataStructres;

import java.util.List;

/**
 * Created by Roi on 2/1/2015.
 */
public class STMPeriodMeasurment extends NMEAPeriodicMeasurement {//todo : check if we want to keep it.

    private double oscilatorError;
    List<STMSVMeasurement> SVs;


    public STMPeriodMeasurment(long time, long UtcTime, double lat, double lon, double alt, double altElip, double hDOP, List<NMEASVMeasurement> sVs) {
        super(time, UtcTime, lat, lon, alt, altElip, hDOP, sVs);
    }

}
