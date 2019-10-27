import lector.JSONURLHandler;

import java.util.ArrayList;

public class URL {

    private String url;
    private String content;
    private ArrayList<Palabra> wordsSet;

    public URL(String pUrl) {
        url = pUrl;
        content = JSONURLHandler.getInstance().getParagraphs(url);
        wordsSet = new ArrayList<>();
    }

    private ArrayList<Palabra> setWordsSet(){

    }







}
