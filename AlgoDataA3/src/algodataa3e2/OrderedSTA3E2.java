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
package algodataa3e2;

/**
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @param <Key> 
 * @param <Value>
 */
public class OrderedSTA3E2<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    /**
     * Initializes an empty symbol table.
     */
    public OrderedSTA3E2() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with a specified initial capacity.
     * @param capacity the maximum capacity
     */
    public OrderedSTA3E2(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
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
    /**
     * Copies arrays to arrays of size {@code capacity}.
     * @param capacity New size
     */
    private void resize(int capacity) {
        assert capacity >= n; //assures that if the requested size is larger than number of elements.
        
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
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
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
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

        int lo = 0, hi = n-1; 
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
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        
        //Checks if array is full
        if (n == keys.length) resize(2*keys.length);
        
        //move all elements after where to insert new element
        //to make room for new element
        for (int j = n; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        
        keys[i] = key;
        vals[i] = val;
        n++;

    } 
    
    /**
     * Deletes requested key-value pair.
     * Finds rank of key and moves every next element one step to the left,
     * replacing the previous key-value pair.
     * 
     * Opposite of put().
     * 
     * Resizes the array if neccessary.
     * @param key Key to be deleted.
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }
        
        //if key in table, replace its place with next element until all is moved
        for (int j = i; j < n-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length/4) resize(keys.length/2);
    }
}
