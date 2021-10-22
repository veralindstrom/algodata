/**
 * README.
 * 
 * Stores hashvalues in an array where each element (hashvalue) points to a
 * linked list containing the key-value pairs of that hashvalue.
 * 
 * Hashvalue is calculated with javas hashCode()-function, then
 * mask off the sign bit and combine with modular hashing to get the hash
 * to represent an array index 0 to M-1:
 * {@code (key.hashCode() & 0x7fffffff) % M;}
 * 
 * hashvalues   linked lists 
 *    [1]   --> [key1#1][key2#1][key3#1]
 *    [2]   --> [key1#2]
 *    [3]   --> [key1#3][key2#3][key3#3][key4#3]
 *    [4]   --> [key1#4][key2#4]
 * 
 * 
 */
package algodataa3e3;

/**
 *
 * @author Vera
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHash_A3E3<Key, Value> {

    private int n;       // number of key-value pairs
    private int m;       // hash table size
    private Node[] st;   // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;
        
        /**
         * Creates node for each key-value pair.
         * @param key
         * @param val
         * @param next 
         */
        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Create separate chaining hash table.
     * Creates 997 linked lists, meaning having the interval for hashvalues to 0-996
     */
    public SeparateChainingHash_A3E3() {
        this(997);
    } 

    /**
     * Create separate chaining hash table.
     * Createds m linked lists, interval for hashvalues is set to 0 to m-1
     * @param m Interval for hashvalues
     */
    public SeparateChainingHash_A3E3(int m) {
        this.m = m;
        st = new Node[m];
    } 


    /**
     * Calculated hashvalue of key.
     * Hashvalue is calculated with javas hashCode()-function, then
     * mask off the sign bit and combine with modular hashing to get the hash
     * to represent an array index 0 to M-1:
     * {@code (key.hashCode() & 0x7fffffff) % M;}
     * @param key
     * @return 
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    } 

    // return number of key-value pairs in symbol table
    public int size() {
        return n;
    } 
    
    /**
     * Returns number of nodes in list st[i]
     * @param i The requested list.
     * @return Size of requested list.
     */
    public int sizeOfList(int i){
        int size = 0;
        for (Node x = st[i]; x != null; x = x.next) {
            size++;
        }
        return size;
        //st[i].size();
    }
    

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    } 

    /**
     * Get value associated with key, null if no such key.
     * Node points to element where the requested key is stored
     * by using the hashvalue which is calculated to be an array index.
     * 
     * Then iterates through the elements linked list until key-value is found.
     * Then returns key-value.
     * 
     * @param key Requested key
     * @return Value associated with key, null if no such key
     */
    public Value get(Key key) {
        int i = hash(key);
        
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.val;
        }
        return null; 
    }
    
    /**
     * Insert key-value pair into the table.
     * Node points to element of the requested key's hashvalue,
     * which is calculated to be an array index.
     * 
     * Then iterates through the elements linked list until key-value is found.
     * If key-value is not found, meaning it's not already in list,
     * then add key-value to the front of the linked list
     * 
     * @param key Key to put in table
     * @param val Vale associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        n++;
        st[i] = new Node(key, val, st[i]);
    }

    //would delete key (and associated value) from the symbol table
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete not currently supported");
    }
}
