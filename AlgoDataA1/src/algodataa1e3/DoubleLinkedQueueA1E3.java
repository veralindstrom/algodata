/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e3;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author Vera
 * @param <Datatype>
 */
public class DoubleLinkedQueueA1E3<Datatype> implements QueueA1E3<Datatype>{
    Node head;
    int size;
    
    // Structure of the node 
    private class Node
    {   
        Datatype data;
        Node next; // Pointer to next node
        Node prev; // Pointer to previous node
    }
    
    /**
     * Adds item to front of queue.
     * [1][2] ← [3]
     * => queue:[1][2][3]
     * @param item Item to be added.
     */
    @Override
    public void enqueue(Datatype item){
        //if list is empty
         if (head == null) { 
            Node temp = new Node(); 
            temp.data = item; 
            temp.next = temp.prev = temp; 
            head = temp; 
            size++;
            
            System.out.println("\nElement: [" + item  + "] has been enqueued");
            return; 
        } 
         
        //if list is not empty
        Node last = head.prev;
        
        Node temp = new Node();
        temp.data = item;
        
        // setting up previous and next of new node 
        temp.next = head;
        temp.prev = last;
        
        // Update next and previous pointers of start and last
        head.prev = last.next = temp;
        //last.next = head.prev;
        
        head = temp;
        size++;
        
        System.out.println("\nElement: [" + item + "] has been enqueued");
        //System.out.println(queueToString());
        print();
        
    }
    
    /**
     * Removes last item in queue.
     * queue:[1][2][3]
     * =>remove: [1]← queue:[2][3]
     */
    @Override
    public void dequeue(){
        Node last = head.prev;
        Datatype item = last.data;
        
        // If list is empty 
        if (isEmpty()) {
            System.out.println("\nList is empty");
            return;
        }
          
        
        last.prev.next = head;
        head.prev = last.prev;
        
        size--;
        
        System.out.println("\nElement: [" + item + "] has been dequeued");
        //System.out.println(queueToString());
        print();
        
        
    }
    
    /**
     * Checks if queue is empty. 
     * If empty set head = null to avoid loitering.
     * (Annars pekar head på sista elementet som vart dequeued)
     * @return true if empty
     */
    @Override
    public boolean isEmpty(){
        return size==0;
    }
    
    /**
     * Just calls the queueToString() method.
     */
    @Override
    public void print() {
        System.out.println(queueToString());
    }

    /**
     * Iterates through queue and adds each element to stringbuilder.
     * @return Stringbuilder with each element from queue in brackets, [data]
     */
    public String queueToString(){
        Node last = head.prev;
        int i = 0;
        if(isEmpty())
            return "[]";
        
        StringBuilder sb = new StringBuilder();
        
        while(i < size){
            sb.append("[");
            sb.append(last.data);
            sb.append("],");
            last = last.prev;
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
     * An iterator.
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
            current = current.prev;
            return item;
        }
    }
    
    /**
     * En test klass.
     */
    private static class Test {
        public static void main(String[] args){
            QueueA1E3<Integer> queue = new DoubleLinkedQueueA1E3<>();
            Integer testIntList[] = {1,2,3,4,5};
        
            System.out.println("Enqueue elements to the queue");
            for (Integer testIntList1 : testIntList) {
                queue.enqueue(testIntList1);
                //System.out.println("QueueA1E3: " + queue.toString());
            }
            
            System.out.println("Dequeue elements from the queue");
            while (!queue.isEmpty()) {
                queue.dequeue();
                //System.out.println("QueueA1E3: " + queue.toString());
            }
            
            if(queue.isEmpty())
                System.out.println("The queue is now empty !");
            else
                System.out.println("Something went wrong, the queue is not empty");
        
        }
    }
    
}
