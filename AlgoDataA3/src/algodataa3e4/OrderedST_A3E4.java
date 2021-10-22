/**
 * README.
 * 
 * Symbol table consists of two synced ordered arrays, one containing keys and one values.
 *     0       1      2
 *  [key1]  [key2]  [key3]
 * [value1][value2][value3]
 * 
 * The symbol table is ordered by keys.
 */
package algodataa3e4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @param <Key>
 * @param <Value>
 */
public class OrderedST_A3E4<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int size = 0;

    /**
     * Initializes an empty symbol table.
     */
    public OrderedST_A3E4() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     * @param capacity the maximum capacity
     */
    public OrderedST_A3E4(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
    }   

        /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    
    
    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    
    // resize the underlying arrays
    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

     /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        if (isEmpty()) return null;
        int i = rank(key); 
        if (i < size && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    } 

    /**
     * Returns the number of keys in this symbol table strictly less than {@code key}.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 

        int lo = 0, hi = size-1; 
        while (lo <= hi) { 
            int mid = lo + (hi - lo) / 2; 
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1; 
            else if (cmp > 0) lo = mid + 1; 
            else return mid; 
        } 
        return lo;
    } 



    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val)  {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 

        if (val == null) {
            delete(key);
            return;
        }
        
        int i = rank(key);

        // key is already in table
        if (i < size && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        if (size == keys.length) resize(2*keys.length);
        
        //move all elements after where to insert new element
        //to make room for new element
        for (int j = size; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        
        keys[i] = key;
        vals[i] = val;
        size++;

    } 
    
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == size || keys[i].compareTo(key) != 0) {
            return;
        }
        
        //if key in table, replace its place with next element until all is moved
        for (int j = i; j < size-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        size--;
        keys[size] = null;  // to avoid loitering
        vals[size] = null;

        // resize if 1/4 full
        if (size > 0 && size == keys.length/4) resize(keys.length/2);
    }

    public Key[] getKeys() {
        return keys;
    }
    
    private static class Test {
        static int position;
        public static void main(String[] args) { 
       
        Scanner scan = new Scanner(System.in);
        //787852
        //804330
        
        OrderedST_A3E4<String, List> st = new OrderedST_A3E4<>();
        StringBuilder theWholeText = new StringBuilder();
        String[] input = {" the project  is your name ", " My name is Vera ", " Vera okay ", "bye"};
        System.out.println("What word would you like to find? ");
        String wordToFind = scan.nextLine();
        
        
        for(String word : input){
            theWholeText.append(word.toLowerCase());
        }

        System.out.println("Length of text: " + theWholeText.length());
        //read file word by word and store in array
        //int spaces = 0;
        int charCounter = 0;
        int i = 0;
        while(i < theWholeText.length()) {
            int spaces = 0;
            System.out.println("\nIteration #" + i);
            StringBuilder word = new StringBuilder();
            StringBuilder klump = theWholeText;
            
            while( position < klump.length()){
                System.out.println("\nPosition: " + position);
                
                System.out.println(klump.charAt(position));
                if((int)klump.charAt(position) == 32){
                    while((int)klump.charAt(position) == 32){
                        spaces++;
                        System.out.println("char was _space_");
                        System.out.println("Posititon: " + position);
                        position++;
                        System.out.println("next char: " + klump.charAt(position));
                    }
                    //System.out.println("Spaces: " + spaces);
                   break;
                }
                else{
                word.append(klump.charAt(position));
                position++;
                }
                
            }
            System.out.println("Spaces: " + spaces);
            System.out.println("Position: " + position);
            charCounter = position;// + 1 - spaces;
            String key = word.toString();
            System.out.println("Position: " + position);
            System.out.println("key: " + key);
            System.out.println("key-length: " + key.length());
            System.out.println("charCounter: " + charCounter);
            
            if (st.contains(key)) {  //if word has appeared before
                List temp = st.get(key);
                temp.add(charCounter - key.length());
                st.put(key, temp);  //add 1 to value
            }
            else {
                List positions = new ArrayList();
                positions.add(charCounter - key.length());
                st.put(key, positions);  //if word has not appeared before, put value to 1
            }
            i++;
        }
        
        List positionsOfWordToFind = new ArrayList();
        
        if(st.contains(wordToFind)){
        positionsOfWordToFind.addAll(st.get(wordToFind));
        
        System.out.println("Word was found " + positionsOfWordToFind.size() + 
                " times at positions: " + positionsOfWordToFind);
        }
        else
            System.out.println("Word was not found");
    }
    }
}
