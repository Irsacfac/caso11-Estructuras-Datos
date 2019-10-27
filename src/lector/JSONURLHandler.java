package lector;

import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class JSONURLHandler implements IHtmlTags{

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

    public String getParagraphs(String pURL){
        String allMatches = "";
        Matcher m = Pattern.compile(PARAGRAPH_REGEX).matcher(getOuterHtml(pURL));
        while (m.find()) {
            allMatches += m.group().substring(3, m.group().length()-4) + " | ";
        }
        return allMatches;
    }


}
