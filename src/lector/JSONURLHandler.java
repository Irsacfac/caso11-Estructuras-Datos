package lector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import otros.IConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class JSONURLHandler implements IHtmlTags, IConstants {

    private static JSONURLHandler mInstance;
    private String JSONFile;

    private JSONURLHandler(String pJSONFile) {
        JSONFile = pJSONFile;
    }

    public static JSONURLHandler getInstance() {
        if (mInstance == null) {
            mInstance =
                    new JSONURLHandler(
                            "C:/Users/jguty/OneDrive/Documents/GitHub/caso11-Estructuras-Datos/src/lector/jsonURL.json");
        }
        return mInstance;
    }


    private String getOuterHtml(String pURL){

        return JSoup.getInstance().parseURL(pURL);
    }

    public String getParagraphs(String pURL){
        String allMatches = "";
        Matcher matcher = Pattern.compile(PARAGRAPH_REGEX).matcher(getOuterHtml(pURL));
        while (matcher.find()) {
            allMatches += matcher.group().substring(START_OF_TEXT_IN_P_TAGS, matcher.group().length()-END_OF_P_TAGS) + " | ";
        }
        return allMatches;
    }

    public int getProfundidadOrAnchura(String pURL, String pAnchuraOrProfundidad){
        JSONObject URLs = JSONReader.getInstance().parseJson(JSONFile);
        JSONArray URLArray = (JSONArray)URLs.get("URLs");
        for (int index = 0; index < URLArray.size(); index++){
            JSONObject url = (JSONObject)URLArray.get(index);
            System.out.println(url.get("url"));
            System.out.println(pURL);

            System.out.println(url.get(pAnchuraOrProfundidad));
            if (url.get("url").equals(pURL))

            return (int)(long)url.get(pAnchuraOrProfundidad);

        }
        return -1;
    }
}
