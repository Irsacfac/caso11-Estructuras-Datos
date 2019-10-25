package lector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoup {

    private static JSoup mInstance;

    private JSoup() {
    }

    public String parseURL(String pURL){
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


    public static JSoup getInstance() {
        if (mInstance == null) {
            mInstance = new JSoup();
        }
        return mInstance;
    }
}
