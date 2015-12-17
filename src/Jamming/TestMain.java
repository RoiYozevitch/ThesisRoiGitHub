package Jamming;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by Roi on 8/4/2015.
 */
public class TestMain {

    private enum Type{
        INT, DOUBLE, STRING, LONG, BOOLEAN, FLOAT;
    }

    private static Map<Integer, Type> indToType;
    static{
        indToType.put(0, Type.STRING);
    }


    public static MongoDatabase getDB(){
        MongoCredential credential = MongoCredential.createCredential("roi_tau", "jammingdata", "basic1234".toCharArray());
        MongoClient mongo = new MongoClient(new ServerAddress("ds059722.mongolab.com:59722"), Arrays.asList(credential));
        return mongo.getDatabase("jammingdata");
    }


    public static void main(String[] args) throws UnknownHostException {
        getData();
    }

    private static  void getData(){
        MongoDatabase db = getDB();
        MongoCollection<Document> collection = db.getCollection("testdata");
        FindIterable<Document> docs = collection.find(new Document(" C/No Max","30"));
        for (Document doc : docs){
            System.out.println(doc.toString());
        }

    }

    private static void insertData() {
        MongoDatabase jammingDataDB = getDB();
        jammingDataDB.createCollection("testdata");
        MongoCollection<Document> data = jammingDataDB.getCollection("testdata");
        System.out.println(data.count());
        try {
            String fileName = "testDataForMongo.csv";
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileName), "UTF8"));
            String[] header = br.readLine().split(",");
            String line;
            List<Document> allNewDocs = new ArrayList<Document>();
            while ((line = br.readLine()) != null){
                allNewDocs.add(makeDocumentFromLine(line, header));
            }
            data.insertMany(allNewDocs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Document makeDocumentFromLine(String line, String[] header){
        String[] data = line.split(",");
        Document result = new Document();
        for (int i = 0; i < header.length; i++){
            Type type = indToType.get(i);
            switch (type){
                case INT:
                    result.append(header[i] , Integer.parseInt(data[i]));
                    break;
                case FLOAT:
                    result.append(header[i] , Float.parseFloat(data[i]));
                    break;
                case STRING:
                    result.append(header[i] , data[i]);
                    break;

            }
        }
        return result;
    }
}
