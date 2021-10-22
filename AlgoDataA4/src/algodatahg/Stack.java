/**
 * README.
 * A Stack.
 */
package algodatahg;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item>{
    
    /**
     * A linked list node.
     */
    private class Node {
        Item data;
        Node prev;
    }

    Node top;
    int size;
    
    /**
    * Constructor for the stack.
    */
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    /**
     * Adds item to top of stack:
     *       top →       temp
     *                     ↓ 
     * data→            [item]
     * head→(top→)    [somedata]
     *                [somedata]
     *                  
     * @param item Data to be added.
     */
    public void push(Item item){
        Node temp = new Node();
        
        //initilize data into temp datafield
        temp.data = item;
        
        //put top reference into temp head
        temp.prev = top;
        
        //update top reference
        top = temp;
        
        size++;
        
    }
   
    /**
     * Removes item from top of stack:
     *                   temp → →↓
     * (top→)           [item]   ↓
     *                           ↓
     *                    ↓← ← ← ↓	
     *                    ↓
     * top.prev→top→  [somedata]
     *                [somedata]
     *              
     * @return Returns the item that was removed from stack.
     */
    
    public Item pop(){
        // check for stack underflow 
        if (top == null) 
            throw new NoSuchElementException("The list is empty");
  
        // update the top pointer to point to the next node 
        Item item = top.data;
        top = top.prev;
        size --;
        return item;
        
    }
    
    
    /**
     * Checks if stack is empty. 
     * @return True if empty, false if not.
     */
    
    public boolean isEmpty() 
    { 
        return top == null; 
    }
    
    /**
     * Get size of stack
     * @return Size of stack
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Dereference pointer and adds its value to a stringbuilder.
     * @return Returns stringbuilder
     */
    public String stackToString(){
        Node temp = top;
        int i = 0;
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        
        while(i < size){
            sb.append("[");
            sb.append(temp.data);
            sb.append("],");
            temp = temp.prev;
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
    public Iterator<Item> iterator() {
        return new LinkedIterator(top);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.prev;
            return item;
        }
    }
    
    /**
     * En test klass.
     */
    private static class Test {
        public static void main(String[] args){
            Stack<Integer> stack = new Stack<>();
            Integer testIntList[] = {1,2,3,4,5};
        
            System.out.println("Pushing elements to the stack");
            for (Integer testIntList1 : testIntList) {
                stack.push(testIntList1);
                System.out.println("Pushed element: " + testIntList1);
                System.out.println("Stack: " + stack.stackToString());
            }
            
            System.out.println("Popping elements from the stack");
            while (!stack.isEmpty()) {
                //stack.pop();
                System.out.println("Popped element: " + stack.pop());
                System.out.println("Stack: " + stack.stackToString());
            }
            
            if(stack.isEmpty())
                System.out.println("The stack is now empty !");
            else
                System.out.println("Something went wrong, the stack is not empty");
        
        }
    }
   
}