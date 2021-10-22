/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

import java.io.IOException;
import static java.lang.System.out;

/**
 *
 * @author Vera
 */
public class MainDoubleLinkedQueue {
    public static void main(String[] args) throws IOException {
		
		// Iterative version
	    out.println("ITERATIVE implementation of FIFO-queue:");
		out.println("Write something:");
		iterative();
		
		// create a character list
	    out.println("Implementation of FIFO queue:");
	    DoubleLinkedQueue<Character> queue = new DoubleLinkedQueue<Character>();
		out.println("Queue size: " + queue.getSize());
		queue.print();
		
		// ENQUEUE queue with characters
		queue.enqueue('H');
		queue.print();
		queue.enqueue('e');
		queue.print();
		out.println("Queue size: " + queue.getSize());
		queue.enqueue('j');
		queue.print();
		out.println("Queue size: " + queue.getSize());
		
		// DEQUEUE queue of characters
		queue.dequeue();
		queue.print();
		out.println("Queue size: " + queue.getSize());
		queue.dequeue();
		queue.dequeue();
		queue.print();
		out.println("Queue size: " + queue.getSize());
		
		// Dequeue empty queue
		out.println("Dequeue an empty queue:");
		queue.dequeue();
		queue.print();
		out.println("Queue size: " + queue.getSize());	
	}
	
	    // ITERATIVE implementation of queue - FIFO
		private static void iterative() throws IOException  {
			DoubleLinkedQueue<Character> queue = new DoubleLinkedQueue<Character>();
			char c = (char) System.in.read();
			
			while(charIsValid(c)) {
				queue.enqueue(c);
	    		c = (char) System.in.read();
	    		queue.print();
	    	}
	    	
	    	for(int i = queue.getSize(); i > 0; i--) {
	    		queue.dequeue();
	    		queue.print();
	    	}
	    }	
		
		private static void alsoiterative() throws IOException  {
			DoubleLinkedQueue<Character> queue = new DoubleLinkedQueue<Character>();
			char c = (char) System.in.read();
			
			while(!charNotValid(c)) {
				queue.enqueue(c);
	    		c = (char) System.in.read();
	    		queue.print();
	    	}
	    	
	    	while(!queue.isEmpty()) {
	    		queue.dequeue();
	    	    queue.print();
	    	}
	    	
	    }
		
		// If char is "new-line" return false
	    public static boolean charIsValid (char c) {
	    	if (c == '\n')
	    		return false;
	    	else
	    		return true;	
	    }
	    
	    // If char is "new-line" return true
	    public static boolean charNotValid (char c) {
	    	return (c == (char) -1) || (c == '\n');	
	    }
}
