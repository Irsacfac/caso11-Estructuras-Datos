package estructuras;

import java.util.Vector;

public class HojaBM<T> {
	private int size;
	private final int M;
	private HojaBM<T> next;
	private Union padre;
	private Vector<LinkBM<T>> data;
	
	public HojaBM(LinkBM<T> pKey, int m, Union pPadre, HojaBM<T> pNext, HojaBM<T> pPrev) {
		M = m;
		size = 1;
		data = new Vector<LinkBM<T>>();
		next = pNext;
		padre = pPadre;
		if((pPrev != null) && (pNext != null)) {
			this.data.add(pKey);
			pKey.setNext(pNext.getFirst());
			pKey.setPrev(pPrev.getLast());
			pPrev.setNext(this);
		}else if((pPrev == null) && (pNext != null)) {
			this.data.add(pKey);
			pKey.setNext(pNext.getFirst());
			pKey.setPrev(null);
		}else if((pPrev != null) && (pNext == null)) {
			this.data.add(pKey);
			pKey.setNext(null);
			pKey.setPrev(pPrev.getLast());
			pPrev.setNext(this);
		}else {
			this.data.add(pKey);
			pKey.setNext(null);
			pKey.setPrev(null);
		}
	}
	
	protected LinkBM<T> getLast() {
		return this.data.lastElement();
	}
	
	protected LinkBM<T> getFirst() {
		return this.data.firstElement();
	}
	
	protected void insert(LinkBM<T> pKey) {
		int ans = Integer.MAX_VALUE;
		boolean parar = false;
		int pos;
		for(pos = 0;((pos < this.data.size()) && !parar); pos++) {
			if((this.data.elementAt(pos)).getLlave().compareTo(pKey.getLlave()) > 0){
				parar = true;
			}
		}
		if(parar) {
			pos-=1;
		}
		if((pos != 0) && (pos != this.data.size())) {
			pKey.setNext(this.data.elementAt(pos));
			pKey.setPrev(this.data.elementAt(pos-1));
			this.data.elementAt(pos-1).setNext(pKey);
			this.data.elementAt(pos).setPrev(pKey);
			this.data.add(pos, pKey);
		}else if((pos == 0) && (pos != this.data.size())) {
			pKey.setNext(this.data.elementAt(pos));
			pKey.setPrev(this.data.elementAt(pos).getPrev());
			if(this.data.elementAt(pos).getPrev() != null) {
				this.data.elementAt(pos).getPrev().setNext(pKey);
			}
			this.data.elementAt(pos).setPrev(pKey);
			this.data.add(pos, pKey);
		}else if((pos != 0) && (pos == this.data.size())) {
			pKey.setNext(this.data.elementAt(pos-1).getNext());
			pKey.setPrev(this.data.elementAt(pos-1));
			this.data.elementAt(pos-1).setNext(pKey);
			if(pKey.getNext() != null){
				pKey.getNext().setPrev(pKey);
			}
			this.data.add(pKey);
		}
		this.size = this.data.size();
	}
	
	protected boolean overflow() {
		return (this.size > this.M-1);
	}
	
	protected HojaBM<T> split(Union pPadre) {
		int mitad = this.size -this.size/2;
		HojaBM<T> nuevaHoja = new HojaBM<T>(this.data.remove(mitad), this.M, pPadre, this.next, this);
		this.size = this.data.size();
		nuevaHoja.getFirst().setPrev(this.data.elementAt(mitad-1));
		while(mitad < this.size) {
			nuevaHoja.insert(this.data.remove(mitad));
			this.size = this.data.size();
		}
		this.next = nuevaHoja;
		return nuevaHoja;
	}
	
	public LinkBM<T> encontrar(Comparable pLlave) {
		for(int pos = 0;pos < this.data.size();pos++) {
			if(this.data.elementAt(pos).getLlave().compareTo(pLlave) <= 0) {
				return this.data.elementAt(pos);
			}
		}
		return null;
	}
	
	public Vector<LinkBM<T>> getData(){
		return this.data;
	}
	
	public HojaBM<T> getNext() {
		return this.next;
	}
	
	protected void setNext(HojaBM<T> pNext) {
		this.next = pNext;
	}
	
	public Union getPadre() {
		return this.padre;
	}
	
	protected void setPadre(Union pPadre) {
		this.padre = pPadre;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String toString() {
		String ans = "";
		for(int pos = 0;pos < this.data.size();pos++) {
			ans+= this.data.elementAt(pos) + ",";
		}
		return ans.substring(0,ans.length()-1);
	}
}
