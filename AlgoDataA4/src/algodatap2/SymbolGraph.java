/**
 * README.
 * A symbol graph.
 * 
 * To combine a symbol table with a directed graph.
 * 
 * Iterate thorugh input and puts every uniqe vertex in a symbol table with each input-vertex as key,
 * this symbol table does not include any doublets.
 * 
 * Creates a digraph.
 * 
 * Iterate through input and get corresponding key-value from symbol table, 
 * (key-value represents index in the adjacent[]-array in digraph)
 *  •addEdge()-method puts only the adjacent vertex (next vertex in input-line) in the specfied key's bag, hence directed.
 * The bag will contain every adjacent vertex of the key-vertex.
 * 
 */
package algodatap2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Vera
 */
public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Digraph graph;             // the underlying graph

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     *
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     * @throws java.io.FileNotFoundException
     */
    public SymbolGraph(File filename, String delimiter) throws FileNotFoundException {
        st = new ST<>();
        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        Scanner scan = new Scanner(filename);
        // while (in.hasNextLine()) {
        while (scan.hasNextLine()) {
            String[] a = scan.nextLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }
       
        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        
        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Digraph(st.size());
        scan = new Scanner(filename);
        while (scan.hasNextLine()) {
            String[] a = scan.nextLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     *
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     *
     * @return the graph associated with the symbol graph
     */
    public Digraph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(graph.V()).append(" vertices, ").append(graph.E()).append(" edges \n");
        for (int v = 0; v < graph.V(); v++) {
            s.append(nameOf(v)).append(": ");
            for (int w : graph.adj(v)) {
                s.append(nameOf(w)).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
