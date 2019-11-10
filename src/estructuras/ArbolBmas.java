package estructuras;

import java.util.Vector;

public class ArbolBmas<T>{
	
	
	private Object raiz;
	private final int M;
	//private int minGap;
	
	public ArbolBmas(int pOrder){
		raiz = null;
		M = pOrder;
		//this.minGap = Integer.MAX_VALUE;
	}
	
	public void insert(T pElemento) {
		this.insert(pElemento, (Comparable)pElemento);
	}
	
	public void insert(T pElemento, Comparable pLlave) {
		if(raiz == null) {
			LinkBM<T> nuevoLink = new LinkBM<T>(pElemento,pLlave);
			raiz = new HojaBM<T>(nuevoLink, M, null, null, null);
		}
		else {
			if(raiz instanceof HojaBM) {
				((HojaBM<T>)raiz).insert(new LinkBM<T>(pElemento,pLlave));
				if(((HojaBM<T>)this.raiz).overflow()) {
					splitRaiz();
				}
			}else {
				Object lugarInsercion = ((Union<T>)this.raiz).find(pLlave, true);
				if(lugarInsercion instanceof HojaBM) {
					HojaBM<T> insertPlace = (HojaBM<T>)lugarInsercion;
					insertPlace.insert(new LinkBM(pElemento,pLlave));
					if(insertPlace.overflow()) {
						((Union<T>)raiz).splitHijo(lugarInsercion);
					}
					if(((Union<T>)raiz).overflow()) {
						splitRaiz();
					}
				}
				else {
					HojaBM temp = buscadorAuxiliar(pLlave, lugarInsercion, true);
					temp.insert(new LinkBM(pElemento,pLlave));
					if(temp.overflow()) {
						(temp.getPadre()).splitHijo(temp);
					}
					Union tempUnion = temp.getPadre();
					boolean parar = false;
					while((!parar) && (tempUnion != this.raiz)) {
						if(tempUnion.overflow()) {
							(tempUnion.getPadre()).splitHijo(tempUnion);
							tempUnion = tempUnion.getPadre();
						}else {
							parar = true;
						}
					}
					if(tempUnion == this.raiz) {
						splitRaiz();
					}
				}
			}
		}
	}

	private void splitRaiz() {
		// TODO Auto-generated method stub
		if(this.raiz instanceof HojaBM) {
			HojaBM nuevaHoja = ((HojaBM)this.raiz).split(null);
			Vector<LinkBM> nuevoElemento = new Vector<LinkBM>();
			nuevoElemento.add(((HojaBM)this.raiz).getLast());
			Vector<Object> nuevosHijos = new Vector<Object>();
			nuevosHijos.add(this.raiz);
			nuevosHijos.add(nuevaHoja);
			Union nuevaRaiz = new Union(nuevoElemento, M, null, nuevosHijos);
			((HojaBM)raiz).setPadre(nuevaRaiz);
			nuevaHoja.setPadre(nuevaRaiz);
			raiz = nuevaRaiz;
		}else {
			Union nuevaUnion = ((Union)raiz).split(null);
			Vector<LinkBM> nuevoElemento = new Vector<LinkBM>();
			nuevoElemento.add(((Union)raiz).getLast());
			Vector<Object> nuevosHijos = new Vector<Object>();
			nuevosHijos.add(raiz);
			nuevosHijos.add(nuevaUnion);
			Union nuevaRaiz = new Union(nuevoElemento, M, null, nuevosHijos);
			for(int pos = 0;pos < nuevosHijos.size();pos++) {
				Object temp = nuevosHijos.elementAt(pos);
				if(temp instanceof HojaBM) {
					HojaBM tempHoja = (HojaBM)temp;
					tempHoja.setPadre(nuevaRaiz);
				}else {
					Union tempUnion = (Union)temp;
					tempUnion.setPadre(nuevaRaiz);
				}
			}
			((Union)this.raiz).setPadre(nuevaRaiz);
			nuevaUnion.setPadre(nuevaRaiz);
			((Union)raiz).removeLastElement();
			raiz = nuevaRaiz;
		}
	}
	
	public LinkBM<T> buscar(Comparable pLlave) {
		if(raiz == null) {

			return null;
		}
		HojaBM<T> nodo = buscadorAuxiliar(pLlave, raiz, false);
		LinkBM<T> temp = ((HojaBM)nodo).encontrar(pLlave);
		if(temp == null) {

			return null;
		}
		if(temp.getLlave().compareTo(pLlave) == 0) {
			return temp;
		}

		return null;
	}
	
	private HojaBM buscadorAuxiliar(Comparable pLlave, Object pNodo, boolean isInsercion) {
		// TODO Auto-generated method stub
		while(!(pNodo instanceof HojaBM)) {
			pNodo = ((Union)pNodo).find(pLlave, isInsercion);
		}
		return (HojaBM)pNodo;
	}

	public String printTree() {
		String ans = "";
		Object nodo = this.raiz;
		while(!(nodo instanceof HojaBM)) {
			nodo = (((Union)nodo).getHijos()).elementAt(0);
		}
		HojaBM nodoHoja = (HojaBM)nodo;
		while(nodoHoja != null) {
			ans += (nodoHoja.toString()).substring(0, (nodoHoja.toString()).length()) + "#";
			nodoHoja = nodoHoja.getNext();
		}
		return ans.substring(0, ans.length()-1);
	}
}
