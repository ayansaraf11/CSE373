package datastructures.concrete;

import datastructures.interfaces.IList;
import misc.exceptions.EmptyContainerException;
import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;
// add previous node to add method


/**
 * Note: For more info on the expected behavior of your methods, see
 * the source code for IList.
 */
public class DoubleLinkedList<T> implements IList<T> {
    // You may not rename these fields or change their types.
    // We will be inspecting these in our private tests.
    // You also may not add any additional fields.
    private Node<T> front;
    private Node<T> back;
    private int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }
    
   

    @Override
    public void add(T item) {
    		Node newNode = new Node(item);
    		//add and if back  == null
    		if(front == null) {
    			// delete this 
    			if(back !=null) {
    				throw new AssertionError("back is occupied");
    			}
    			front = newNode;
    			back = front;
    			front.prev = null;
    			this.size++;
    			
    		}
    		else {
    			back.setNextNode(newNode);
    			newNode.setNextNode(null);
    			newNode.prev = back;
    			back = newNode;
    			this.size++;
    			
    		}
    		
    }
    
    /**
     * Removes and returns the item from the *end* of this IList.
     *
     * @throws EmptyContainerException if the container is empty and there is no element to remove.
     */
    
    // remove from back node instead

    @Override
    public T remove() {
    		if(this.size==0) {
    			throw new EmptyContainerException();
    		}
    		Node lastNode = front;
    		Node previousToLast = null;
    		if(front.next == null) {
    			front = null;
    			back = null;
    		}
    		while(lastNode.next != null) {
    			previousToLast = lastNode;
    			lastNode = lastNode.next;
    		}
    		if(front !=null) {
    			previousToLast.next = null;
    		}
    		this.size--;    		
    		return (T) lastNode.data;
    }

    
    @Override
    public T get(int index) {
    	if(index <0 || index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
    	Node current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return (T) current.data;
    }

    /**
     * Overwrites the element located at the given index with the new item.
     *
     * @throws IndexOutOfBoundsException if the index < 0 or index >= this.size()
     */
    @Override
    public void set(int index, T item) {
    		if(index <0 || index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
    		Node current = front;
    		if(index == 0) {
    			if(front.next!=null) {
    				front = new Node(item);
    				front.next = current.next;
    			}
    			else {
    				front = new Node(item);
    			}
    			return;
    		}
    		int counter = 1;
    		Node previous = front;
    		current = current.next;
    		while(counter <= index) {
    			if(counter == index) {
    				Node currentBeforeReplace = current;
    				if(current.next != null) {
        				current = new Node(item);
        				previous.next = current;
        				current.next = currentBeforeReplace.next;
    				}
    				else {
    					current = new Node(item);
        				previous.next = current;
        				current.next = null;
    				}
    				break;
    			}
    			current = current.next;
    			previous = previous.next;
    			counter++;
    		}
    		
    }
    
    /**
     * Inserts the given item at the given index. If there already exists an element
     * at that index, shift over that element and any subsequent elements one index
     * higher.
     *
     * @throws IndexOutOfBoundsException if the index < 0 or index >= this.size() + 1
     */

    @Override
    public void insert(int index, T item) {
		//throw new NotYetImplementedException();
		if(index < 0 || index >= this.size()+1) {
			throw new IndexOutOfBoundsException();
		}
		Node previousHead = front;
		if(index == 0) {
			Node newHead = new Node(item);
			front = newHead;
			front.next = previousHead;
			this.size++;
		}
		else {
			int counter = 1;
			Node prevoiusNode = front;
			Node currentNode = front.next;
			while(counter <= index) {
				if(counter == index) {
					if(index > this.size-1) {
						Node newNode = new Node(item);
						prevoiusNode.next = newNode;
						newNode.next = null;
						this.size++;
						break;
				}
					
					if(currentNode.next != null) {
						Node newNode = new Node(item);
						prevoiusNode.next = newNode;
						newNode.next = currentNode;
						this.size++;
					}
					else {
						Node newNode = new Node(item);
						prevoiusNode.next = newNode;
						newNode.next = null;
						this.size++;
					}
				}
				prevoiusNode = currentNode;
				currentNode = currentNode.next;
				counter++;
			}
		}
//		if(index < 0 || index >= this.size()+1) {
//		throw new IndexOutOfBoundsException();
//		}
//		Node previousHead = front;
//		if(index == 0) {
//			System.out.print("hello");
//			if(front == null) {
//				
//				Node newHead = new Node(item);
//				
//				front = newHead;
//				
//				front.next = null;
//				front.prev = null;
//				back = front;
//				
//			}
//			else {
//				
//				if(front.next == null) {
//					Node newHead = new Node(item);
//					front = newHead;
//					front.next = null;
//					front.prev = null;
//				}
//				else {
//					Node newHead = new Node(item);
//					front = newHead;
//					front.next = previousHead;
//					front.prev = null;
//					previousHead.prev = front;
//				}
//			}
//			
//			
//			this.size++;
//			System.out.println(this.size());
//		}
//		else {
//			System.out.println("not 0");
//			if(index > this.size()/2) {
//				System.out.println("index greater than half");
//				System.out.println(this.size());
//				Node newNode = new Node(item);
//				if(index == this.size()) {
//					if(this.size() == 1) {
//						newNode.prev = front;
//						newNode.next = null;
//						back = newNode;
//						this.size++;
//						return;
//					}
//					else {
//						newNode.prev = back;
//						newNode.next = null;
//						System.out.println("index greater than half");
////						if(back == null) {
////							
////						}
//						back.next = newNode;
//						
//						back = newNode;
//						this.size++;
//						return;
//					}
//					
//				}
//				else {
//					Node nextNodeFromBack = back;
//					Node nodeBefore = back.prev;
//					int counter = this.size()-1;
//					while(nextNodeFromBack.prev !=null) {
//						if(counter == index) {
//							if(counter == this.size()-1) {
//								newNode.next = nextNodeFromBack;
//								newNode.prev = nodeBefore;
//								back = newNode.next;
//								back.prev = newNode;
//								this.size++;
//							}
//							else {
//								newNode.next = nextNodeFromBack;
//								newNode.prev = nodeBefore;
//								nodeBefore.next = newNode;
//								nextNodeFromBack.prev = newNode;
//								this.size++;
//							}
//						
//						}
//						nextNodeFromBack = nextNodeFromBack.prev;
//						nodeBefore = nodeBefore.prev;
//						counter--;
//					}
//					
//				}
//			}
//			else {
//				System.out.println("not 0 and index not greater than half");
//	    		if(index == 0) {
//	    			Node newHead = new Node(item);
//	    			front = newHead;
//	    			front.next = previousHead;
//	    			this.size++;
//	    		}
//	    		else {
//	    			int counter = 1;
//	    			Node prevoiusNode = front;
//	    			Node currentNode = front.next;
//	    			while(counter <= index) {
//	    				if(counter == index) {
//	    					if(index > this.size-1) {
//	    						Node newNode = new Node(item);
//	    						prevoiusNode.next = newNode;
//	    						newNode.next = null;
//	    						this.size++;
//	    						break;
//						}
//							
//	    					if(currentNode.next != null) {
//	    						Node newNode = new Node(item);
//	    						prevoiusNode.next = newNode;
//	    						newNode.next = currentNode;
//	    						this.size++;
//	    					}
//	    					else {
//	    						Node newNode = new Node(item);
//	    						prevoiusNode.next = newNode;
//	    						newNode.next = null;
//	    						this.size++;
//	    					}
//	    				}
//	    				prevoiusNode = currentNode;
//	    				currentNode = currentNode.next;
//	    				counter++;
//	    			}
//	    		}
//			}
//		}
		

		
    }

    /**
     * Deletes the item at the given index. If there are any elements located at a higher
     * index, shift them all down by one.
     *
     * @throws IndexOutOfBoundsException if the index < 0 or index >= this.size()
     */
    
    @Override
    public T delete(int index) {
    	if(index <0 || index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
    		Node current = front;
    		if(index == 0) {
    			if(front.next!=null) {
    				front = front.next;
    				front.prev = null;
    			}
    			else {
    				front = null;
    			}
    			this.size--;
    			return (T) current;
    		}
    		int counter = 1;
    		Node previous = front;
    		current = current.next;
    		while(counter <= index) {
    			if(counter == index) {
    				Node currentBeToBeDeleted = current;
    				if(current.next != null) {
    					if(current.prev !=null) {
        					current.prev.next = current.next;
    						current.next.prev = current.prev;
    						this.size--;
    					}
    					else {
    						if(this.size!=1) {
    							previous.next = previous.next.next;
    							previous.next.prev = previous;
    							current = null;
    							this.size--;
    							return (T) current;
    						}
    						else {
    							front = current.next;
        						current = null;
        						front.prev = null;
        						
    						}
    						this.size--;
    					}
    				}
    				else {
    					
    					current = null;
    					this.size--;
    				}
    				
    				break;
    			}
    			current = current.next;
    			previous = previous.next;
    			counter++;
    		}
    		return (T) current;
    }
    
    /**
     * Returns the index corresponding to the first occurrence of the given item
     * in the list.
     *
     * If the item does not exist in the list, return -1.
     */

    @Override
    public int indexOf(T item) { 
    		int index = 0;
    		Node currentNode = front;
    		while(currentNode !=null) {
    			if(currentNode.data == item) {
    				return index;
    			}
    			if(currentNode.data.equals(item)) {
    				return index;
    			}
    			index++;
    			currentNode = currentNode.next;
    		}
    		return -1;
    }

    @Override
    public int size() {
    		return this.size;
        //throw new NotYetImplementedException();
    }
    
    /**
     * Returns 'true' if this container contains the given element, and 'false' otherwise.
     */

    @Override
    public boolean contains(T other) {
		Node currentNode = front;
		while(currentNode !=null) {
			if(currentNode.data == other) {
				return true;
			}
			if(currentNode.data.equals(other)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
    }
    
    

    @Override
    public Iterator<T> iterator() {
        // Note: we have provided a part of the implementation of
        // an iterator for you. You should complete the methods stubs
        // in the DoubleLinkedListIterator inner class at the bottom
        // of this file. You do not need to change this method.
        return new DoubleLinkedListIterator<>(this.front);
    }

    private static class Node<E> {
        // You may not change the fields in this node or add any new fields.
        public final E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(E data) {
            this(null, data, null);
        }

        // Feel free to add additional constructors or methods to this class.
        
        //ADDITIONAL METHODS
        private E getData() {
        		return this.data;
        }
        private void setNextNode(Node n) {
        		this.next = n;
        }
        private Node getNextNode() {
        		return this.next;
        }
      //END OF ADDITIONAL METHODS
        
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> current;

        public DoubleLinkedListIterator(Node<T> current) {
            // You do not need to make any changes to this constructor.
            this.current = current;
        }

        /**
         * Returns 'true' if the iterator still has elements to look at;
         * returns 'false' otherwise.
         */
        public boolean hasNext() {
        	//System.out.println(current.data);
        		if(current == null) {
        			System.out.println("null ");
        			return false;
        		}
        		return current != null;
           //throw new NotYetImplementedException();
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() {
        		
			
        		if(current != null) {
        			T data = current.data;
        			current = current.next;
        			return data;
        		}
        		else {
        			throw new NoSuchElementException();
        		}
			
            //throw new NotYetImplementedException();
        }
    }
}
