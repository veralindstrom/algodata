/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

import java.io.IOException;
import static java.lang.System.out;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public class CircularLinkedList<Datatype> {
	   // Node structure
		private class Node{
			Datatype data;
			Node next;
		}
		
		Node head;
		Node last;
		int size;

	    // Constructor for the stack
		// Initialized at:
	    public CircularLinkedList() {
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
	    
	    // Represent list items starting from head going to next
            @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        
	        if(head == null)
	            return "[empty]";
	        
	        // Not use global head -> create local head
	        Node localhead = head;
	        
	        for (int i = getSize(); i > 0; i--) {
	        	
	        	sb.append("[");
                sb.append(localhead.data.toString());
                sb.append("],");
                localhead = localhead.next;
	        }
	        if (sb.length()>0)
	        	sb.deleteCharAt(sb.length() - 1);
	        
	        return sb.toString();
	    }
	    
	    
	    // ENQUEUE method inserts a new item
	    public void enqueue (Datatype item) throws IOException {
	    	
	    	// create node to insert item in
	    	Node temp = new Node();
	    	temp.data = item;
	  
	    	// if queue is empty
	    	if (head == null) 
	    		newEnqueue(temp);
	    	
	    	// if queue is not empty
	    	else {
	    		// Enqueue front or back
		    	out.println("Enqueue [" + item.toString() + "] in front (1) or enqueue back (2)");
				Scanner input = new Scanner(System.in);
				int place = input.nextInt();
		    	switch (place) {
		    		case 1: 
		    			frontEnqueue(temp);
		    			break;
		    		case 2:
		    			backEnqueue(temp);
		    			break;
		    		default:
		    			break;
		    	}
	    	}
	    	size++;
	    	out.println("\nElement: [" + item.toString() + "] has been enqueued");
	    }
	    
	    public void newEnqueue (Node temp) {
	    	temp.next = temp;
    		head = last = temp;
    		head.next = last.next = temp;
	    }
	    
        public void frontEnqueue (Node temp) {
	    	temp.next = head;
	    	last.next = temp;
	    	head = temp;
	    }
        
        public void backEnqueue (Node temp) {	
	    	temp.next = head;
	    	last.next = temp;
	    	last = temp;
	    }
	    
	    // DEQUEUE method removes first or last item
	    public void dequeue () throws IOException {
	    	
	    	// check if queue is empty
	    	if (size == 0) // or size == 0
	    		throw new NoSuchElementException("List is empty");
	    	
	    	Node temp = new Node();

	    	// Dequeue front or back
	    	out.println("Dequeue front (1) or dequeue back (2)");
	    	Scanner input = new Scanner(System.in);
			int place = input.nextInt();
	    	switch (place) {
	    		case 1: 
	    			frontDequeue(temp);
	    			break;
	    		case 2:
	    			backDequeue(temp);
	    			break;
	    		default:
	    			break;
	    	}
	    	size--;
	    }
	    
	    public Datatype frontDequeue (Node temp) {	
	    	// pick out first item
	    	temp = head;
	    	Datatype item = temp.data;
	    	// reassign references to new first item
	    	head = temp.next = head.next;
	    	last.next = temp.next = head;
	    	temp.next = null; // to avoid loitering
	    	out.println("\nElement: [" + item.toString() + "] has been dequeued");
	    	return item;
	    }
	    
	    public Datatype backDequeue (Node temp) {	
	    	// find last item
	    	while(last.next != head)
	    		last = last.next;
	    	// pick out last item
	    	temp = last;
	    	Datatype item = temp.data;
	    	// reassign references to new last item
	    	while(last.next != temp)
	    		last = last.next;
	    	last.next = head; 
	    	temp.next = null; // to avoid loitering
	    	out.println("\nElement: [" + item.toString() + "] has been dequeued");
	    	return item;
	    }
	    
}

