package Jamming.Simulation;

import Geometry.Point3D;
import dataStructres.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Roi on 8/23/2015.
 */
public class TestSimulation {

    static final int numberOfSamplePoints = 500;
    static final double area = 500;
    static final double maxSNR  = 50;

    public static void main(String[] args) {
        Point3D minRoi = new Point3D(670114.15, 3551135.3, 1);
        Point3D maxRoi = new Point3D(minRoi);
        maxRoi.movePoint(area, area, 0);
        Point3D jamLoc = new Point3D(670214 ,3551235, 1 );
        double jamRadius = 200; // in meters
        Jammer jam1 = new Jammer(jamLoc, jamRadius, Pattern.OMNI);
      //  List<Point3D> samplePoints = new ArrayList<>();
        List<Pair<Point3D, Double>> samplePoints2 = new ArrayList<>();

        // Initialization
        for(int i=0; i<numberOfSamplePoints; i++) {
            Point3D tmp = getRandomLoc(minRoi, maxRoi);
            Double SnrAttu = jam1.singlePointAttenuation(tmp, maxSNR);
            if (SnrAttu != null)
                samplePoints2.add(new Pair<Point3D, Double>(tmp, SnrAttu));


        }
        int numOfRelevantPoints =0;
        for (Pair<Point3D, Double> sample : samplePoints2)
        {

            System.out.println(sample.getFirst() + " . Max Snr: " + sample.getSecond() + " Distance " + sample.getFirst().distance(jam1.jamPos));
            if(sample.getSecond() < 50 )
                numOfRelevantPoints++;
        }
        System.out.println("Number of Relevant Points "+ numOfRelevantPoints +" out of "+numberOfSamplePoints+". Ratio is "+ (double)numOfRelevantPoints/numberOfSamplePoints);
        String filepath = "data4QGIS.csv";
       // PrintPairListToFile(filepath, samplePoints2);



    }

    private static void PrintPairListToFile(String filepath, List<Pair<Point3D, Double>> samplePoints) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filepath + ".csv"));
            writer.write("xCor, yCor, zCor\n");
            for (Pair<Point3D, Double> sample : samplePoints)
            {

                String line =sample.getFirst().getX() + "," + sample.getFirst().getY() + ","+ (maxSNR - sample.getSecond())+"\n";
                writer.write(line);
            }


            writer.flush();
            writer.close();
            System.out.println("Done creating an QGIS file");
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public static Point3D getRandomLoc(Point3D minRoi, Point3D maxRoi)
    {
        Random rand = new Random();
        double dx = maxRoi.getX() - minRoi.getX();
        double dy = maxRoi.getY() - minRoi.getY();
         double x= rand.nextDouble()*dx;
        double y = rand.nextDouble()*dy;
        return new Point3D(minRoi.getX()+x, minRoi.getY()+y, minRoi.getZ());
    }

}
