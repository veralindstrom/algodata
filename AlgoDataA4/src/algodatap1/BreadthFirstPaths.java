/**
 * README.
 * Breadth first paths looks for at path from source to destination,
 * by checking from source all the paths it is adjacent and connects to
 * and continues the same process for each adjacent vertex it has by checking 
 * them one at the time for all their adjacent vertices. When reached the end 
 * of one sub-source to source, the next sub-source to same source is checked 
 * for all its adjacent vertices continuing the process till there are no more
 * unchecked connections.
 * 
 * It checks as if the connections where directed as a path from
 * the given source to the the given destination is the direction
 * sought for. But the graph that holds the found connection between
 * any two vertices has it in both directions, making it undirected.
 * Hence, the path from destination to source is valid as well.
 * 
 * When marking the adjacent paths as checked, the vertices are placed
 * in a (FIFO) queue that is dequeued when all adjacent vertices to that sources 
 * are checked. When dequeuing one source-adjacent, all its adjacent components 
 * are marked as checked and placed in the queue and the process repeated by 
 * dequeuing them one at the time checking for their adjacent vertices. 
 * 
 * Process continues till no more elements are left in the queue, breaking the
 * loop with all paths reachable from the given source marked.
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
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE; //max_value to achive
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between the source vertex {@code s}
     * and every other vertex in the graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);

    }

    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<>();
        for (int v = 0; v < G.V(); v++){
            distTo[v] = INFINITY; //set all undiscovered distances to infinity since for all we know 
                                  //the distance is infinite or not even a possible path
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1; //when adding 1 to infinity => overflow, java handles it by starting from 0
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path between the source vertex {@code s}
     * (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns a shortest path between the source vertex {@code s} (or sources)
     * and {@code v}, or {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return (Iterable<Integer>) path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code BreadthFirstPaths} data type.
     *
     * @param args the command-line arguments
     */
    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //In in = new In(args[0]);
        Graph G = new Graph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) System.out.print(x);
                    else        System.out.print("-" + x);
                }
                System.out.println();
            }

            else {
                System.out.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }*/
}
