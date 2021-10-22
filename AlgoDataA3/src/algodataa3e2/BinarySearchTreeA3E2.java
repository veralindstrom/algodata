/**
 * README.
 * 
 * Binary Search Tree sets root to first key-value pair then puts next key-value pair
 * to left or right depending on if its key is greater or smaller than root-key. Continues by setting
 * root to subroot, recursively.
 * 
 * insert: key2 and key3
 * left: key2 < root, right: key3 > root
 *                 root
 *            [key1][value1]
 *  subroot(left)          subroot (right)
 * [key2][value2]          [key3][value3]
 * 
 * 
 * 
 */
package algodataa3e2;

/**
 * Implementation of a Binary Search Tree.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchTreeA3E2<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BinarySearchTreeA3E2

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    
    /**
     * Initializes an empty symbol table.
     */
    public BinarySearchTreeA3E2() {
    }
 
    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Gets requested key.
     * Checks if requested key is smaller or greater than root, then sets subroot
     * recursivly until key is found.
     * 
     * If no key-value pairs rooted at x exist or given key is not in ST throw exception.
     * @param x Root
     * @param key Key-value to get.
     * @return Value of key to get.
     */
    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
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
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");

        root = put(root, key, val);
    }
    
    /**
     * INSERTION method recursively calls on it self till the given keys right position
     * in the tree is found and puts the key-value pair there. (x.key = root-key)
     * If no key-value pairs root exist throw exception.
     * @param x Root 
     * @param key Key to be inserted
     * @param val Value of the specified key
     * @return 
     */

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    /**
     * Returns the number of key-value pairs in this BST.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Return number of key-value pairs.
     * @param x Where to start the count
     * @return Number of key-value pairs rooted at x
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }
    
    
    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
     
    /**
     * Does this symbol table contain the given key?
     * Checks if given key exist in BST.
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    
}
