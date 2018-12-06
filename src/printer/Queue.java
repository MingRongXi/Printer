package printer;

public class Queue<E> {

	private LinkList<E> linkList = new LinkList<>();
	private int size;

	public Queue() {
		
	}
	
	public E top() {
		//System.out.println(linkList.getData());
		return linkList.getData();
	}
	
	public void pop() {
		linkList.remove();
		size--;
		
	}
	
	public void push(E object) {
		linkList.add(object);
		size++;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
