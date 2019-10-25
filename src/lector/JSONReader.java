package lector;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {


    private static JSONReader mInstance;

    private JSONReader() {
    }

    public static JSONReader getInstance() {
        if (mInstance == null) {
            mInstance = new JSONReader();
        }
        return mInstance;
    }

    public JSONObject parseJson(String pFilePath){
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(pFilePath);
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

}
