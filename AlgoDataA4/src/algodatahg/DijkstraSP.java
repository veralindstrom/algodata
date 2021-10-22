/**
 * README.
 * Dijkstra's Shortest Path algoritm.
 * 
 * First check so all edges in the EdgeWeightedGraph has positive weights.
 * 
 * Then create two useful arrays:
 * distTo[] and edgeTo[] where each index represents each vertex in the graph.
 * 
 * Set all vertices distances in distTo[] to INFINITY since for all we know,
 * that path has the weight of infinity. Meaning in this case it might be a very, very long path
 * or simply impossible to go that path.
 * 
 * Then set the source-vertex distoTo to 0.0 since it is going to be where we are at.
 * 
 * An indexed minimum priority queue is created and the source-vertex is inserted.
 * [S, 0.0]
 * 
 * Then for every element in queue: 
 *      Delete the highest priority key, hence the minimum key, from queue and save the key.
 *      S = [-1, null]
 *      Relax() every adjacent vertex (called w) to S.
 *      relax():
 *          check if the distance in distTo[] of the w vertex is greater than 
 *          the weight of that path S -> w vertex
 *          if vertex has not been visisted yet, the distTo[w] = infinity 
 *              ->in that case:
 *              •set the distTo[w] = distance to source + the weight of that path S -> w
 *              •insert the adjacent vertex to the indexed minimum priority queue
 *      
 *          if vertex has been visisted already, the distTo[w] will be the distance of the path
 *          between the source vertex and w.
 *              ->in that case:
 *              •check if this w's weight + the distance of distTo[S] at this moment is smaller than the
 *               the distance of the last w's disTo[w].
 *              •decreaseKey() with the new, smaller, distance in the indexed minimum priority queue
 * 
 * this continues for all adjacent vertices to source, hence source will be updated to each adjacent vertex
 * operating this on all conntected vertices.
 * 
 * Time complexity:
 * This algoritm is using an adjacent-list and a binary heap in the indexed priority queue.
 *  •To check all paths in the each vertex's adjacent-list has a time complexity of O(V+E), similar to BFS.
 *      In worst case, the graph consists of the same vertices over and over, making the relax()-method 
 *      call the decreaseKey()-method instead of the insert()-method in IndexMinPQ.
 *      •For a binary heap, it takes O(log V) time to operate the decreaseKey().
 * Time complexity for Dijkstra's algoritm is then: O(E+V)*O(LogV) <=> O((E+V)*LogV) <=> O(ELogV)
 */
package algodatahg;

/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Vera
 */
public class DijkstraSP {
    private final double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private final DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private final IndexMinPQ<Double> pq;    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraSP(EdgeWeightedGraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

     /**
     * Relax edge e and update pq if changed.
     * Insert the shortest path. If previous path still infinity, 
     * the new path (shorter) will be inserted.
     * If path already exist but a shorter one is encountered, the path
     * will be updated by decreaseKey().
     * 
     * @param e (the edge containing the (from) vertex, (to) vertex and weight between them)
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DijkstraSP} data type.
     *
     * @param args the command-line arguments
     */
    /*public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraSP sp = new DijkstraSP(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }*/
}
