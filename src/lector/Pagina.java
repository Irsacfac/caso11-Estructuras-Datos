package lector;

import java.util.ArrayList;

public class Pagina {
	private String URL;
	private ArrayList<String> topCinco;
	
	public Pagina(String pURL) {
		URL = pURL;
	}

	public String getURL() {
		return URL;
	}

	public ArrayList<String> getTopCinco() {
		return topCinco;
	}
	
	public void addTop(String pPalabra) {
		if(topCinco.size()<5) {
			topCinco.add(pPalabra);
		}
	}
	public void setTopCinco(ArrayList<String> pTopCinco) {
		if(pTopCinco.size()==5 && topCinco.size()==0) {
			topCinco.addAll(pTopCinco);
		}
	}
}
