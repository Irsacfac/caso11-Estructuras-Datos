package estructuras;

public class LinkBM{
	private int elemento;
	private LinkBM prev;
	private LinkBM next;
	
	public LinkBM(int pElemento){
		elemento = pElemento;
		prev = null;
		next = null;
	}
	
	public int getElemento() {
		return elemento;
	}

	public LinkBM getPrev() {
		return prev;
	}
	
	public void setPrev(LinkBM pPrev) {
		prev = pPrev;
	}

	public LinkBM getNext() {
		return next;
	}
	
	public void setNext(LinkBM pNext) {
		next = pNext;
	}
	
	public String toString() {
		return "" + this.elemento;
	}
}
