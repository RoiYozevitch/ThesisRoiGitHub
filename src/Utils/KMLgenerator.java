package Utils;

import Geometry.Point3D;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 */
public class KMLgenerator {



    public static void GenerateTimeStampKMLfromList(List<Point3D> PointList, String FilePath)
    {
    String line= new String("");
    int i=0;
    double  lat,lon;
    Point3D tmp;
    FileWriter fstream;
    try {
        fstream = new FileWriter(FilePath);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("<Document>\n");
        out.write("<Style id=\"red\">\n");
        out.write("<IconStyle>\n");
        out.write("<color>00ff00ff</color>\n<scale>0.5</scale>\n</IconStyle>\n");
        out.write("</Style>\n");
        ////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        out.write("\n\n<Style id=\"green\">\n");
        out.write("<IconStyle>\n");
        out.write("<color>ff0000ff</color>\n<scale>0.5</scale>\n</IconStyle>\n");
        out.write("</Style>\n");

        System.out.println("");


        for (i=0; i<PointList.size(); i++)
        {
            out.write("\n\n<Placemark>\n");
            //	if(i%2==0)

            //	out.write(" <styleUrl>#green</styleUrl>\n");
            //	else
            out.write(" <styleUrl>#green</styleUrl>\n");
            out.write("<Style>\n<BalloonStyle>\n<text>This point was taken at time "+ i +"</text>\n</BalloonStyle>\n</Style>\n ");
            out.write("<TimeStamp>\n");
            out.write("<when>"+i+"</when>\n");
            out.write(" </TimeStamp>\n");
            out.write("<Point>\n<altitudeMode>relativeToGround</altitudeMode>\n<coordinates>");
            tmp=PointList.get(i);
            tmp = GeoUtils.convertAnyCordToLATLON(PointList.get(i));
            lat=tmp.getX();
            lon=tmp.getY();
            double alt = tmp.getZ();
            line=Double.toString(lon)+" "+Double.toString(lat)+" "+Double.toString(alt);

            out.write("</coordinates>\n");
            out.write("</Point>\n</Placemark>");

        }//end of for
        out.write("</Document>");

        //Close the output stream
        out.close();
        //System.out.println( "The File was created succsefuly");
    }
    catch (IOException e) {
        e.printStackTrace();}
}// end of main
}
