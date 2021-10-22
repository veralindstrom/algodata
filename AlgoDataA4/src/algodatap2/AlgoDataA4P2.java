/**
 * README.
 * 
 * TASK:
 * Write a program that can answer if there is a path between any two vertices.
 * 
 * SOLUTION:
 * Read a file as input for the symbolgraph. Ask user where he at, and where his destination is.
 * Take the user input and look for its index in the symbolgraph.
 * 
 * Create a directed dfs and putting the users location as source vertex.
 * The path will be marked by directedDFS if the destination i reachable from the source vertex.
 * 
 * Check if the users destination is marked in the marked[]-array in directedDFS, if it is then
 * there is a path from his location to the desired destination.
 */
package algodatap2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA4P2 {
    public static void main(String[] args) throws FileNotFoundException { //contiguous-usa.dat //test-data-example
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA4/src/algodatap1/contiguous-usa.dat.txt");
        Scanner scan = new Scanner(System.in);
        SymbolGraph sg = new SymbolGraph(file, " ");
        Digraph graph = sg.graph();
        System.out.println("Where is you?");
        int starting = sg.indexOf(scan.next().toUpperCase());
        System.out.println("Where you going?");
        int destination = sg.indexOf(scan.next().toUpperCase());

        DirectedDFS dfs = new DirectedDFS(graph, starting);
        
        if(dfs.marked(destination))
            System.out.println("Yay, there is a direct path between " + sg.nameOf(starting) + " and " + sg.nameOf(destination));
        else 
            System.out.println("Sorry, there is no direct path between " + sg.nameOf(starting) + " and " + sg.nameOf(destination));
    
    }
}
