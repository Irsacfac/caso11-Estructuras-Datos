package estructuras;

import java.util.Vector;

public class HojaBM {
	private int size;
	private final int M;
	private HojaBM next;
	private Union padre;
	private Vector<LinkBM> data;
	
	public HojaBM(LinkBM pKey, int m, Union pPadre, HojaBM pNext, HojaBM pPrev) {
		M = m;
		size = 1;
		data = new Vector<LinkBM>();
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
	
	protected LinkBM getLast() {
		return this.data.lastElement();
	}
	
	protected LinkBM getFirst() {
		return this.data.firstElement();
	}
	
	protected int insert(LinkBM pKey) {
		int ans = Integer.MAX_VALUE;
		boolean parar = false;
		int pos;
		for(pos = 0;((pos < this.data.size()) && !parar); pos++) {
			if((this.data.elementAt(pos)).getElemento() > pKey.getElemento()){
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
			int gap1 = this.data.elementAt(pos+1).getElemento() - this.data.elementAt(pos).getElemento();
			int gap2 = this.data.elementAt(pos).getElemento() - this.data.elementAt(pos-1).getElemento();
			ans = Math.min(gap1, gap2);
		}else if((pos == 0) && (pos != this.data.size())) {
			pKey.setNext(this.data.elementAt(pos));
			pKey.setPrev(this.data.elementAt(pos).getPrev());
			if(this.data.elementAt(pos).getPrev() != null) {
				this.data.elementAt(pos).getPrev().setNext(pKey);
			}
			this.data.elementAt(pos).setPrev(pKey);
			this.data.add(pos, pKey);
			int gap1 = this.data.elementAt(pos+1).getElemento() - this.data.elementAt(pos).getElemento();
			int gap2;
			if(this.data.elementAt(pos).getPrev() != null) {
				gap2 = this.data.elementAt(pos).getElemento() - (this.data.elementAt(pos).getPrev()).getElemento();
			}
			else {
				gap2 = Integer.MAX_VALUE;
			}
			ans = Math.min(gap1, gap2);
		}else if((pos != 0) && (pos == this.data.size())) {
			pKey.setNext(this.data.elementAt(pos-1).getNext());
			pKey.setPrev(this.data.elementAt(pos-1));
			this.data.elementAt(pos-1).setNext(pKey);
			if(pKey.getNext() != null){
				pKey.getNext().setPrev(pKey);
			}
			this.data.add(pKey);
			int gap1;
			if(this.data.elementAt(pos).getNext() != null) {
				gap1 = this.data.elementAt(pos).getNext().getElemento() - this.data.elementAt(pos).getElemento();
			}else {
				gap1 = Integer.MAX_VALUE;
			}
			int gap2 = this.data.elementAt(pos).getElemento() - this.data.elementAt(pos-1).getElemento();
			ans = Math.min(gap1, gap2);
		}
		this.size = this.data.size();
		return ans;
	}
	
	protected boolean overflow() {
		return (this.size > this.M-1);
	}
	
	protected HojaBM split(Union pPadre) {
		int mitad = this.size -this.size/2;
		HojaBM nuevaHoja = new HojaBM(this.data.remove(mitad), this.M, pPadre, this.next, this);
		this.size = this.data.size();
		nuevaHoja.getFirst().setPrev(this.data.elementAt(mitad-1));
		while(mitad < this.size) {
			nuevaHoja.insert(this.data.remove(mitad));
			this.size = this.data.size();
		}
		this.next = nuevaHoja;
		return nuevaHoja;
	}
	
	public LinkBM encontrar(int pElemento) {
		for(int pos = 0;pos < this.data.size();pos++) {
			if(pElemento <= this.data.elementAt(pos).getElemento()) {
				return this.data.elementAt(pos);
			}
		}
		return null;
	}
	
	public Vector<LinkBM> getData(){
		return this.data;
	}
	
	public HojaBM getNext() {
		return this.next;
	}
	
	protected void setNext(HojaBM pNext) {
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
