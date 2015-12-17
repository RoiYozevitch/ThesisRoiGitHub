package Jamming.data.updatechecker;

import java.io.File;

/**
 * Created by Roi on 8/4/2015.
 */
public class DataUpdateCheckerFromDirectory implements DataUpdateCheckerInterface{

    private final File dir;
    private final long maxDeltaInMS;
    private long lastDateModified;

    public DataUpdateCheckerFromDirectory(File dir, long maxDeltaInMS){
        this.dir = dir;
        this.maxDeltaInMS = maxDeltaInMS;
        this.lastDateModified = 0;
    }

    @Override
    public boolean shouldUpdate() {
        if (dir.lastModified() - lastDateModified > maxDeltaInMS){
            lastDateModified = dir.lastModified();
            return true;
        }
        return false;
    }
}
