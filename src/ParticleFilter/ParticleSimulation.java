package ParticleFilter;

import GNSS.Sat;
import GUI.KML_Generator;
import Geometry.Building;
import Geometry.BuildingsFactory;
import Geometry.Point3D;
import Parsing.nmea.NMEAProtocolParser;
import dataStructres.NMEAPeriodicMeasurement;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by Roi on 5/23/2016.
 */
public class ParticleSimulation {


    public static void main(String[] args) {
      //simulationMain2();
      //  test();
        NMEAParser();

    }

    private static void NMEAParser() {

        NMEAProtocolParser parser = new NMEAProtocolParser();
        String NMEAFIlePath = "";
        List<NMEAPeriodicMeasurement> NmeaList = null;
        try {
            NmeaList = parser.parse(NMEAFIlePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int i=0; i<NmeaList)


    }

    public static void test()
    {
        int i=1;
        int b=0;
    }


    public  static void simulationMain2() {

        String walls_file = "EsriBuildingsBursaNoindentWithBoazBuilding.kml"; //todo Roi : Sparse this file to contain only 10 buildings!
       // Buildings bs;
        List<Sat> allSats;

        List<Point3D> path;
        Particles ParticleList;
        Point3D pivot, pivot2;
        int CurrentGeneration;
     //   String Simulation_route_kml_path = "C:\\Users\\Roi\\Documents\\PHD\\Papers\\ParticleFilter\\Data\\Simulaton_route.kml";



        List<ActionFunction>  Actions;
        List<Building> bs = null;
        try {
            bs = BuildingsFactory.generateUTMBuildingListfromKMLfile(walls_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Number of buildings is " + bs.size());
        path = UtilsAlgorithms.createPath();

        allSats = UtilsAlgorithms.createSatDataList();
        String Simulation_route_3D_kml_path = "Simulaton__route_May_2016.kml";

        String Particle_path = "KaminData\\Simulaton_routeTest_FInal";
        String Particle_path3 = "KaminData\\Simulaton_routeTest_initial.kml";

        KML_Generator.Generate_kml_from_List(path, Simulation_route_3D_kml_path);
        String Particle_ans_path = ".\\Data\\ans_Simulation_route_100_particles2.kml";

        ParticleList = new Particles();
        pivot = new Point3D(670053, 3551100, 1);
        pivot2 =  new Point3D(pivot);
        pivot2.offset(100, 100, 0);
        LosData losData = new LosData( bs, path, allSats);


        ParticleList.initParticles(pivot, pivot2);
        KML_Generator.Generate_kml_from_ParticleList(ParticleList, Particle_path3,5);

        NMEAProtocolParser parser = new NMEAProtocolParser();
        String NMEAFIlePath = "";
        List<NMEAPeriodicMeasurement> NmeaList = null;
        try {
            NmeaList = parser.parse(NMEAFIlePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Actions = new ArrayList<ActionFunction>();
        List<Point3D> PointList;
        CurrentGeneration = 0;
        Random R1= new Random();
    /*    for(int i=0;i<path.size()-1; i++)
        {
            ActionFunction tmp = new ActionFunction(path.get(i), path.get(i+1), 0 , 0,0);

            Actions.add(tmp);
        }
        */
        List<Point3D> ans = new ArrayList<Point3D>();
        for(int i=1;i<path.size()-1; i++)
        {

           System.out.println("compute for timestamp "+i);
            ActionFunction currentAction = UtilsAlgorithms.getActionFromNMEA(NmeaList.get(i));
            ParticleList.MoveParticleWithError(Actions.get(i));

            allSats = UtilsAlgorithms.GetUpdateSatList(NmeaList.get(i));

            ParticleList.OutFfRegion(bs, pivot, pivot2);

            //ParticleList.MessureSignalFromSats( bs,  allSats);
            ParticleList.MessureSignalFromSats( bs,  allSats);

          //  ParticleList.MoveParticleWithError(Actions.get(i));

            ParticleList.ComputeWeightsNoHistory(losData.getSatData(i));
            //ParticleList.ComputeWeights(losData.getSatData(i)); // compute weights with hisotry
            ParticleList.Resample();

           // Point3D tmp = ParticleList.GetParticleWithMaxWeight();
            //ans.add(tmp);
            String Particle_path2=Particle_path+i+".kml";

            KML_Generator.Generate_kml_from_ParticleList(ParticleList, Particle_path2,5);
            //  KML_Point3D_List_Generator.Generate_kml_from_List(PointList,Particle_path2);
          //  ParticleList.ComputeAndPrintErrors(path.get(i));

        }

       // GUI.Generate_kml_from_List(ans, Particle_ans_path);


    }


}

