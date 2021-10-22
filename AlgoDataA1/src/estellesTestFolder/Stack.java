/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vera
 */
public class Stack<Item> {
            int n; //size of the stack
            private Node<Item> first; //top of the stack
            char[] input;

            //hjälper linked  list class
            private static class Node<Item> {
                private Item item;
                private Node<Item> next;
            }

            //initierar en tom stack
            public Stack() {
                first = null;
                n = 0;
            }

            // return true om stacken är tom; annars false
            public boolean isEmpty() {
                return first == null;
            }

            // returnerar antalet element i stacken
            public int size() {
                return n;
            }


            //Funktion för att pusha element i stacken
            //item the item to add

            public void push(Item item) {
                Node<Item> oldFirst = first;
                first = new Node<Item>();
                first.item = item;
                first.next = oldFirst;
                n++;
            }


            //flyttar och returnerar senast tilllagda elementet till stacken
            //returnerar item som var senast tillagd
            // undantag om stacken är tom
            public Item pop()
            {
                if (isEmpty()) throw new NoSuchElementException("Stack overflow");
                Item item = first.item; //spara item till return
                first = first.next; // radera first node
                n--;
                return item; //returnera sparade item

            }
            
        public String stackToString(){
        Node temp = first;
        int i = 0;
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        
        while(i < n){
            sb.append("[");
            sb.append(temp.item);
            sb.append("],");
            temp = temp.next;
            i++;
        }
        if(sb.length()>0)
            sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }
    /*
         //Returns a string representation of this stack.
        //return the sequence of items in this stack in LIFO order, separated by spaces
         public String toString(){
            StringBuilder w =new StringBuilder();
            for (Char item : this){
                w.append(item);
                w.append(' ');
            }
            return w.toString();
         }
    */

            /**
             * Returns an iterator to this stack that iterates through the items in LIFO order.
             *
             * @return an iterator to this stack that iterates through the items in LIFO order
             */
            public Iterator<Item> iterator() {
                return new LinkedIterator(first);
            }

            // an iterator, doesn't implement remove() since it's optional
            private class LinkedIterator implements Iterator<Item> {
                private Node<Item> current;

                public LinkedIterator(Node<Item> first) {
                    current = first;
                }

                public boolean hasNext() {
                    return current != null;
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }

                public Item next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Item item = current.item;
                    current = current.next;
                    return item;
                }
            }

    /*

            public static String reverseItr (String word)
            {
                //base case: om string är null eller tom
                if (word == null || word.equals(""))
                    return word;

                Stack<String> stack = new Stack<String>();

                //pusha varje char i stringen i stacken
                for(int i =0; i < word.length(); i++)
                    stack.push(word.charAt(i));

                //pop char från stacken och append them to string builder
                StringBuilder w = new StringBuilder();
                while (!stack.isEmpty())
                    w.append(stack.pop());

                //convert stringBuilder to string and return
                return w.toString();

            }
        }*/

    /*
            public static String reverseItr(String word) {

                //base case: om string är null eller tom
                if (word == null || word == "")
                    return word;

                //Skapa en tom Stack
                Stack stack = new Stack();

                //pusha varje char i stringen in i stacken
                char[] input = word.toCharArray();
                //int n = input.length;


                //string.toCharArray converts given string to char array
                //char[] input = word.toCharArray();
                for (int i = 0; i < word.length(); i++)
                    stack.push(input[i]);
                //stack.push(input[i]);
                int k = 0;


                //convert stringBuilder to string and return
                //return w.toString();

                return String.copyValueOf(input);
            }

            public static String reverseItr(String word)
            {
                // base case: if string is null or empty
                if (word == null || word.equals(""))
                    return word;

                // create an empty stack of characters
                Stack stack = new Stack();

                // push every character of the given string into the stack
                char[] w = word.toCharArray();
                for (int i = 0; i < word.length(); i++)
                    stack.push(w[i]);

                // start from index 0
                int k = 0;

                // pop characters from the stack until it is empty
                while (!stack.isEmpty())
                {
                    // assign each popped character back to the character array
                    w[k++] = stack.pop();
                }

                // convert the character array into string and return it
                return String.copyValueOf(w);
            }

        }
    */
  }
