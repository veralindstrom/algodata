/**
 * README.
 * Depth first paths looks for at path from source to destination,
 * by checking from source the path till the end location, where no more 
 * edges are connected. 
 * 
 * It checks as if the connections where directed as a path from
 * the given source to the the given destination is the direction
 * sought for. But the graph that holds the found connection between
 * any two vertices has it in both directions, making it undirected.
 * Hence, the path from destination to source is valid as well.
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
 * return true and the pathTO() can be applied to show the found path from the 
 * source to the destination.
 */
package algodatap1;


/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Vera
 */
public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * V() Returns the number of vertices in this graph.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return (Iterable<Integer>) path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DepthFirstPaths} data type.
     *
     * @param args the command-line arguments
     */
    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);
        
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                System.out.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) System.out.print(x);
                    else        System.out.print("-" + x);
                }
                System.out.println();
            }

            else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }*/
}
