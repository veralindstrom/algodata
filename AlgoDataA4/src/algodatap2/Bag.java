/**
 * README.
 * A bag.
 * A series of linked nodes.
 * The nodes are added to front.
 * The order depends on input, there is no specific order.
 */
package algodatap2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Vera
 * @param <Datatype>
 */
public class Bag<Datatype> implements Iterable<Datatype> {
    private Node<Datatype> first;    // beginning of bag
    private int n;               // number of elements in bag

    // helper linked list class
    private static class Node<Datatype> {
        private Datatype item;
        private Node<Datatype> next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this bag is empty.
     *
     * @return {@code true} if this bag is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this bag.
     *
     * @param  item the item to add to this bag
     */
    public void add(Datatype item) {
        Node<Datatype> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    /**
     * Returns an iterator that iterates over the items in this bag in arbitrary order.
     *
     * @return an iterator that iterates over the items in this bag in arbitrary order
     */
    @Override
    public Iterator<Datatype> iterator()  {
        return new LinkedIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Datatype> {
        private Node<Datatype> current;

        public LinkedIterator(Node<Datatype> first) {
            current = first;
        }

        @Override
        public boolean hasNext()  { return current != null;                     }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Datatype next() {
            if (!hasNext()) throw new NoSuchElementException();
            Datatype item = current.item;
            current = current.next; 
            return item;
        }
    }

    /**
     * Unit tests the {@code Bag} data type.
     *
     * @param args the command-line arguments
     */
    private static class Test{
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            Bag<String> bag = new Bag<>();
            while (in.hasNext()) {
                String item = in.next();
                bag.add(item);
            }

            System.out.println("size of bag = " + bag.size());
            for (String s : bag) {
                System.out.println(s);
            }
        }
    }

}
