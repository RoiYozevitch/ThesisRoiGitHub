package dataStructres;

import java.io.Serializable;
import java.util.*;

public class NMEAPeriodicMeasurement implements  Serializable{

	private static final long serialVersionUID = 886357302201842088L;
	
	double lat, lon, alt, altElip, HDOP;
	long time;
	double UtcTime;
	private Map<Integer, NMEASVMeasurement> mappedSvMeasurements;

	public NMEAPeriodicMeasurement(double utcTime, double lat, double lon, double alt, double altElip, double hDOP) {
		this.UtcTime=utcTime;
		this.lat=lat;
		this.lon=lon;
		this.alt=alt;
		this.altElip=altElip;
		this.HDOP=hDOP;
        this.mappedSvMeasurements = new HashMap<>();
	}

	public double getAltElip(){
		return altElip;
	}

	public double getUtcTime() {
		return UtcTime;
	}

	public double getHDOP(){
		return HDOP;
	}
	
	public double getNumOfSVs(){
		return mappedSvMeasurements.size();
	}
	
	public double getLon() {
		// TODO Auto-generated method stub
		return lon;
	}

	public long getTime() {
		// TODO Auto-generated method stub
		return this.time;
	}

	public double getLat() {
		// TODO Auto-generated method stub
		return lat;
	}

	public double getAlt() {
		// TODO Auto-generated method stub
		return alt;
	}
	
	public Collection<NMEASVMeasurement> getAllSvMeasurements(){
		return mappedSvMeasurements.values();
	}
	
	public NMEASVMeasurement getSvMeasurement(int prn){
		for (NMEASVMeasurement meas : mappedSvMeasurements.values()){
			if (meas.getPrn() == prn){
				return meas;
			}
		}
		return null;
	}
	
	public List<Integer> getAllPRNs(){
		List<Integer> res = new ArrayList<Integer>();
		for (NMEASVMeasurement meas : mappedSvMeasurements.values()){
			res.add(meas.getPrn());
		}
		return res;
	}

	public NMEAPeriodicMeasurement(long time, double UtcTime, double lat, double lon, double alt,
								   double altElip, double hDOP, List<NMEASVMeasurement> sVs) {
        this.mappedSvMeasurements = new HashMap<>();
		this.time = time;
		this.UtcTime  = UtcTime;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.altElip = altElip;
		HDOP = hDOP;
		for (NMEASVMeasurement sv : sVs){
			mappedSvMeasurements.put(sv.getPrn(), sv);
		}
	}

	public Map<Integer,? extends NMEASVMeasurement> getMappedSvMeasurements() {
		return mappedSvMeasurements;
	}
}
