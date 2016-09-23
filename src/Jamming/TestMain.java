package Jamming;

import Geometry.Point2D;

import java.awt.*;

/**
 * Created by Roi on 9/23/2016.
 */
public class TestMain {

    public static void main(String[] args) {

        Point2D jammerloc = new Point2D(50,50);
        JammerParticle realJammer  = new JammerParticle(50, jammerloc, 10);

        JamParticles JammerParticles = new JamParticles();
        JammerParticles.NaiveInit(0,0,200,200);



        ClientList receiverList = new ClientList();
        receiverList.Init(0,0,200,200);

        for(int i=0; i<40; i++)
        {
            receiverList.movebyCOGSOG();
            receiverList.senseNoise(realJammer);
            JammerParticles.evalWeights(receiverList);
            JammerParticles.Resample();
            JammerParticles.PrintResults();

        }



    }
}
