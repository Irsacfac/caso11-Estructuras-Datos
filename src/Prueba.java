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
		final String url = "https://www.uber.com/es-CR/blog/lugares-para-visitar-en-liberia-guanacaste/";
		final String url2 = "https://www.uber.com/es-CR/blog/lugares-para-visitar-en-san-carlos/";

		ArrayList<String> matches = JSONURLHandler.getInstance().splitIntoWords(JSONURLHandler.getInstance().getText(url));
		System.out.println("hola mundo !!");






	}

}
