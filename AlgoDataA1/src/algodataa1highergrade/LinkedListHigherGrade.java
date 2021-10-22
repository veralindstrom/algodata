/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1highergrade;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public class LinkedListHigherGrade<Datatype extends Comparable<Datatype>> implements ListHigherGrade<Datatype> {
    Node head;
    int size;
    
    // Structure of the node 
    private class Node{   
        Datatype data;
        Node next; // Pointer to next node
    
    }
    
    /**
     * Checks if there is as many item a's as item b's in list.
     * 
     * Declaring variables because we can only call countParentheses() once for each item.
     * Otherwise the dequeue()-method in countParentheses() will make result inaccurate.
     * 
     * @param a To be compared to b
     * @param b To be compared to a
     */
    @Override
    public void checkForMatches(Datatype a, Datatype b){
        int countA = countParentheses(a);
        int countB = countParentheses(b);
        
        if(countA == 0 && countB == 0)
                System.out.println(a + " " + b + " is not in list");
        
        else if(countA == countB)
                System.out.println(a + " " + b + " is balanced");
        
        else 
            System.out.println(a + " " + b + " is not balanced");
    }
    
    /**
     * Counts number of items in list and then dequeues the item.
     * @param item To be counted
     * @return Number of items
     */
    private int countParentheses(Datatype item){
        //print();
        if(isEmpty())
            return 0;
        
        Node temp = head;
        Node comparable = new Node();
        comparable.data = item; 
        //System.out.println("Temp: " + temp.data);
        //System.out.println("Comparable: " + comparable.data);
        int i = 0;
        int count = 0;
        while(i<=size){
            //print();
            if(isEqualTo(comparable.data, temp.data)){
                count++;
                dequeue(temp);
                //System.out.println("Queue efter dequeue: ");
                //print();
            }
            if(temp.next != null){
                temp = temp.next;
                //System.out.println("Temp efter temp.next: " + temp.data);
            }
            i++;
        }
      
        //System.out.println("Count: " + count);
        
        
        return count;
    }
    
    /**
     * Adds element [item] to front of queue.
     * Queue: [1][2]
     * => Queue after enqueue(): [item][1][2]
     * @param item
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
        
        // setting up previous and next of new node 
        temp.next = head;
        head = temp;
        
        size++;
        
    }
    
  
    /**
     * Removes k:th element. 
     * @param temp Element to be deleted
     */
    private void dequeue(Node temp){
        // If list is empty 
        if (isEmpty()) {
            System.out.println("\nList is empty");
            return;
        }
        
        //If element is the only element in list
        if(size==1){
            head = null;
            size--;
            
            return;
        }
        
        //If element is the first element in list
        else if(head==temp){
            head = temp.next;
            size--;
            return;
        }
        
        //find element before temp element
        Node tempPrev = head;
        while(tempPrev.next != temp)
            tempPrev = tempPrev.next;
        
        //Check if element is last element in list
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
     * Checks if next item it less than current item.
     * In that case, items should switch places.
     * @param temp Node to be compared
     * @return true if next item was smaller
     */
    private boolean isEqualTo(Datatype comparable, Datatype temp) {
            return comparable.compareTo(temp) == 0;
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
            return " ";
        
        StringBuilder sb = new StringBuilder();
        
        while(i < size){
            sb.append("'");
            sb.append(node.data);
            sb.append("',");
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
            ListHigherGrade<Character> queue = new LinkedListHigherGrade<>();
            Character testIntList[] = {'(','(','[',')', ']'};
            
            System.out.println("Enqueue elements to the front of queue");
            for (char element : testIntList) {
                queue.enqueue(element);
            }
            
            queue.print();
            
            while(!queue.isEmpty()){
                System.out.println("Check for matches for ()");
                queue.checkForMatches('(', ')');
                System.out.print("Queue: ");
                queue.print();
                System.out.println("Check for matches for []");
                queue.checkForMatches('[', ']');
                System.out.print("Queue: ");
                queue.print();
                System.out.println("Check for matches for {}");
                queue.checkForMatches('{', '}');
                System.out.print("Queue: ");
                queue.print();
            }
            
            if(queue.isEmpty())
                System.out.print("Queue is empty");
            else
                System.out.println("Queue is not empty, something went wrong");
            
        }
    }
}
