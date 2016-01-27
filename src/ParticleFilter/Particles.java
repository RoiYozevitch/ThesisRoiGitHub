package ParticleFilter;

import Geometry.Point3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Roi on 12/21/2015.
 */
public class Particles {

    private List<Particle> ParticleList;

    public List<Particle> getParticleList() {
        return ParticleList;
    }

    public void setParticleList(List<Particle> particleList) {
        ParticleList = particleList;
    }

  /*  public void Resample()
    {
        double[] Weight;// Normal_Weights()
        double max=0;
        for(int i=0; i<ParticleConfig.NumberOfParticles; i++)
            max+=Weight[i];
        // System.out.println("sum of weights is "+max);
        double beta=0.0;
        Random R1 = new Random();
        List<Point3D> NewWeightedList = new ArrayList<Point3D>();
        int index = (int)(R1.nextDouble()* ParticleConfig.NumberOfParticles);
        double mw = GetMax(Weight);
        for(int i=0; i<ParticleConfig.NumberOfParticles; i++)
        {
            beta+=R1.nextDouble()*2*mw;
            while(beta>Weight[index])
            {
                beta-= Weight[index];
                index = (index+1)%ParticleConfig.NumberOfParticles;

            }
            Point3D tmp = new Point3D(ParticleList.get(index).getLocPoint());
            tmp.OldWeight = ParticleList.get(index).OldWeight;
            // System.out.print(tmp.pos + " ");
            // tmp.setWeight(ParticleList.get(index).getWeight());
            NewWeightedList.add(tmp);
        }
        SetAfterResample(NewWeightedList);
    }

*/
    public Point3D GetOptimalParticle(Point3D lastPar)
    {

        double w=this.ParticleList.get(0).getParticleWeight();
        double tmpW=0;
        double thres=5;
        int index=0;
        int i;
        for( i=0; i<ParticleConfig.NumberOfParticles; i++)

            tmpW = this.ParticleList.get(i).getParticleWeight();
        if(tmpW>w)
        {
            w=tmpW;
            index=i;
        }

        //  System.out.print(w+ " ");
        double x=0, y=0, z=0,p=0;
        Point3D tmp = new Point3D(lastPar);
        // tmp.offset(actionFunction.PivotX, actionFunction.PivotY,0);

        for( i=0; i<ParticleConfig.NumberOfParticles; i++)
        {

            double dist = this.ParticleList.get(i).getLocPoint().distance(lastPar);
            if (dist < thres) {
                double tmpWeight = ParticleList.get(i).getParticleWeight();
                if (tmpWeight != 0) {
                    tmpWeight = tmpWeight / w;
                    double cw = Math.pow(tmpWeight, ParticleConfig.weightPow);

                    p += cw;
                    x += cw * ParticleList.get(i).locPoint.getX();
                    y += cw * ParticleList.get(i).locPoint.getY();
                    z += cw * ParticleList.get(i).locPoint.getZ();
                }
            }

        }
        x=x/p;y=y/p;z=z/p;
        Point3D ans = new Point3D(x,y,z);

        return ans;
    }

}
