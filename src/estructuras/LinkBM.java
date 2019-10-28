package estructuras;

public class LinkBM<T>{
	private T elemento;
	private Comparable llave;
	private LinkBM<T> prev;
	private LinkBM<T> next;
	
	public LinkBM(T pElemento) {
		this(pElemento, (Comparable) pElemento);
	}
	
	public LinkBM(T pElemento, Comparable pLlave){
		elemento = pElemento;
		llave = pLlave;
		prev = null;
		next = null;
	}
	
	public T getElemento() {
		return elemento;
	}
	
	public Comparable getLlave() {
		return llave;
	}

	public LinkBM<T> getPrev() {
		return prev;
	}
	
	public void setPrev(LinkBM<T> pPrev) {
		prev = pPrev;
	}

	public LinkBM<T> getNext() {
		return next;
	}
	
	public void setNext(LinkBM<T> pNext) {
		next = pNext;
	}
	
	public String toString() {
		return "" + this.elemento;
	}
}
