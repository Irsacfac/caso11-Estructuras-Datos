package lector;

import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.regex.*;

public class JSONURLHandler {

    private static JSONURLHandler mInstance;

    private JSONURLHandler() {
    }

    public static JSONURLHandler getInstance() {
        if (mInstance == null) {
            mInstance = new JSONURLHandler();
        }
        return mInstance;
    }

    private String getOuterHtml(String pURL){

        return JSoup.getInstance().parseURL(pURL);
    }


}
