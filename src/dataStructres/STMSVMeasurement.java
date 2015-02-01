package dataStructres;

import Geometry.Point3D;

/**
 * Created by Roi on 1/31/2015.
 */
public class STMSVMeasurement extends NMEASVMeasurement {

    /*
The below equation fits GPS soley
Correct PseudoRange (before clock bias computation) is :PR = rawPR - deltaP_ATM + deltaP_sv
 */
    private double rawPR;
    private double deltaP_ATM;
    private double deltaP_SV;
    /*
    for GLONASS sats, the equation becomes PR = rawPR - deltaP_ATM + deltaP_sv + deltaP_glo
     */
    private double deltaP_glo;
    private double correctedPR;

    protected Point3D ECEFpos;
    protected Point3D EcefVel;
    double frequncy;
    boolean lockSignal;
    boolean navigationData;

    public STMSVMeasurement(int prn, int el, int az, int snr, double xPos, double yPos, double zPos, double rawPR, double deltaP_ATM, double deltaP_SV) {
        super(prn, el, az, snr);
        this.ECEFpos = new Point3D(xPos, yPos, zPos);
        this.rawPR = rawPR;
        this.deltaP_ATM = deltaP_ATM;
        this.deltaP_SV = deltaP_SV;
        setCorrectedPR();
    }

    public STMSVMeasurement(int prn, double rawPR, double freq, boolean lockSignal, int cn0, double trackedTime, boolean navigationData, double ecefPosX, double ecefPosY, double ecefPosZ, double ecefVelX, double eceFVelY, double eceFVelz, double deltaPsv, double deltaPatm) {
        //
        super(prn, cn0);
        this.deltaP_ATM = deltaP_ATM;
        this.deltaP_SV = deltaP_SV;
        this.ECEFpos = new Point3D(ecefPosX, ecefPosY, ecefPosZ);
        this.EcefVel = new Point3D(ecefVelX, eceFVelY, eceFVelz);
        this.frequncy = freq;
        this.lockSignal = lockSignal;
        this.navigationData = navigationData;
        setCorrectedPR();


    }


    public double getCorrectedPR() {

        return this.correctedPR;
    }



    private void setCorrectedPR() //todo ROi add glonass correction.
    {
        if(super.prn<40)
            this.correctedPR = this.rawPR -this.deltaP_ATM + this.deltaP_SV;
       // else if(super.prn>=40)
        //    this.correctedPR = this.rawPR -this.deltaP_ATM + this.deltaP_SV
    }

    public Point3D getECEFpos() {
        return ECEFpos;
    }

    public Point3D getEcefVel() {
        return EcefVel;
    }

    public double getFrequncy() {
        return frequncy;
    }

    public boolean isLockSignal() {
        return lockSignal;
    }

    public boolean isNavigationData() {
        return navigationData;
    }

}
