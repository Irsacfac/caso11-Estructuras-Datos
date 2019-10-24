import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gui.Ventana;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana window = new Ventana();
		final String url = "https://en.wikipedia.org/wiki/Donald_Trump";
		
		try {
			final Document document = Jsoup.connect(url).get();
			
			System.out.println(document.outerHtml());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
