
package algodataa3hg;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public class GeneralizedQueue<Datatype> implements Queue<Datatype> {
    Node head;
    int size;
    
    // Structure of the node 
    private class Node{   
        Datatype data;
        Node next; // Pointer to next node
    }
    
    public Datatype getHead(){
        return head.data;
    }
    
    /**
     * Adds element [item] to front of queue.
     * QueueA1E5: [1][2]
     * => QueueA1E5 after enqueue(): [item][1][2]
     * @param item Item to be added to queue.
     */
    @Override
    public void enqueue(Datatype item){
        //if list is empty
         if (head == null) { 
            Node temp = new Node(); 
            temp.data = item; 
            head = temp; 
            size++;
            
            return; 
        } 
        
        //if list is not empty
        
        //insert to front
        Node temp = new Node();
        temp.data = item;
        
        // setting up next of new node 
        temp.next = head;
        head = temp;
        
        size++;
    }
    
  
    /**
     * Removes element from back of queue.
     * [1][2][3]
     * => [1][2] --> removed: [3]
     */
    @Override
    public void dequeue(){
    // If list is empty 
        if (isEmpty()) {
            System.out.println("\nList is empty");
            return;
        }
          
         //find last node
        Node node = head;
         
        
        while(node.next != head && node.next != null)
            node = node.next;
        Node last = node;
        
        //find last prev, making lasts prev => last
        node = head;
        while(node.next != last)
            node = node.next;
        
        //remove from back
        node.next = head;
       
        size--;
    }
    
    /**
     * Checks if queue is empty. 
     * If empty set head = null to avoid loitering.
     * (Annars pekar head på sista elementet som vart dequeued)
     * @return true if empty
     */
    @Override
    public boolean isEmpty(){
        if(size==0){
            head = null;
            return true;
        }
        return false;
    }
    
    /**
     * Just calls the queueToString() method.
     */
    @Override
    public void print() {
        System.out.println(queueToString());
    }

    /**
     * Iterates through queue, dereference pointer and adds each value to stringbuilder.
     * @return Stringbuilder with each element from queue in brackets, [data]
     */
    private String queueToString(){
        Node node = head;
        int i = 0;
        
        //if queue is empty
        if(head==null)
            return "[]";
        
        StringBuilder sb = new StringBuilder();
        
        while(i < size){
            sb.append("[");
            sb.append(node.data);
            sb.append("],");
            node = node.next; 
            
            i++;
        }
        if(sb.length()>0)
            sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }
    
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order 
     */
    @Override
    public Iterator<Datatype> iterator() {
        return new LinkedIterator(head);
    }
    
    /**
     * An iterator taken from princeton: https://algs4.cs.princeton.edu/13stacks/Queue.java.html.
     */
    private class LinkedIterator implements Iterator<Datatype> {
        private Node current;
        
        /**
         * Constructor for iterator.
         * @param head First item in queue.
         */
        public LinkedIterator(Node head) {
            current = head;
        }
        
        /**
         * Checks if next element exists.
         * @return True if next element exists.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        /**
         * Advances the pointer to next element.
         * @return the current element.
         */
        @Override
        public Datatype next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Datatype item = current.data;
            current = current.next;
            return item;
        }
    }
    
    /**
     * En test klass.
     */
    private static class Test {
        public static void main(String[] args){
            Queue<Character> queue = new GeneralizedQueue<>();
            Character testIntList[] = {'a','b','c','d','e'};
            
            System.out.println("Enqueue elements to the front of queue");
            for (char element : testIntList) {
                queue.enqueue(element);
            }
            
            System.out.print("List: ");
            queue.print();
            
            //Remove element that doesnt exist, list-size: 5
            System.out.print("Remove elements: ");
            
            for(char element : testIntList){
                System.out.println("Remove element " + element);
                queue.dequeue();
                System.out.println("\nList: ");
                queue.print();
            }
             
            if(queue.isEmpty())
                System.out.println("The queue is now empty!\n");
            else
                System.out.println("Something went wrong, the queue is not empty");
        }
    }
}
