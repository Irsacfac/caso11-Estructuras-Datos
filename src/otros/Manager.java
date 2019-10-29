package otros;

import java.util.ArrayList;

import estructuras.ArbolBmas;
import estructuras.LinkBM;
import gui.Ventana;
import lector.JSONURLHandler;

public class Manager {
	private Ventana window;
	private ArbolBmas<ArrayList<String>> arbolRango;
	private ArbolBmas<ArrayList<String>> arbolPalabra;
	private ArbolBmas<ArrayList<String>> arbolTop;
	private JSONURLHandler urlHandler;
	private ArrayList<String> URLs;
	
	public Manager() {
		window = new Ventana("Buscador");
		arbolRango = new ArbolBmas<>(7);
		arbolPalabra = new ArbolBmas<>(10);
		arbolTop = new ArbolBmas<>(7);
		urlHandler = JSONURLHandler.getInstance();
		URLs = urlHandler.getAllURLs();
		this.llenarArboles();
	}
	
	private void llenarArboles() {
		ArrayList<Word> actualList;
		String actualURL;
		Word actualWord;
		for(int actual = 0; actual < URLs.size();actual++) {
			actualURL = URLs.get(actual);
			actualList = urlHandler.getWordsWithoutRepetitions(actualURL);
			llenarTop(actualURL, actualList);
			for(int pos = 0;pos < actualList.size();pos++) {
				actualWord = actualList.get(pos);
				llenarRango(actualWord.getRepetitions(), actualURL);
				llenarPalabra(actualWord.getWord(), actualURL);
			}
		}
	}
	
	private void llenarRango(int pRango, String pURL) {
		
	}
	
	private void llenarPalabra(String pPalabra, String pURL) {
		LinkBM<ArrayList<String>> arrayURLs = arbolPalabra.buscar(pPalabra);
		if(arrayURLs == null) {
			ArrayList<String> array = new ArrayList<String>(); 
			array.add(pURL);
			arbolPalabra.insert(array, pPalabra);;
		}else {
			arrayURLs.getElemento().add(pURL);
		}
	}
	
	private void llenarTop(String pURL, ArrayList<Word> pContenido) {
		Word top1 = null;
		Word top2 = null;
		Word top3 = null;
		Word top4 = null;
		Word top5 = pContenido.remove(0);
		for(int pos = 0;pos < pContenido.size();pos++) {
			
		}
		ArrayList<String> top = new ArrayList<>();
		arbolTop.insert(top, pURL);
	}
}
