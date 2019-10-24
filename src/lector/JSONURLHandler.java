package lector;

public class JSONURLHandler {

    private JSONReader jsonReader;
    private JSoup jSoup;

    public JSONURLHandler() {

        this.jsonReader = JSONReader.getInstance();
        this.jSoup = JSoup.getInstance();

    }

    

}
