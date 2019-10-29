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
		LinkBM<ArrayList<String>> arrayURLs = arbolPalabra.buscar(pRango);
		if(arrayURLs == null) {
			ArrayList<String> array = new ArrayList<String>(); 
			array.add(pURL);
			arbolPalabra.insert(array, pRango);;
		}else {
			arrayURLs.getElemento().add(pURL);
		}
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
		while(pContenido.size() != 0) {
			if(top5.getRepetitions() < pContenido.get(0).getRepetitions()) {
				top1 = top2;
				top2 = top3;
				top3 = top4;
				top4 = top5;
				top5 = pContenido.remove(0);
			}else if(top4.getRepetitions() < pContenido.get(0).getRepetitions() || top4 == null) {
				top1 = top2;
				top2 = top3;
				top3 = top4;
				top4 = pContenido.remove(0);
			}else if(top3.getRepetitions() < pContenido.get(0).getRepetitions() || top3 == null) {
				top1 = top2;
				top2 = top3;
				top3 = pContenido.remove(0);
			}else if(top2.getRepetitions() < pContenido.get(0).getRepetitions() || top2 == null) {
				top1 = top2;
				top2 = pContenido.remove(0);
			}else if(top1.getRepetitions() < pContenido.get(0).getRepetitions() || top1 == null) {
				top1 = pContenido.remove(0);
			}
		}
		ArrayList<String> top = new ArrayList<>();
		top.add(top5.getWord());
		top.add(top4.getWord());
		top.add(top3.getWord());
		top.add(top2.getWord());
		top.add(top1.getWord());
		arbolTop.insert(top, pURL);
	}
}
