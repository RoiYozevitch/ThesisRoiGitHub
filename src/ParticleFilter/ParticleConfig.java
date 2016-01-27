package ParticleFilter;

import java.util.Random;

/**
 * Created by Roi on 12/21/2015.
 */
public class ParticleConfig {

    static final int NumberOfParticles = 1000;
    public static final double VelocityGauusianError=0.1;
    public  static final double VelocityHeadingError=0.5;
    // static final int NumberOfParticles=5;
    private double MovingNoise=0;
    private double TurnNoise = 0;
    private int SenseNoise = 0;
    static int weightPow;
    Random R1;
}
