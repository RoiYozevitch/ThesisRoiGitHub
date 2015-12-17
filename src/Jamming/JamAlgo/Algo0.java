package Jamming.JamAlgo;

import Jamming.data.DataSample;
import Jamming.data.DataSampleCollection;

/**
 * Created by Roi on 8/4/2015.
 */
public class Algo0 {

//todo should i define a class for problistic rings?

    public void computeEventsProb(DataSampleCollection data)
    {
        for(DataSample tmp : data.getSamples())
        {
            tmp.setSNREventProb(0);
        }

    }

}
