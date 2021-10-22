/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public class GeneralizedQueueA1E5<Datatype> implements QueueA1E5<Datatype> {
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
 => QueueA1E5 after enqueue(): [item][1][2]
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
     * Removes k:th element. 
     * @param k Element to be deleted
     */
    @Override
    public void dequeueKthElement(int k){
        // If list is empty 
        if (isEmpty()) {
            System.out.println("\nList is empty");
            return;
        }
        //Check if element exists
        else if(k > size){
            System.out.println("\nElement does not exist");
            return;
        }
        
        //If element k is the only element in list
        if(size==1){
            head = null;
            size--;
            
            return;
        }
          
        //If list not empty and if element exist, find element of k
        Node temp = head;
        int i = 1;
        while(i!=k){
            temp = temp.next;
            i++;
        }
        //System.out.println("\nElement k is: " + temp.data);
        
        //If element k is the only element in list
        if(size==1){
            head = null;
            size--;
            
            return;
        }
        
        //If element k is the first element in list
        else if(k==1){
            head = temp.next;
            size--;
            return;
        }
        
        //find element before k:th element
        Node tempPrev = head;
        int j = 1;
        while(j != k-1){
            tempPrev = tempPrev.next;
            j++;
        } 
        
        //Check if element k is last element in list
        if(temp.next == null){
            //temp = null;
            tempPrev.next = null;
            size--;
        }
        else{
            tempPrev.next = temp.next;
            size--;
        }
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
            QueueA1E5<Character> queue = new GeneralizedQueueA1E5<>();
            Character testIntList[] = {'a','b','c','d','e'};
            
            System.out.println("Enqueue elements to the front of queue");
            for (char element : testIntList) {
                queue.enqueue(element);
            }
            
            System.out.print("List: ");
            queue.print();
            
            //Remove element that doesnt exist, list-size: 5
            System.out.print("Remove element that doesnt existe: ");
                System.out.println(6);
                queue.dequeueKthElement(6);
                System.out.println("\nList: ");
                queue.print();
             
                
            //Remove last element, list-size: 5
            System.out.print("Remove last element: ");
                System.out.println(testIntList.length);
                queue.dequeueKthElement(testIntList.length);
                System.out.println("\nList: ");
                queue.print();
            
            //Remove first element,list-size: 4
            System.out.print("Remove first element: ");
                System.out.println(1);
                queue.dequeueKthElement(1);
                System.out.println("\nList: ");
                queue.print();
                
            //Remove middle element, list-size: 3
            System.out.print("Remove middle element: ");
                System.out.println(2);
                queue.dequeueKthElement(2);
                System.out.println("\nList: ");
                queue.print();
              
            //Remove some element (this case: last), list size: 2
            System.out.print("Remove some element (this case: last): ");
                System.out.println(2);
                queue.dequeueKthElement(2);
                System.out.println("\nList: ");
                queue.print();
             
            //Remove element when only element in list, list-size: 1
            System.out.print("Remove element when only element in list: ");
                System.out.println(1);
                queue.dequeueKthElement(1);
                System.out.println("\nList: ");
                queue.print();
            
            //Remove some element when list empty, list-size: 1
            System.out.print("Remove some element when list empty: ");
                System.out.println(1);
                queue.dequeueKthElement(1);
                System.out.println("\nList: ");
                queue.print();
             
            
            if(queue.isEmpty())
                System.out.println("The queue is now empty!\n");
            else
                System.out.println("Something went wrong, the queue is not empty");
        }
    }
}
