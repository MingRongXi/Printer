package printer;


/**
 * This class realize the add data, remove data, modify data and find data.
 * @author ZhaoLei
 *
 * @param <E>  Any type
 */
public class LinkList<E> implements Iterable<E>{
	/**
	 * The basic element of LinkList, storing the data and the next element
	 * @author dell
	 *
	 * @param <E>
	 */
	public static class Node<E>{
		/**
		 * The information stored
		 */
		private E data;
		/**
		 * The next element
		 */
		private Node<E> link;
		public Node(E data, Node<E> link){
			this.data = data;
			this.link = link;
		}
		
		/**
		 * @return the data
		 */
		public E getData() {
			return data;
		}
	}
	
	/**
	 * The header of LinkList
	 */
	private Node<E> headNode;
	
	/**
	 * The tail of LinkList
	 */
	private Node<E> tailNode;
	
	/**
	 * The number of the elements in the LinkList
	 */
	private int size = 0;
	
	/**
	 * Implement the iterator
	 */
	public java.util.Iterator<E> iterator(){
		return new ListIterator();
	}
	
	public LinkList() {
		headNode = new Node<E>(null, null);
		tailNode = new Node<E>(null, null);
	}
	
	
	/**
	 * Add the data to the tail of LinkList
	 * @param data the information
	 */
	public void add(E data) {
		Node<E> newNode = new Node<E>(data, null);
		/**
		 * If the LinkList is empty, add the node next to the headNode, and it become the last element
		 */
		if(headNode.link == null) {
			headNode.link = newNode;
			tailNode = newNode;
		}
		
		else {
			tailNode.link = newNode;
			tailNode= newNode;
		}
		
		size++;
	}
	
	/**
	 * Add the element at the specified index
	 * @param data  The information
	 * @param index   The specified index in the LinkList
	 */
	public void insert(E data, int index) {
		checkIndex(index);
		
		// If add the index is the tail of the LinkList, call add(data)
		if(index == size) {
			add(data);
		}
		
		else {
		Node<E> newNode = new Node<E>(data, null);
		
		// Get the (index-1)th node
		Node<E> beforeNode = findNode(index);
		newNode.link = beforeNode.link;
		beforeNode.link = newNode;
		size++;
		}
	}
	
	
	/**
	 * Remove the node of the specified index in the LinkList
	 * @param index  The specified index
	 * @return  The information of the node
	 */
	public void remove() {
		headNode.link = headNode.link.link;
		size--;
	}
	
	/**
	 * Get the information of the specified index in the LinkList
	 * @param index
	 * @return the information
	 */
	public E getData() {
		//System.out.println(headNode.link.data);
		if(headNode.link.data != null) {
			return headNode.link.data;

		}
		else
			return null;
	}
	
	
	/**
	 * Get the index of node in the LinkList through the data
	 * @param data
	 * @return the index
	 */
	public int getIndex(E data) {
		int index = 0;
		Node<E> foundNode = headNode;
		while(foundNode.data != data && foundNode.link != null) {
			foundNode = foundNode.link;
			index++;
		}
		
		if(foundNode.link != null) {
			return index;
		}
		throw new ArrayIndexOutOfBoundsException("没有此元素");
	}
	
	
	/**
	 * Modified the element 
	 * @param newData
	 * @param index
	 * @return
	 */
	public E setData(E newData, int index) {
		Node<E> modifiedNode = findNode(index).link;
		E oldData = modifiedNode.data;
		modifiedNode.data = newData;
		
		return oldData;
	}
	
	
	/**
	 * Get the (index-1)th Node
	 * @param index
	 * @return
	 */
	public Node<E> findNode(int index){	
		checkIndex(index);
		int k = 0;
		Node<E> foundNode = headNode;
		while(k < index - 1) {
			foundNode = foundNode.link;
		}
		
		return foundNode;
	}
	
	
	/**
	 * Clear the LinkList
	 */
	public void clear() {
		headNode = tailNode;
		size = 0;
	}
	
	
	public void checkIndex(int index) {
		if(index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		Node<E> currentNode = headNode.link;
		while(currentNode != null) {
			s += currentNode.data + "  ";
			currentNode = currentNode.link;
		}
		return s;
	}

	
	/**
	 * Travel the LinkList
	 * @author dell
	 *
	 */
	public class ListIterator implements java.util.Iterator<E>{
		// The node that pointer points
		private Node<E> current;
		
		public E begin() {
			current = headNode.link;
			if(current != null) {
				E data = current.data;
				return data;
			}
			return null;
		}
		
		
		public E next() {
			if(!hasNext()){
				throw new NullPointerException();
			}
			//必须先将指针下移，然后再得到data。将两条语句颠倒顺序，会少遍历一个元素
			current = current.link;
			if (current != null) {
				E data = current.data;
				return data;
			}
			return null;
		}

				
		public boolean hasNext() {
			//This method is not used to tell the current.link whether is null or not, nut the current.
			return current != null;
		}
		
		
		public int size() {
			int sum = 0;
			this.begin();
			while(this.hasNext()) {
				sum++;
				this.next();
			}
			return sum;
		}
	}
}


	
