import lector.JSONReader;
import lector.JSONURLHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gui.Ventana;

import java.util.ArrayList;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ventana window = new Ventana();
		final String url = "https://es.wikipedia.org/wiki/Terrorismo";

		ArrayList<String> matches = JSONURLHandler.getInstance().getParagraphs(url);
		System.out.println(matches.get(14));





	}

}
