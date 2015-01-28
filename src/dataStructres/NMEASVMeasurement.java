package dataStructres;

import Geometry.Point3D;

public class NMEASVMeasurement {
	
	private int az, el, snr, prn;
	 Point3D ECEFpos;
	/*
	The below equation fits GPS soley
	Correct PseudoRange (before clock bias computation) is :PR = rawPR - deltaP_ATM + deltaP_sv
	 */
	double rawPR;
	double deltaP_ATM;
	double deltaP_SV;
	/*
	for GLONASS sats, the equation becomes PR = rawPR - deltaP_ATM + deltaP_sv + deltaP_glo
	 */
 	double deltaP_glo;
	double correctedPR;

	public double getCorrectedPR() {
		return correctedPR;
	}

	public NMEASVMeasurement( int snr, int prn, double xPos, double yPos, double zPos, double rawPR, double deltaP_ATM, double deltaP_SV, double deltaP_glo) {

		this.snr = snr;
		this.prn = prn;
		this.ECEFpos = new Point3D(xPos, yPos, zPos);
		this.rawPR = rawPR;
		this.deltaP_ATM = deltaP_ATM;
		this.deltaP_SV = deltaP_SV;
		this.deltaP_glo = deltaP_glo;
		setCorrectedPR();

	}

	public Point3D getECEFpos() {
		return ECEFpos;
	}

	private void setCorrectedPR()
	{
		if(this.prn<40)
			this.correctedPR = this.rawPR -this.deltaP_ATM + this.deltaP_SV;
		else if(this.prn>=40)
			this.correctedPR = this.rawPR -this.deltaP_ATM + this.deltaP_SV + this.deltaP_glo;
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
	
	

}
