import lector.IHtmlTags;
import lector.JSONURLHandler;
import otros.IConstants;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URL implements IHtmlTags, IConstants {

    private String url;
    public String content;
    private ArrayList<Palabra> wordsSet;
    private ArrayList<String> innerURLs;

    public URL(String pUrl) {
        url = pUrl;
        content = JSONURLHandler.getInstance().getParagraphs(url);
        wordsSet = new ArrayList<>();
        innerURLs = new ArrayList<>();
    }

    public void enqueueInnerURLs() {

        Matcher matcher = Pattern.compile(INNER_URL_REGEX).matcher(content);
        int anchura = JSONURLHandler.getInstance().getProfundidadOrAnchura(url, "anchura");

        while (matcher.find() && anchura > 0) {
            innerURLs.add(matcher.group().substring(START_OF_URL_IN_HREF_TAGS,matcher.group().length()- END_OF_HREF_TAGS));
            anchura--;
        }

    }



//    private ArrayList<Palabra> setWordsSet(){
//
//    }






}
