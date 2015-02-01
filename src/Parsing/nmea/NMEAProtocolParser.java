package Parsing.nmea;

import dataStructres.NMEAPeriodicMeasurement;
import dataStructres.NMEASVMeasurement;
import dataStructres.STMSVMeasurement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NMEAProtocolParser {


	public static List<NMEAPeriodicMeasurement> parse(String path) throws IOException{
		List<NMEAPeriodicMeasurement> result = new ArrayList<NMEAPeriodicMeasurement>();
		BufferedReader br = new BufferedReader(new FileReader(path)); //todo throw spesific exeption. Check where an NMEA msg starts
		String line;
		double lat = 0, lon = 0, alt = 0, altElip = 0, hDOP = 0;
		long time = 0;
		long UtcTime=0;
		List<NMEASVMeasurement> svList = new ArrayList<NMEASVMeasurement>();
		while((line = br.readLine()) != null){
			String[] data = filter(line).split(",");
			if (data.length == 0){
				continue;
			}
			NMEASentence currentSentence = NMEASentence.sentencesToParse.get(data[0]);
			if (currentSentence == null){
				continue;
			}
			if (hasTimestamp(currentSentence) && Long.parseLong(data[currentSentence.getfieldsIndexByName("UtcTime")].replace(".", "")) != UtcTime){
				result.add(new NMEAPeriodicMeasurement(time, UtcTime,  lat, lon, alt, altElip, hDOP, svList));
				lat = 0;
				lon = 0; alt = 0;
				altElip = 0;
				hDOP = 0;
				time = 0;
				svList = new ArrayList<NMEASVMeasurement>();
			}
			if(currentSentence.getSentenceName().equals("$GPRMC"))
			{
				UtcTime = Long.parseLong(data[currentSentence.getfieldsIndexByName("UtcTime")]);

			}
			if (currentSentence.getSentenceName().equals("$GPGGA")){
				time = Long.parseLong(data[currentSentence.getfieldsIndexByName("time")].replace(".", ""));
				String latString = data[currentSentence.getfieldsIndexByName("lat")];
				if (!latString.equals("")){
					System.out.println(latString);

					lat = Double.parseDouble(latString) / 100;
					lon = Double.parseDouble(data[currentSentence.getfieldsIndexByName("lon")]) / 100;
					hDOP = Double.parseDouble(data[currentSentence.getfieldsIndexByName("hDOP")]);
					alt = Double.parseDouble(data[currentSentence.getfieldsIndexByName("alt")]);
					altElip = Double.parseDouble(data[currentSentence.getfieldsIndexByName("altElip")]);
				}
			}
			if (isSVDataSentence(currentSentence)){
				if(currentSentence.getSentenceName().equals("$PSTMTS"))
					addSTM_SVdata(svList, data);
				else
					addSVData(svList, data);
			}
		}
		result.add(new NMEAPeriodicMeasurement(time, UtcTime, lat, lon, alt, altElip, hDOP, svList));
		return result;
	}

	private static void addSVData(List<NMEASVMeasurement> svList, String[] data) {
		List<String[]> miniData = splitSatellites(data);
		for (String[] svData : miniData){
            NMEASVMeasurement sv = new NMEASVMeasurement(Integer.parseInt(svData[0]),
                    Integer.parseInt(svData[1]),
                    Integer.parseInt(svData[2]),
                    Integer.parseInt(svData[3]));
            svList.add(sv);
        }
	}

	private static void addSTM_SVdata(List<NMEASVMeasurement> svList, String[] data)
	{
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
		svList.add(currentStmSVmesserment);

	}





	private static boolean isSVDataSentence(NMEASentence sentence){
		return sentence.getSentenceName().equals("$GPGSV")
				|| sentence.getSentenceName().equals("$GLGSV")
				|| sentence.getSentenceName().equals("$PSTMTS");
	}

	private static String filter(String line) {
		if (line.contains("$")){//start of message
			line = line.substring(line.indexOf("$"));
		}
		else{//corrupt line
			return "N/A";
		}
		if (line.contains("*")){//checksum
			line = line.substring(0, line.indexOf("*"));
		}
		return line;
	}

	private static List<String[]> splitSatellites(String[] data) {
		List<String[]> res = new ArrayList<String[]>();
		for (int i = 4; i + 3 < data.length; i = i + 4){
			if (data[i].equals("") || data[i + 1].equals("") || data[i + 2].equals("") || data[i + 3].equals("")){
				continue;
			}
			res.add(new String[]{data[i], data[i + 1], data[i + 2], data[i + 3]});
		}
		return res;
	}

	private static boolean hasTimestamp(NMEASentence currentSentence) {
		return currentSentence.getSentenceName().equals("$GPRMS");
	}

}
