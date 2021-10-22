/**
 * README.
 * Directed depth first paths looks for at path from source to destination,
 * by checking from source the path till the end location, where no more 
 * edges are connected. 
 * 
 * It checks the directed connections leading from the given source to the 
 * given destination from the bag of the source-vertex in the digraph where
 * all connections reached from the source are collected, giving the direction.
 * Hence, the path from destination to source, is not (necessarily) valid.
 * 
 * When the end of a checked path is reached the recursive call backtracks to 
 * the last source of the final location, checking all unmarked path that the
 * sub-source (to the last marked) can reach (if any) and continues to backtrack 
 * till main source where new paths adjacent are marked till their end and get 
 * back to the source.
 * 
 * When all paths from source (to sub-source, to sub-source) are marked
 * and the backtrack has ended, if no more adjacent vertices to the source 
 * are left unmarked (to check connecting paths to) ... then all
 * paths reachable from the given source are marked.
 * 
 * To find a given destination from the previously given source, if the source 
 * hasPathTo(destination) that destination will be marked and the method will
 * return true.
 */
package algodatap2;


/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Vera
 */
public class DirectedDFS {
    private boolean[] marked;  // marked[v] = true iff v is reachable from source(s)
    private int count;         // number of vertices reachable from source(s)

    /**
     * Computes the vertices in digraph {@code G} that are
     * reachable from the source vertex {@code s}.
     * @param G the digraph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }
    
    //recursive
    private void dfs(Digraph G, int v) { 
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    /**
     * Is there a directed path from the source vertex (or any
     * of the source vertices) and vertex {@code v}?
     * @param  v the vertex
     * @return {@code true} if there is a directed path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of vertices reachable from the source vertex
     * (or source vertices).
     * @return the number of vertices reachable from the source vertex
     *   (or source vertices)
     */
    public int count() {
        return count;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    } 
}
