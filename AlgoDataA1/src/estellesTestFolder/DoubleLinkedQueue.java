/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

import static java.lang.System.out;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 */
public class DoubleLinkedQueue<Datatype> {
    
	    // Node structure
		private class Node{
			Datatype data;
			Node next;
			Node prev;
		}
		
		Node head;
		int size;

	    // Constructor for the stack
		// Initialized at:
	    public DoubleLinkedQueue() {
	    	this.head = null;
	    	this.size = 0;
	    }
	    
	    // Get the size of the stack
	    public int getSize() {
	    	return size;
	    }
	    
	    // Check if FIFO queue is empty
	    public boolean isEmpty() {
	    	 return head == null;
	    }
	    
	    // Print string representation of queue
	    public void print(){
	        System.out.println(toString());
	    }
	    
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        
	        if(head == null)
	            return "[empty]";
	
	        // FIFO -> last item in queue = out first
	        Node last = head.prev;
	        
	        for (int i = getSize(); i > 0; i--) {
	        	
	        	sb.append("[");
                sb.append(last.data.toString());
                sb.append("],");
                last = last.prev;
	        }
	        if (sb.length()>0)
	        	sb.deleteCharAt(sb.length() - 1);
	        
	        return sb.toString();
	    }
	    
	    
	    // ENQUEUE method inserts a new item
	    public void enqueue (Datatype item) {
	    	
	    	// create node to insert item in
	    	Node temp = new Node();
	    	temp.data = item;
	  
	    	// if queue is empty
	    	if (head == null) {
	    		temp.next = temp.prev = temp;
	    	}
	    	// if queue is not empty
	    	else {
	    		Node last = head.prev;
	    		temp.next = head;
	    		temp.prev = last;
	    		head.prev = last.next = temp;
	    	}
	    	head = temp;
	    	size++;
	    	out.println("\nElement: [" + item.toString()+ "] has been enqueued");
	    }
	    
	    // DEQUEUE method removes first inserted item
	    public Datatype dequeue () {
	    	
	    	// check if queue is empty
	    	if (size == 0) // or size == 0
	    		throw new NoSuchElementException("Queue is empty");
	    	
	    	// pick out the last item as FIFO
	    	Node last = head.prev;
	    	Datatype item = last.data;
	    	
	    	// update references
	    	last.prev.next = head;
	    	head.prev = last.prev;
	    	last = null; // to avoid loitering
	    	
	    	size--;
	    	out.println("\nElement: [" + item.toString()+ "] has been dequeued");
	    	return item;
	    }
}
