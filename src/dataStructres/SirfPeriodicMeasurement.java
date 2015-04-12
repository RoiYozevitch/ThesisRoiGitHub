package dataStructres;

import GNSS.Sat;
import Geometry.Point3D;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SirfPeriodicMeasurement implements Serializable{
	
	private static final long serialVersionUID = 8214629324823516578L;
    Point3D ReciverReportedPosition;
	int xPos;
    int yPos;
    int zPos;
    int speed;
    int course;
    int horizontalPosError;
    int GPSWeek;
    int verticalPosError;
    int clockError;
    int HorizontalVelocityError;
    int ClockBias;
    int SvNum;
    double GPSTOW;
    int ClockBiasError;
    int ClockDrift;
    int ClockDriftError;

 //   public void SetPosReciverPos()
    //  this.ReciverReportedPosition = new Point3D(this.)

    public byte getMode1() {
        return Mode1;
    }

    public void setMode1(byte mode1) {
        Mode1 = mode1;
    }

    byte Mode1;

    public byte getMode2() {
        return Mode2;
    }

    public void setMode2(byte mode2) {
        Mode2 = mode2;
    }

    byte Mode2;
	double yV;
    double ClockDrif7;
    double ClockBias7;
    double EstimateGPSTime;
	double xV;
	double zV;
	double hdop, lat, lon, altEllipsoid, altMSL;
	long time;
	Map<Integer, SirfSVMeasurement> satellites;
	
	public SirfPeriodicMeasurement(){
		satellites = new HashMap<Integer, SirfSVMeasurement>();
	}
	
	/**
	 * @return the xPos
	 */
    public int getxExGPSWeek()
    {
       return GPSWeek;
    }

    public double getGPSTOW()
    {
         return GPSTOW;
    }

    public int getSVNum()
    {
         return SvNum;
    }

    public double getClockDrift7()
    {
         return ClockDrif7;
    }

    public double getClockBias7()
    {
         return ClockBias7;
    }

    public double getEstimatedGPSTime()
    {
        return EstimateGPSTime;
    }

    public int getxPos() {
		return xPos;
	}
	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}
	/**
	 * @return the zPos
	 */
	public int getzPos() {
		return zPos;
	}
	/**
	 * @return the xV
	 */
	public double getxV() {
		return xV;
	}
	/**
	 * @return the yV
	 */
	public double getyV() {
		return yV;
	}
	/**
	 * @return the zV
	 */
	public double getzV() {
		return zV;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @return the course
	 */
	public int getCourse() {
		return course;
	}
	/**
	 * @return the horizontalPosError
	 */
	public int getHorizontalPosError() {
		return horizontalPosError;
	}
	/**
	 * @return the verticalPosError
	 */
	public int getVerticalPosError() {
		return verticalPosError;
	}
	/**
	 * @return the clockError
	 */
	public int getClockError() {
		return clockError;
	}
	/**
	 * @return the horizontalVelocityError
	 */
	public int getHorizontalVelocityError() {
		return HorizontalVelocityError;
	}
	/**
	 * @return the clockBias
	 */
	public int getClockBias() {
		return ClockBias;
	}
	/**
	 * @return the clockBiasError
	 */
	public int getClockBiasError() {
		return ClockBiasError;
	}
	/**
	 * @return the clockDrift
	 */
	public int getClockDrift() {
		return ClockDrift;
	}
	/**
	 * @return the clockDriftError
	 */
	public int getClockDriftError() {
		return ClockDriftError;
	}
	/**
	 * @return the hdop
	 */
	public double getHdop() {
		return hdop;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @return the altEllipsoid
	 */
	public double getAltEllipsoid() {
		return altEllipsoid;
	}
	/**
	 * @return the altMSL
	 */
	public double getAltMSL() {
		return altMSL;
	}
	/**
	 * @return the date
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @return the satellites
	 */
	public Map<Integer, SirfSVMeasurement>  getSatellites() {
		return satellites;
	}
	public SirfPeriodicMeasurement(int xPos, int yPos, int zPos, int xV, int yV,
			int zV, int speed, int course, int horizontalPosError,
			int verticalPosError, int clockError, int horizontalVelocityError, int GPSWeek,
			int clockBias, int clockBiasError, int clockDrift,
			int clockDriftError, double hdop, double lat, double lon,
			double altEllipsoid, double altMSL, long time,
			Map<Integer, SirfSVMeasurement> satellites) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.xV = xV;
		this.yV = yV;
		this.zV = zV;
		this.speed = speed;
		this.course = course;
		this.horizontalPosError = horizontalPosError;
		this.verticalPosError = verticalPosError;
		this.clockError = clockError;
		HorizontalVelocityError = horizontalVelocityError;
		ClockBias = clockBias;
		ClockBiasError = clockBiasError;
		ClockDrift = clockDrift;
		ClockDriftError = clockDriftError;
		this.hdop = hdop;
		this.lat = lat;
		this.lon = lon;
		this.altEllipsoid = altEllipsoid;
		this.altMSL = altMSL;
		this.time = time;
		this.satellites = satellites;
	}


    public void computePseudoRangeLeastSquare()
    {

    }

    public void setxExGPSWeek(int GPSWeek)
    {
        this.GPSWeek=GPSWeek;
    }

    public void setGPSTOW(double GPSTOW)
    {
        this.GPSTOW=GPSTOW;
    }

    public void setSVNum(int SvNum)
    {
        this.SvNum=SvNum;
    }

    public void setClockDrift7(double ClockDrif7)
    {
        this.ClockDrif7=ClockDrif7;
    }

    public void setClockBias7(double ClockBias7)
    {
        this.ClockBias7=ClockBias7;
    }

    public void setEstimatedGPSTime(double EstimateGPSTime)
    {
          this.EstimateGPSTime=EstimateGPSTime;
    }



    public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	/**
	 * @param zPos the zPos to set
	 */
	public void setzPos(int zPos) {
		this.zPos = zPos;
	}


	/**
	 * @param d the xV to set
	 */
	public void setxV(double d) {
		this.xV = d;
	}
	/**
	 * @param d the yV to set
	 */
	public void setyV(double d) {
		this.yV = d;
	}
	/**
	 * @param d the zV to set
	 */
	public void setzV(double d) {
		this.zV = d;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(int course) {
		this.course = course;
	}
	/**
	 * @param horizontalPosError the horizontalPosError to set
	 */
	public void setHorizontalPosError(int horizontalPosError) {
		this.horizontalPosError = horizontalPosError;
	}
	/**
	 * @param verticalPosError the verticalPosError to set
	 */
	public void setVerticalPosError(int verticalPosError) {
		this.verticalPosError = verticalPosError;
	}
	/**
	 * @param clockError the clockError to set
	 */
	public void setClockError(int clockError) {
		this.clockError = clockError;
	}
	/**
	 * @param horizontalVelocityError the horizontalVelocityError to set
	 */
	public void setHorizontalVelocityError(int horizontalVelocityError) {
		HorizontalVelocityError = horizontalVelocityError;
	}
	/**
	 * @param clockBias the clockBias to set
	 */
	public void setClockBias(int clockBias) {
		ClockBias = clockBias;
	}
	/**
	 * @param clockBiasError the clockBiasError to set
	 */
	public void setClockBiasError(int clockBiasError) {
		ClockBiasError = clockBiasError;
	}
	/**
	 * @param clockDrift the clockDrift to set
	 */
	public void setClockDrift(int clockDrift) {
		ClockDrift = clockDrift;
	}
	/**
	 * @param clockDriftError the clockDriftError to set
	 */
	public void setClockDriftError(int clockDriftError) {
		ClockDriftError = clockDriftError;
	}
	/**
	 * @param hdop the hdop to set
	 */
	public void setHdop(double hdop) {
		this.hdop = hdop;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * @param altEllipsoid the altEllipsoid to set
	 */
	public void setAltEllipsoid(double altEllipsoid) {
		this.altEllipsoid = altEllipsoid;
	}
	/**
	 * @param altMSL the altMSL to set
	 */
	public void setAltMSL(double altMSL) {
		this.altMSL = altMSL;
	}
	/**
	 * @param date the date to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @param satellites the satellites to set
	 */
	public void setSatellites(Map<Integer, SirfSVMeasurement> satellites) {
		this.satellites = satellites;
	}
	



    public int GetMaxSNR(int timeInMiliSec)
    {

        int MaxSNR=0;

        Sat tmp;
        for (Integer I : this.getSatellites().keySet())

        {
            SirfSVMeasurement tmpSat = this.getSatellites().get(I);
            if(tmpSat.CNo[timeInMiliSec]>=MaxSNR)
                MaxSNR = tmpSat.CNo[timeInMiliSec];
        }

            return MaxSNR;
    }
	
	public SirfPeriodicMeasurement(SirfPeriodicMeasurement other, Map<Integer, SirfSVMeasurement> satellites) {
		this.xPos = other.xPos;
		this.yPos = other.yPos;
		this.zPos = other.zPos;
		this.xV = other.xV;
		this.yV = other.yV;
		this.zV = other.zV;
		this.speed = other.speed;
		this.course = other.course;
		this.horizontalPosError = other.horizontalPosError;
		this.verticalPosError = other.verticalPosError;
		this.clockError = other.clockError;
		HorizontalVelocityError = other.HorizontalVelocityError;
		ClockBias = other.ClockBias;
		ClockBiasError = other.ClockBiasError;
		ClockDrift = other.ClockDrift;
		ClockDriftError = other.ClockDriftError;
		this.hdop = other.hdop;
		this.lat = other.lat;
		this.lon = other.lon;
		this.altEllipsoid = other.altEllipsoid;
		this.altMSL = other.altMSL;
		this.time = other.time;
		this.satellites = satellites;
	}



	


	public double getAlt() {
		return getAltEllipsoid();
	}
	

	

	
	

}
