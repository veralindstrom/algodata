/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Double linked list.
 * @author Vera
 * @param <Datatype>
 */
public class OrderedQueueA1E6<Datatype extends Comparable<Datatype>> implements QueueA1E6<Datatype> {
    Node head;
    int size;
    
    // Structure of the node 
    private class Node{   
        Datatype data;
        Node next; // Pointer to next node
        Node prev; //Pointer to prev node
    }
    
    /**
     * Decices what operation to execute.
     * @param item Item to be added.
     */
    @Override
    public void operation(Datatype item){
        
        if(isEmpty()) 
            enqueue(item);
       
        else if(size == 1){
            Node temp = enqueue(item);
            if(lessOrEquals(temp)){
                switchOnlyTwoElements(temp);
            }
        }
        
        else if(size > 1){
            Node temp = enqueue(item);
            while(lessOrEquals(temp)){
                    if (temp == head)
                        switchWithFirstElementIfMoreThanTwoElements(temp);
                    else if(temp != head && temp.next.next != null)
                            switchMiddleElements(temp);
                    else
                        switchLastElementsIfMoreThanTwoElements(temp);
            }
        }
    }
  
   
    /**
     * Adds element [item] to front of queue.
     * QueueA1E6: head=>[1]<=>[2]
     * => QueueA1E6 after enqueue(): head=>[item]<=>[1]<=>[2]
     * @param item Item to be added to queue.
     * @return Node to added item.
     */
     private Node enqueue(Datatype item){
        //if list is empty
        if (head == null) { 
            Node temp = new Node(); 
            temp.data = item; 
            temp.next = temp.prev = temp; 
            head = temp; 
            size++;
            
            return temp; 
        } 
        
        //if list is not empty
        //insert to front
        Node temp = new Node();
        temp.data = item;
        
        // setting up previous and next of new node 
        temp.next = head;
        temp.next.prev = temp;
        head = temp;
        
        size++;
        return temp;
    }
    
  
    /**
     * Removes k:th element in list.
     * @param k Index of element to be deleted
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
          
        //If list not empty and if element exist, find element of k
        Node element = head;
        int i = 1;
        while(i!=k){
            element = element.next;
            i++;
        }
        
        //If element k is the only element in list
        if(size==1){
            head = null;
            size--;
            
            return;
        }
        
        //If element k is the first element in list
        else if(k==1){
            head = element.next;
            size--;
            
            return;
        }
        
        //find element before k:th element
        Node elementPrev = head;
        int j = 1;
        while(j != k-1){
            elementPrev = elementPrev.next;
            j++;
        } 
        
        //Check if element k is last element in list
        if(element.next == null){
            //temp = null;
            elementPrev.next = null;
            size--;
        }
        else{
            elementPrev.next = element.next;
            size--;
        }
        
    }
    
    /*----SWITCHING ELEMENTS----*/
    
    /**
     * Scenario #1 step 1
     * Switching first element with second element if list inlcudes more than two elements.
     * head=> [2]<=>[1]<=>[3]...
     * head=> [1]<=>[2]<=>[3]...
     */
    private void switchWithFirstElementIfMoreThanTwoElements(Node temp){
        head = temp.next;
        temp.next = temp.next.next;
        head.next = temp;
        temp.prev = head;
        temp.next.prev = temp;
        
        //To avoid loitering
        head.prev = null;
    }
    
    /**
     * Scenario #1 step 2
     * Switching two elements that is in the middle of list.
     * head =>[1]<=>[3]<=>[2]<=>[4]
     * head =>[1]<=>[2]<=>[3]<=>[4]
     */
    private void switchMiddleElements(Node temp){
        Node tempPrev = temp.prev;
        Node tempNext = temp.next;
        
        temp.next = tempNext.next;
        tempNext.prev = tempPrev;
        tempNext.next = temp;
        tempPrev.next = tempNext;
        temp.prev = tempNext;
        temp.next.prev = temp;
       
    }
    
    
    /**
     * Scenario #1 step 3
     * Switching second last element with last element if list includes more than two elements.
     * ...<=>[2]<=>[4]<=>[3]
     * ...<=>[2]<=>[3]<=>[4]
     */
    private void switchLastElementsIfMoreThanTwoElements(Node temp){
        temp.next.next = temp;
        temp.prev.next = temp.next;
        temp.prev = temp.next;
        temp.next.prev = temp.prev;
        
        //to avoid loitering
        temp.next = null;
    }
    
    /**
     * Scenari #2 step 1
     * Switching first element with second element if only two elements in list.
     * head=>[2]<=>[1]
     * head=>[1]<=>[2]
     */
    private void switchOnlyTwoElements(Node temp){
        head = temp.next;
        head.next = temp;
        temp.prev = head;
        
        //To avoid loitering
        head.prev = null;
        temp.next = null;
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
     * Checks if next item it less than current item.
     * In that case, items should switch places.
     * @param temp Node to be compared
     * @return true if next item was smaller
     */
    private boolean lessOrEquals(Node temp) {
         Datatype item = temp.data;
         if(temp.next!=null){
            Datatype nextItem = temp.next.data;
            return nextItem.compareTo(item) < 0;
         }
         else 
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
    private String queueToString(){
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
            QueueA1E6<Integer> queue = new OrderedQueueA1E6<>();
            Integer testIntList[] = {3,5,7,8,10,2,15};
            
            System.out.println("Enqueue elements to the front of queue");
            for (Integer element : testIntList) {
                System.out.print("Add element: " + element + "\n");
                queue.operation(element);
                queue.print();
            }
            
            System.out.print("Add element: 7 \n");
            queue.operation(7);
            queue.print();
            
            
            //Remove element that doesnt exist, list-size: 5
            System.out.print("\nRemove element that doesnt exist: ");
                System.out.println(8);
                queue.dequeueKthElement(8);
                System.out.println("\nList: ");
                queue.print();
             
            System.out.print("Remove last element: ");
                System.out.println(testIntList.length);
                queue.dequeueKthElement(testIntList.length);
                System.out.println("\nList: ");
                queue.print();
            
            System.out.print("Remove first element: ");
                System.out.println(1);
                queue.dequeueKthElement(1);
                System.out.println("\nList: ");
                queue.print();
                
            System.out.print("Remove some element: ");
                System.out.println(4);
                queue.dequeueKthElement(4);
                System.out.println("\nList: ");
                queue.print();
        }
    }
}
