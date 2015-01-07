package Geometry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roi on 1/7/2015.
 */
public class BuildingsFactory {

    List<Building> buildingList;
    Integer numberOfBuildings;

    public BuildingsFactory() {
        this.buildingList = new ArrayList<Building>();
    }

  //this function return true is the list was created and false if an error occurs.
   public boolean generateBuildingListfromKMLfile(String file, int numberOfLinesPlacemarkToCord)
   {


       try {
           BufferedReader reader = new BufferedReader(new FileReader(file));
           String line= "";
           line = reader.readLine();
           while(line!=null)
           {
               if(line.startsWith("<Placemark"))
               {

               }
           }


       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }


       return true;

   }
    public Building generateBuildingFromCordString(String CordFromKML)
    {
        Building ans = null;
        return ans;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public Integer getNumberOfBuildings() {
        return numberOfBuildings;
    }


}
