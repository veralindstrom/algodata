/**
 * README.
 * 
 * Linear Probing Hash table is an open addressing type of 
 * collision resolver. It handles collisions by calculating a new hash 
 * for the colliding key:
 * -> keys-hash-value = (keys-hash-value + 1) % size of table
 * As a result all keys have their unique hash value, making the time 
 * to search for a given key constant.
 */
package algodataa3hg;

import java.util.Scanner;

/**
 *
 * @author Vera
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST <Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values
    

    /**
     * Initializes an empty symbol table.
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     *
     * @param capacity the initial capacity
     */
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
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
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }
   
    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     * 
     * If table is 50% full, resize:
     * Because if there are [100 element..., only free spot, first element it checks, 100element...] 
     * then it would have to loop through 200 elements. But if the size is always
     * double the number of key-valur pairs then there will be much easier to find a free spot
     * (a non occupied hash).
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Returns the value associated with the specified key.
     * If the keys is not found at the calculated hash, check another hash
     * i = (i+1) % m
     * @param key the key
     * @return the value associated with {@code key};
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    /*
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) resize(m/2);

    }*/
    
    // delete key (and associated value) from the symbol table
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete not currently supported");
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new GeneralizedQueue<>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
    
    /**
     * Returns all values in this symbol table as an {@code Iterable}.
     * To iterate over all of the values in the symbol table named {@code st},
     * use the foreach notation: {@code for (Value value : st.values())}.
     *
     * @return all values in this symbol table
     */
    public Iterable<Value> values() {
        Queue<Value> queue = new GeneralizedQueue<>();
        for (int i = 0; i < m; i++)
            if (vals[i] != null) queue.enqueue(vals[i]);
        return queue;
    }

    /**
     * Unit tests the {@code LinearProbingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        QuickSort qs = new QuickSort();
        
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        
        String input[] = {"hej", "hej", "hej", "då", "då", "då", "vad", "vad", "vill", "du"};
        int distinct = 0;
        for (String key : input) {
            if (key == null || key.length() < 1) continue; //if word is too small, skip
           
            if (st.contains(key)) {  //if word has appeared before
                st.put(key, st.get(key) + 1);  //add 1 to value
            }
            else {
                st.put(key, 1);  //if word has not appeared before, put value to 1
                distinct++;
            }
        }
        
        String keys[] = new String[distinct];
        Integer values[] = new Integer[distinct];
        
        int i = 0;
        for (String key : st.keys()){
            keys[i] = key;
            System.out.println("Keys[]: " + keys[i]);
            i++;
        } 
        
        int j = 0;
        for (Integer value : st.values()) {
            values[j] = (value * -1);
            System.out.println("Values[]: " + values[j]);
            j++;
        }
        
        qs.sort(values, keys);
        
        System.out.println("Distinct: " + distinct);
        while(true){
            System.out.println("What do you want? ");
            int k = sc.nextInt();
            int n = sc.nextInt();
            
            for(int w = k; w <= n; w++)
                System.out.println("#" + w + " most common word: " + keys[w-1] + " with " + (values[w-1] * -1) + " occurrences");    
        }
    }
}
