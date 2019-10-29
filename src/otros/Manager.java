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
		window = new Ventana("Buscador", this);
		arbolRango = new ArbolBmas<>(7);
		arbolPalabra = new ArbolBmas<>(10);
		arbolTop = new ArbolBmas<>(7);
		urlHandler = JSONURLHandler.getInstance();
		URLs = urlHandler.getAllURLs();
		this.llenarArboles();
	}

	private ArrayList<String> interseccion(ArrayList<ArrayList<String>> pArrayLists){
		ArrayList<String> interseccion = interseccionEntreDos(pArrayLists.get(0), pArrayLists.get(1));
		if (pArrayLists.size() > 2){
			for (int index = 2; index < pArrayLists.size(); index++){
				interseccion = interseccionEntreDos(interseccion, pArrayLists.get(index));
			}
		}
		return interseccion;
	}

	private ArrayList<String> interseccionEntreDos(ArrayList<String> pLista1, ArrayList<String> pLista2){
		ArrayList<String> interseccion = new ArrayList<>();
		for (String t: pLista1) {
			if(pLista2.contains(t)) {
				interseccion.add(t);
			}
		}
		return interseccion;
	}

	
	public ArrayList<String> buscarURL(String pURL) {
		return arbolTop.buscar(pURL).getElemento();
	}
	
	public ArrayList<String> buscarRango(int pMin, int pMax){
		LinkBM<ArrayList<String>> resultado = arbolRango.buscar(pMin);
		while((resultado == null) && pMin <= pMax) {
			pMin++;
			resultado = arbolRango.buscar(pMin);
		}
		return null;
	}
	
	public ArrayList<String> buscarPalabra(String pPalabra){
		return arbolPalabra.buscar(pPalabra).getElemento();
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
		int repeticiones;
		while(pContenido.size() != 0) {
			repeticiones = pContenido.get(0).getRepetitions();
			if(top5.getRepetitions() < repeticiones) {
				top1 = top2;
				top2 = top3;
				top3 = top4;
				top4 = top5;
				top5 = pContenido.remove(0);
			}else if(top4.getRepetitions() < repeticiones || top4 == null) {
				top1 = top2;
				top2 = top3;
				top3 = top4;
				top4 = pContenido.remove(0);
			}else if(top3.getRepetitions() < repeticiones || top3 == null) {
				top1 = top2;
				top2 = top3;
				top3 = pContenido.remove(0);
			}else if(top2.getRepetitions() < repeticiones || top2 == null) {
				top1 = top2;
				top2 = pContenido.remove(0);
			}else if(top1.getRepetitions() < repeticiones || top1 == null) {
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
