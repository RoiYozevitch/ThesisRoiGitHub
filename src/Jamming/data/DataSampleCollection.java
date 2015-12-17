package Jamming.data;

import Geometry.Point3D;

import java.util.ArrayList;

/**
 * Created by Roi on 8/4/2015.
 */
public class DataSampleCollection {
    ArrayList<DataSample> samples;

    public DataSampleCollection(ArrayList<DataSample> samples) {
        this.samples = samples;
    }

    public ArrayList<DataSample> getSamples() {
        return samples;
    }

    public ArrayList<DataSample> extractByImeu(long imeu)
    {
        return null;
    }
    public ArrayList<DataSample> extractByPos(Point3D pos, double dist)
    {
        return null;
    }

    public ArrayList<DataSample> extractByTime(long  time, double margin)
    {
        return null;
    }

    public void addSample(DataSample newSample)
    {
        samples.add(newSample);
    }

}
