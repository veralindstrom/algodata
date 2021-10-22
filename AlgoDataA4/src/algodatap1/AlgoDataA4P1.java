/**
 * README.
 * 
 * TASK:
 * 1. Write a program based on DFS which can answer questions of the type: "Find the a path from X to Y" 
 * Which should result in a list of vertices traversed from X to Y if there is a path.
 * 2. Change the program to use BFS.
 * 
 * Read a file as input for the symbolgraph. Then ask the user where he at, and where his destination is.
 * Take the user input and look for its index in the symbolgraph.
 * 
 * Create my dfs and bfs putting the users location as source vertex.
 * Iterate through the path-stack of that source vertex until destination is reached.
 */
package algodatap1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA4P1 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException { //contiguous-usa.dat //test-data-example
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA4/src/algodatap1/contiguous-usa.dat.txt");
        Scanner scan = new Scanner(System.in);
        SymbolGraph sg = new SymbolGraph(file, " ");
        Graph graph = sg.graph();
        System.out.println("Where is you?");
        int starting = sg.indexOf(scan.next().toUpperCase());
        System.out.println("Where you going?");
        int destination = sg.indexOf(scan.next().toUpperCase());

        DepthFirstPaths dfs = new DepthFirstPaths(graph, starting);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, starting);
        
        System.out.println("DFS: ");
        if(dfs.hasPathTo(destination)){
            for(int i : dfs.pathTo(destination))
                System.out.print(sg.nameOf(i) + "-");
            System.out.print("\b\n\n");
        }
        
        System.out.println("BFS: ");
        if(bfs.hasPathTo(destination)){
            for(int i : bfs.pathTo(destination))
                System.out.print(sg.nameOf(i) + "-");
            System.out.print("\b\n");
        }
    }
}