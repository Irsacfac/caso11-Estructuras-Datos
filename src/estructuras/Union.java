package estructuras;

import java.util.Vector;

public class Union<T> {
	private int size;
	private final int M;
	private Union<T> padre;
	private Vector<LinkBM<T>> elementos;
	private Vector<Object> hijos;
	private int numDeElemntos;
	
	public Union(Vector<LinkBM<T>> pKeys, int m, Union<T> pPadre, Vector<Object> pHijos) {
		padre = pPadre;
		elementos = pKeys;
		hijos = pHijos;
		numDeElemntos = 0;
		this.size = pKeys.size();
		M = m;
		for(int pos =0;pos < pHijos.size();pos++) {
			Object temp = pHijos.elementAt(pos);
			if(temp instanceof HojaBM) {
				this.numDeElemntos += ((HojaBM<T>)(temp)).getSize();
			}else {
				this.numDeElemntos += ((Union<T>)(temp)).numDeElementos();
			}
		}
	}
	
	protected boolean overflow() {
		return this.size > this.M - 1;
	}
	
	protected Union<T> split(Union<T> pPadre) {
		int punteroMitad;
		if(this.hijos.size()%2 == 0) {
			punteroMitad = this.hijos.size() - this.hijos.size()/2;
		}else {
			punteroMitad = this.hijos.size() - this.hijos.size()/2 - 1;
		}
		int mitadElementos = this.elementos.size() - this.elementos.size()/2;
		Vector<LinkBM<T>> nuevoElementos = new Vector<LinkBM<T>>();
		Vector<Object> nuevoHijos = new Vector<Object>();
		while(mitadElementos < this.elementos.size()) {
			nuevoElementos.add(this.elementos.remove(mitadElementos));
		}
		while(punteroMitad < this.hijos.size()) {
			nuevoHijos.add(this.hijos.remove(punteroMitad));
		}
		this.numDeElemntos = 0;
		for(int pos = 0;pos < this.hijos.size();pos++) {
			if(this.hijos.elementAt(pos) instanceof HojaBM) {
				this.numDeElemntos += ((HojaBM<T>)this.hijos.elementAt(pos)).getSize();
			}else {
				this.numDeElemntos += ((Union<T>)this.hijos.elementAt(pos)).numDeElemntos;
			}
		}
		Union<T> nuevaUnion = new Union<T>(nuevoElementos, this.M, pPadre, nuevoHijos);
		for(int pos = 0;pos < nuevoHijos.size();pos++) {
			Object temp = nuevoHijos.elementAt(pos);
			if(temp instanceof HojaBM) {
				HojaBM<T> tempHoja = (HojaBM<T>)temp;
				tempHoja.setPadre(nuevaUnion);
			}else {
				Union<T> tempUnion = (Union<T>)temp;
				tempUnion.setPadre(nuevaUnion);
			}
		}
		return nuevaUnion;
	}
	
	protected void splitHijo(Object node) {
		int location = 0;
		boolean encontrado = false;
		while(!encontrado) {
			if(this.hijos.elementAt(location) == node) {
				encontrado = true;
			}
			else {
				location++;
			}
		}
		if(this.hijos.elementAt(location) instanceof HojaBM) {
			splitHoja(location);
		}else {
			splitUnion(location);
		}
		this.size = this.elementos.size();
	}

	protected void splitHoja(int pLocation) {
		// TODO Auto-generated method stub
		HojaBM<T> toSplit = (HojaBM<T>)(this.hijos.elementAt(pLocation));
		HojaBM<T> nuevaHoja = toSplit.split(this);
		if(pLocation < this.elementos.size()) {
			this.elementos.add(pLocation, toSplit.getLast());
			this.hijos.add(pLocation+1, nuevaHoja);
			this.elementos.setElementAt(nuevaHoja.getLast(), pLocation+1);
		}else {
			this.elementos.add(toSplit.getLast());
			this.hijos.add(nuevaHoja);
		}
	}
	
	protected void splitUnion(int pLocation) {
		Union<T> toSplit = (Union<T>)this.hijos.elementAt(pLocation);
		Union<T> nuevaUnion = toSplit.split(this);
		if(pLocation < this.elementos.size()) {
			this.elementos.add(pLocation, toSplit.getLast());
			this.hijos.add(pLocation+1, nuevaUnion);
		}else {
			this.elementos.add(toSplit.getLast());
			this.hijos.add(nuevaUnion);
		}
		toSplit.setSize(toSplit.getElementos().size());
		nuevaUnion.setSize(toSplit.getElementos().size());
		toSplit.removeLastElement();
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Vector<LinkBM<T>> getElementos(){
		return this.elementos;
	}
	
	public Vector<Object> getHijos(){
		return this.hijos;
	}
	
	public LinkBM<T> getLast() {
		return this.elementos.lastElement();
	}
	
	public Union<T> getPadre() {
		return this.padre;
	}
	
	protected void setPadre(Union<T> pPadre) {
		padre = pPadre;
	}
	
	protected void setSize(int pSize) {
		size = pSize;
	}
	
	protected void removeLastElement() {
		if(this.hijos.size() == this.elementos.size()) {
			this.elementos.remove(this.elementos.size()-1);
		}
		this.size = this.elementos.size();
	}
	
	protected Object find(Comparable pPos, boolean isInsercion) {
		if(isInsercion) {
			this.numDeElemntos++;
		}
		for(int pos = 0;pos < this.elementos.size();pos++) {
			if(this.elementos.elementAt(pos).getLlave().compareTo(pPos) >= 0) {
				return this.hijos.elementAt(pos);
			}
		}
		return this.hijos.lastElement();
	}
	
	protected int numDeElementos() {
		return this.numDeElemntos;
	}
	
	public String toString() {
		String ans = "";
		for(int pos = 0;pos <this.elementos.size();pos++) {
			ans += this.elementos.elementAt(pos) + ",";
		}
		return ans;
	}
}
