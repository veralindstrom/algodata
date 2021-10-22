/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 * @param <Datatype> Datatype for input
 */
public class CircularLinkedListA1E4<Datatype> implements ListA1E4<Datatype>{
    Node head;
    int size;
    
    // Structure of the node 
    private class Node
    {   
        Datatype data;
        Node next; // Pointer to next node
        
    }
    
    /**
     * Adds element to front of queue.
     * [1][2]
     * => [item][1][2]
     * @param item Item to be added to queue.
     */
    @Override
    public void enqueueToFront(Datatype item){
        //if list is empty
         if (head == null) { 
            Node temp = new Node(); 
            temp.data = item; 
            head= temp.next = temp; 
            size++;
            
            System.out.println("\nElement: [" + item.toString() + "] has been enqueued to front");
            print();
            return; 
        } 
        
        //if list is not empty
        
        //find last node
        Node last = head;
         
        while(last.next != head)
            last = last.next;
        
        //insert to front
        Node temp = new Node();
        temp.data = item;
        
        // setting up previous and next of new node 
        temp.next = head;
        head = last.next = temp;
        
        size++;
        
        System.out.println("\nElement: [" + item.toString()+ "] has been enqueued to front");
        //System.out.println(queueToString());
        print();
    }
    
    /**
     * Adds element to back of queue.
     * [1][2]
     * => [1][2][item]
     * @param item Item to be added to queue.
     */
    @Override
    public void enqueueToBack(Datatype item){
        //if list is empty
         if (head == null) { 
            Node temp = new Node(); 
            temp.data = item; 
            head = temp.next = temp;
            size++;
            
            System.out.println("\nElement: [" + item + "] has been enqueued to back");
            print();
            return; 
        } 
        
        //if list is not empty
        //find last node
        Node node = head;
        
        //if queue has more element than 1 (meaning head node != last node) 
        if(size > 1){
            while(node.next != head)
                node = node.next;
        }
        
        Node last = node;
        
        //insert to back
        Node temp = new Node();
        temp.data = item;
        
        // setting up previous and next of new node 
        last.next = temp;
        temp.next = head;
        //last = temp;
        
        size++;
        
        System.out.println("\nElement: [" + item.toString()+ "] has been enqueued to back");
        //System.out.println(queueToString());
        print();
        
    }
    
    /**
     * Removes element from front of queue.
     * [1][2][3]
     * => [2][3] --> removed: [1]
     */
    @Override
    public void dequeueFromFront(){
        
        // If list is empty 
        if (isEmpty()) {
            System.out.println("\nList is empty");
            return;
        }
          
         //find last node
        Node last = head;
         
        while(last.next != head)
            last = last.next;
        
        //insert to front
        Node temp = head;
        
        temp.data = head.data; //if item is needed
        
        head = head.next; //temp.next
        last.next = head; //temp.next
       
        size--;
        
        System.out.println("\nElement: [" + temp.data.toString()+ "] has been dequeued from front");
        //System.out.println(queueToString());
        print();
    }
    
    /**
     * Removes element from back of queue.
     * [1][2][3]
     * => [1][2] --> removed: [3]
     */
    @Override
    public void dequeueFromBack(){
    // If list is empty 
        if (isEmpty()) {
            System.out.println("\nList is empty");
            return;
        }
          
         //find last node
        Node node = head;
         
        while(node.next != head)
            node = node.next;
        Node last = node;
        
        //find last prev, making lasts prev => last
        node = head;
        while(node.next != last)
            node = node.next;
        
        //remove from back
        node.next = head;
       
        size--;
        
        System.out.println("\nElement: [" + last.data.toString()+ "] has been dequeued from back");
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
     * Iterates through queue and adds each element to stringbuilder.
     * @return Stringbuilder with each element from queue in brackets, [data]
     */
    public String queueToString(){
        Node node = head;
        int i = 0;
        
        //if queue is empty
        if(isEmpty())
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
            ListA1E4<Integer> queue = new CircularLinkedListA1E4<>();
            Integer testIntList[] = {1,2,3,4,5};
            
            System.out.println("Enqueue elements to the front of queue");
            for (Integer element : testIntList) {
                queue.enqueueToFront(element);
                //System.out.println("ListA1E4: " + queue.queueToString());
            }
            
            
            System.out.println("Dequeue elements from the back of queue");
            while (!queue.isEmpty()) {
                queue.dequeueFromBack();
                //System.out.println("ListA1E4: " + queue.queueToString());
            }
            
            
            if(queue.isEmpty())
                System.out.println("The queue is now empty!\n");
            else
                System.out.println("Something went wrong, the queue is not empty");
            
            
            System.out.println("Enqueue elements to the back of queue");
            for (Integer element : testIntList) {
                queue.enqueueToBack(element);
                //System.out.println("ListA1E4: " + queue.queueToString());
            }
            
            
            System.out.println("Dequeue elements from the front of queue");
            while (!queue.isEmpty()) {
                //stack.pop();
                queue.dequeueFromFront();
                //System.out.println("ListA1E4: " + queue.queueToString());
            }
            
            
            if(queue.isEmpty())
                System.out.println("The queue is now empty !");
            else
                System.out.println("Something went wrong, the queue is not empty");
        
        }
    }
}
