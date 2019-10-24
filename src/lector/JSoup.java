package lector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoup {

    public static JSoup getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static String parseURL(String pURL){
        try {
            final Document document = Jsoup.connect(pURL).get();
            System.out.println(document.outerHtml());
            return document.outerHtml();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static class SingletonHolder {
        private static final JSoup INSTANCE = new JSoup();
    }
}
