/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (first == null){
			return null;
		}
		Node holder = first;
		for (int i = 0; i < index; i++) {
			holder = holder.next;
		}
		return holder;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node newNode = new Node(block);
		if (index == 0) {
			newNode.next = first;
			first = newNode;
			if (size == 0) {last = first;}
		}
		else if (index == size) {
			last.next = newNode;
			last = newNode;
		}
		else {
			Node prev = getNode(index - 1);
			newNode.next = prev.next;
			prev.next = newNode;
		}
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);
		if (last == null) {
			last = newNode;
			first = last;
		}
		else {
			last.next = newNode;
			last = newNode;
		}
		size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);
		newNode.next = first;
		first = newNode;
		if (size == 0) {
			last = first;
		}
		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (getNode(index) == null) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		return getNode(index).block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node current = first;
		for (int i = 0; i < size; i++) {
			if (current.block.equals(block)){
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (this.first == node) {
			first = first.next;
		}
		else if (this.last == node) {
			Node prev = getNode(size - 2);
			prev.next = null;
			last = prev;
		}
		else {
			int index = indexOf(node.block);
			getNode(index - 1).next = getNode(index + 1);
		}
		size--;
		if (size == 0) {
			last = null;
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		remove(getNode(index));
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		remove(getNode(index));
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String linkedList = "";
		for (int i = 0; i < size; i++) {
			linkedList = linkedList  + getNode(i).toString();
		}
		return linkedList;
	}

	public static void main(String[] args) {
		MemoryBlock[] memoryBlockList = new MemoryBlock[20];
        for (int i = 0; i < 20; i++) {
            memoryBlockList[i] = new MemoryBlock(i, i + 1);
        }
        LinkedList list = new LinkedList();
        list.addFirst(memoryBlockList[0]);
        System.out.println(list); //(0,1)
        Node node = list.getNode(0);
        System.out.println(node); //(0,1)
        list.addFirst(memoryBlockList[1]);
        System.out.println(list); //(1,2) , (0,1)
        int indexTest = list.indexOf(memoryBlockList[0]);
        System.out.println(indexTest); // 1
        list.addLast(memoryBlockList[2]);
        System.out.println(list); //(1,2) , (0,1), (2,3)
		MemoryBlock toTest = list.getBlock(2);
		System.out.println(toTest); // (2,3)
        list.add(1, memoryBlockList[3]);
        System.out.println(list); //(1,2) , (3,4) , (0,1), (2,3)
        // list.add(5, memoryBlockList[3]);//error
        // list.add(-1, memoryBlockList[3]);//error
        list.remove(1);
        System.out.println(list); //(1,2) , (0,1), (2,3)
        list.remove(memoryBlockList[0]);
        System.out.println(list); //(1,2) , (2,3)
        // int t = list.getSize(); 
        // list.remove(3); //error
        // list.remove(-1);//error

	}
}