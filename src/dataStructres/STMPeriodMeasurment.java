package dataStructres;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Roi on 2/1/2015.
 */
public class STMPeriodMeasurment extends NMEAPeriodicMeasurement {//todo : check if we want to keep it.

    private double oscilatorError;
    List<STMSVMeasurement> SVs;

    public List<STMSVMeasurement> getSVs() {
        return SVs;
    }
    private Map<Integer, STMSVMeasurement> mappedSvMeasurement;

    public STMPeriodMeasurment( double UtcTime, double lat, double lon, double alt, double altElip, double hDOP, List<STMSVMeasurement> sVs) {
        super(0, UtcTime, lat, lon, alt, altElip, hDOP);
        this.SVs = sVs;
    }

    public Map<Integer,? extends NMEASVMeasurement> getMappedSvMeasurements() {
        return mappedSvMeasurement;
    }
    public double getNumOfSVs(){
        return mappedSvMeasurement.size();
    }
    public Collection<STMSVMeasurement> getAllSvMeasurement(){
        return mappedSvMeasurement.values();
    }

    public NMEASVMeasurement getSvMeasurement(int prn){
        for (NMEASVMeasurement meas : mappedSvMeasurement.values()){
            if (meas.getPrn() == prn){
                return meas;
            }
        }
        return null;
    }

    public List<Integer> getAllPRNs(){
        List<Integer> res = new ArrayList<Integer>();
        for (NMEASVMeasurement meas : mappedSvMeasurement.values()){
            res.add(meas.getPrn());
        }
        return res;
    }

}
