package ParticleFilter;

import Geometry.Point3D;

/**
 * Created by Roi on 12/21/2015.
 */
public class Particle {

     Point3D locPoint;
     double particleWeight;
     double velocityHeading;
     double velocityMagnitude;
     double oldWeight;


    public Particle(Point3D locPoint, double particleWeight, double velocityHeading, double velocityMagnitude) {
        this.locPoint = locPoint;
        this.particleWeight = particleWeight;
        this.velocityHeading = velocityHeading;
        this.velocityMagnitude = velocityMagnitude;
        oldWeight = 0;
    }

    public Particle(double x, double y, double z)
    {
        this.locPoint = new Point3D(x, y, z);

    }

    public void MoveParticle(double veNoise, double heNoise )
    {
        double PivotX = Math.cos(this.getVelocityHeading())*this.getVelocityMagnitude();
        double PivotY =Math.sin(this.getVelocityHeading())*this.getVelocityMagnitude();

        this.locPoint.offset(PivotX, PivotY, 0);
    }

    public void  MoveParticle()
    {
      //  double PivotX = Math.cos(action.heading)*action.velocity;
      //  double PivotY =Math.sin(action.heading)*action.velocity;
        //  System.out.println(PivotX+ " "+PivotY);
      //  this.locPoint.offset(PivotX, PivotY, 0);
    }

    public Point3D getLocPoint() {
        return locPoint;
    }

    public void setLocPoint(Point3D locPoint) {
        this.locPoint = locPoint;
    }

    public double getParticleWeight() {
        return particleWeight;
    }

    public void setParticleWeight(double particleWeight) {
        this.particleWeight = particleWeight;
    }

    public double getVelocityHeading() {
        return velocityHeading;
    }

    public void setVelocityHeading(double velocityHeading) {
        this.velocityHeading = velocityHeading;
    }

    public double getVelocityMagnitude() {
        return velocityMagnitude;
    }

    public void setVelocityMagnitude(double velocityMagnitude) {
        this.velocityMagnitude = velocityMagnitude;
    }
}
