package dataStructres;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NMEAPeriodicMeasurement implements  Serializable{

	private static final long serialVersionUID = 886357302201842088L;
	
	double lat, lon, alt, altElip, HDOP;
	int numOfSVs;
	List<NMEASVMeasurement> SVs;
	long time;

	long UtcTime;

	public double getAltElip(){
		return altElip;
	}

	public long getUtcTime() {
		return UtcTime;
	}

	public double getHDOP(){
		return HDOP;
	}
	
	public double getNumOfSVs(){
		return SVs.size();
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
	
	public List<NMEASVMeasurement> getAllSvMeasurements(){
		return SVs;
	}
	
	public NMEASVMeasurement getSvMeasurement(int prn){
		for (NMEASVMeasurement meas : this.SVs){
			if (meas.getPrn() == prn){
				return meas;
			}
		}
		return null;
	}
	
	public List<Integer> getAllPRNs(){
		List<Integer> res = new ArrayList<Integer>();
		for (NMEASVMeasurement meas : this.SVs){
			res.add(meas.getPrn());
		}
		return res;
	}

	public NMEAPeriodicMeasurement(long time, long UtcTime, double lat, double lon, double alt,
								   double altElip, double hDOP, List<NMEASVMeasurement> sVs) {
		this.time = time;
		this.UtcTime  = UtcTime;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.altElip = altElip;
		HDOP = hDOP;
		SVs = sVs;
	}
	
}
