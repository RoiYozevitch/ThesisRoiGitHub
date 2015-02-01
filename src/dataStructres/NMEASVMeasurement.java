package dataStructres;

import Geometry.Point3D;

public class NMEASVMeasurement {
	
	protected int az, el, snr, prn;



	public NMEASVMeasurement(int prn, int el, int az, int snr) {
		this.az = az;
		this.el = el;
		this.snr = snr;
		this.prn = prn;
	}

	public NMEASVMeasurement(int prn, int snr)
	{
		this.prn = prn;
		this.snr = snr;
	}




	/**
	 * @return the az
	 */
	public int getAz() {
		return az;
	}

	/**
	 * @return the el
	 */
	public int getEl() {
		return el;
	}

	/**
	 * @return the snr
	 */
	public int getSnr() {
		return snr;
	}

	/**
	 * @return the prn
	 */
	public int getPrn() {
		return prn;
	}


	public enum GNSSType {
		GPS, GLONASS
	}
}
