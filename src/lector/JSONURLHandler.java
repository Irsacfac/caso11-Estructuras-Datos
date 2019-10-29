package lector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import otros.IConstants;
import otros.Word;

import java.util.ArrayList;
import java.util.regex.*;

public class JSONURLHandler implements IHtmlTags, IConstants {

    /*
    Singleton Pattern
     */
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


    /*
    Public Methods
     */

    public ArrayList<Word> getWordsWithoutRepetitions(String pURL){
        String text = getText(pURL);
        ArrayList<String> words = splitIntoWords(text);
        ArrayList<Word> wordsWithoutRepetitions = new ArrayList<>();
        for (String word : words){
            if (exists(word, wordsWithoutRepetitions)) getWord(word, wordsWithoutRepetitions).incrementRepetitions();
            else wordsWithoutRepetitions.add(new Word(word));
        }
        return wordsWithoutRepetitions;
    }

    public ArrayList<String> getAllURLs(){
        ArrayList<String> urls = new ArrayList<>();
        JSONObject URLs = JSONReader.getInstance().parseJson(JSONFile);
        JSONArray URLArray = (JSONArray)URLs.get("URLs");
        for (int index = 0; index < URLArray.size(); index++) {
            JSONObject url = (JSONObject) URLArray.get(index);
            urls.add((String)url.get("url"));
        }
        return urls;
    }


    /*
    Private Methods
     */


    private boolean exists(String pWord, ArrayList<Word> words){
        boolean exists = false;
        for (Word word : words){
            if (word.getWord().equals(pWord)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private Word getWord(String pWord, ArrayList<Word> words){
        for (Word word : words){
            if (word.getWord().equals(pWord)) {
                return word;
            }
        }
        return null;
    }


    private ArrayList<String> splitIntoWords(String pText){
        ArrayList<String> allMatches = new ArrayList<>();
        Matcher matcher = Pattern.compile(WORD_REGEX).matcher(pText);
        while (matcher.find()) {

            if (matcher.group().length() >= 4){
                System.out.println(matcher.group());
                allMatches.add(matcher.group().toLowerCase());
            }

        }
        return allMatches;
    }

    private String getOuterHtml(String pURL){

        return JSoup.getInstance().parseURL(pURL);
    }

    private String getText(String pURL){
        String allMatches = "";
        Matcher matcher = Pattern.compile(PARAGRAPH_REGEX).matcher(getOuterHtml(pURL));
        while (matcher.find()) {
            allMatches += matcher.group().substring(START_OF_TEXT_IN_P_TAGS, matcher.group().length()-END_OF_P_TAGS) + " | ";
        }
        return allMatches;
    }



    private int getProfundidadOrAnchura(String pURL, String pAnchuraOrProfundidad){
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
