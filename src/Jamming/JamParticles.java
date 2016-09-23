package Jamming;

import Geometry.Point3D;
import ParticleFilter.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Roi on 9/23/2016.
 */
public class JamParticles {

    private final int numberOfParticles = 200;

    private List<JammerParticle> jamList;


    public void NaiveInit(double x1, double y1, double x2, double y2)
    {
        for (int i=0; i<numberOfParticles;i++)
        {
            JammerParticle tmp = new JammerParticle(x1,y1,x2,y2);
            jamList.add(tmp);
        }
    }

    private double[] Normal_Weights()
    {
        double MaxWeight=0;
        List<Double> doubleWeight = new ArrayList<Double>();
        double[] Weights=new double[numberOfParticles];
        for(JammerParticle tmp : this.jamList)
        {
            MaxWeight+=tmp.getWeight();
            doubleWeight.add(tmp.getWeight());
        }



        int i=0;
        for(JammerParticle tmp : this.jamList)
        {
            Weights[i]=tmp.getWeight()/MaxWeight;
            i++;
        }

        return Weights;
    }

    public double GetMax(double[] Weights)
    {
        double max=Weights[0];
        for(int i=1;i<Weights.length; i++)
        {
            if(Weights[i]>=max)
                max=Weights[i];
        }
        return  max;
    }

    public void Resample()
    {
        double[] Weight = Normal_Weights();
        double max=0;
        for(int i=0; i<numberOfParticles; i++)
            max+=Weight[i];
        // System.out.println("sum of weights is "+max);
        double beta=0.0;
        Random R1 = new Random();
        List<JammerParticle> NewWeightedList = new ArrayList<JammerParticle>();
        int index = (int)(R1.nextDouble()* numberOfParticles);
        double mw = GetMax(Weight);
        for(int i=0; i<numberOfParticles; i++)
        {
            beta+=R1.nextDouble()*2*mw;
            while(beta>Weight[index])
            {
                beta-= Weight[index];
                index = (index+1)%numberOfParticles;

            }
            JammerParticle tmp = new JammerParticle(jamList.get(index));
            // tmp.OldWeight = ParticleList.get(index).OldWeight;
            // System.out.print(tmp.pos + " ");
            // tmp.setWeight(ParticleList.get(index).getWeight());
            NewWeightedList.add(tmp);
        }
        for(int i=0; i<numberOfParticles; i++)
            jamList.get(i).Update(NewWeightedList.get(i));
    }

    public void evalWeights(ClientList receiverList) {

        for(int i=0;i<numberOfParticles;i++)
        {
            this.jamList.get(i).evalWeight(receiverList);

        }
    }

    public void PrintResults(JammerParticle realJammer) {

        for(int i=0;i<numberOfParticles;i++)
        {
            this.jamList.get(i).PrintResults(realJammer);

        }

    }
}
