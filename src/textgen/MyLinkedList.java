package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head=new LLNode<E>(null);
		tail=new LLNode<E>(null);
		head.setNext(tail);
		tail.setPrev(head);
		
		this.size=0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> a= new LLNode<E>(element);
		if(this.size==0){
			a.setNext(tail);
			a.setPrev(head);
			head.setNext(a);
			tail.setPrev(a);
			this.size++;
		}
		else{
		tail.getPrev();
	//	System.out.println( "//////  "+a.getData());
	//	System.out.println( "//////  "+tail.getPrev().getData());
		
		a.setNext(tail);
		a.setPrev(tail.getPrev());
		tail.getPrev().setNext(a);
		tail.setPrev(a);
		//System.out.println( "//////  "+a.getData());
		//System.out.println( "//////  "+a.getNext().getData());
		//System.out.println( "//////  "+a.getPrev().getData());
		
		this.size++;
		//System.out.println( "//////  "+this.get(1));
		}
		//System.out.println( this.get(0));
		//System.out.println( this.size);
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		E c=null;
		if ( this.size<=index || index<0  ){throw new IndexOutOfBoundsException();}
		
		else {
		LLNode<E> a=head.getNext();
		for(int i=0; i<this.size+1;i++){
		
			if(i==index){c=a.getData();
				return c;
			}
			else{a=a.getNext();}
		}}
		return c;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */                                                                            
	public void add(int index, E element ) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if (this.size()==0 && index==0){
			this.add(element);
		}
		if ( this.size<=index || index<0  ){throw new IndexOutOfBoundsException();}
		LLNode<E> e= new LLNode<E>(element);
		LLNode<E> a=head.getNext();
		//LLNode<E> result;
		System.out.println(" e is   :  "+ e.getData());
		System.out.println(" the size is   :  "+ this.size());
		for(int i=0; i<this.size;i++){
			
			if(i==index){
				
				e.setPrev(a.getPrev());
				e.setNext(a);
				a.getPrev().setNext(e);
				a.setPrev(e);
				
				this.size++;
				//System.out.println(" e is   :  " + e.getNext().getData());
				//System.out.println(" e is   :  "+ e.getPrev().getData());
				//System.out.println(" the size is   :  "+ this.size());
				}
			else{a=a.getNext();}
		}
		
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{		if ( this.size()<=index || index<0  ){throw new IndexOutOfBoundsException();}
		// TODO: Implement this method
		LLNode<E> a=head.getNext();	
		E c= null;
		for(int i=0; i<index+1;i++){
			
			if(i==index){
				a.getNext().setPrev(a.getPrev());
				a.getPrev().setNext(a.getNext());
				c=a.getData();
				this.size--;
				
				return c;
			}
			else{a=a.getNext();}
			}
		return c;
		}
		
	
	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IllegalArgumentException
	{
		if ( element==null ){throw new IllegalArgumentException();}
		else if ( this.size<=index || index<0  ){throw new IndexOutOfBoundsException();}
		// TODO: Implement this method
		LLNode<E> a=head.getNext();
		for(int i=0; i<index+1;i++){
			
			if(i==index){
				a.setData(element);
				return element;
			}
			a=a.getNext();
			}
		return null;
			}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
	
		setData(e);
		this.setPrev( null);
		this.setNext(null); 
	}
	
   public E getData()
   {
	   return this.data;
   }
   public void setData(E e)
   {
	   this.data=e;
	   
   }
   public LLNode<E> getPrev(){
	   
	   return this.prev;
   }
   
  public LLNode<E> getNext(){
	   
	   return this.next;
   }
   
  public  void setNext(LLNode<E> a){
	   
	   this.next=a;
  }
  
  public  void setPrev(LLNode<E> a){
	   
	   this.prev=a;
  }

}