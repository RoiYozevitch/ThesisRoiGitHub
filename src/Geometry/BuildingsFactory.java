package Geometry;

import Utils.GeoUtils;

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
   public boolean generateUTMBuildingListfromKMLfile(String file, int numberOfLinesPlacemarkToCord)
   {


       try {
           BufferedReader reader = new BufferedReader(new FileReader(file));
           String line= "";
           line = reader.readLine();
           while(line!=null)
           {
               if(line.startsWith("<Placemark")) {
                   for (int tmp = 0; tmp <= numberOfLinesPlacemarkToCord; tmp++)
                       line = reader.readLine();

                   Building tmpBuilding = generateUTMBuildingFromCordString(line);
                   buildingList.add(tmpBuilding);
               }
               line = reader.readLine();
           }


       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }


       return true;

   }

    public Building generateUTMBuildingFromCordString(String cordFromKMLString)
    {
        String[] cords = cordFromKMLString.split(",| ");
        List<Point3D> buildingVertices = new ArrayList<Point3D>();
        int size = cords.length;
        int tmp=0;
        Double x, y, z;
        while(tmp<size-2)
        {
            y = Double.parseDouble(cords[tmp]);
            x = Double.parseDouble(cords[tmp+1]);
            z= Double.parseDouble(cords[tmp+2]);
            Point3D tmpPoint = new Point3D(x, y, z);
            tmpPoint = GeoUtils.convertLATLONtoUTM(tmpPoint);
            buildingVertices.add(tmpPoint);
            tmp+=3;

        }
        int Size = buildingVertices.size();
        if(buildingVertices.get(Size-1)== buildingVertices.get(0))
            buildingVertices.remove(Size-1); //remove the last point since it similar to the first point
        Building ans = new Building();
        ans.generateBuildingFromPoint3dList(buildingVertices);
        ans.setNumberOfWalls(ans.getWalls().size());
        ans.setMaxHeight();

        return ans;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public Integer getNumberOfBuildings() {
        return numberOfBuildings;
    }


}
