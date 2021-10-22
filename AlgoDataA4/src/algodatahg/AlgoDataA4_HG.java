/**
 * README.
 * 
 * Take a file as input and put in an EdgeWeightedGraph.
 * 
 * Then let user tell the program where he is at, where he is going to and 
 * where he would like to pass through.
 * 
 * Set the passing-vertex as the source vertex.
 * So it will be checking the paths:
 * passing -> starting, passing -> destination
 * 
 * Iterate through the path from passing -> starting:
 * What we really are looking for is the path starting -> passing. 
 * Since the data contains undirected edges we know
 * that the path from passing -> starting is also the path from starting -> passing.
 * The pathTo()-method returns a stack with path passing -> starting
 * To inverse the path, push every vertex to another stack.
 * Then pop every vertex of that new stack and enqueue each popped element to a queue.
 * 
 * Iterate through the path from passing -> destination:
 * Enqueue every vertex in the stack returned by the pathTo()-method to
 * the very same queue as used above.
 * 
 * Print path-queue.
 */
package algodatahg;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA4_HG { 
    public static void main(String[] args) throws IOException{ //test.txt
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA4/src/algodatahg/test.txt");
        Scanner filename = new Scanner(file).useLocale(Locale.US); //USA uses dot [.] to recognize decimals instead of comma [,]  
        Scanner scan = new Scanner(System.in);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(filename);
        
        System.out.println("Where is you?");
        int starting = scan.nextInt();
        
        System.out.println("Where do you want to go?");
        int destination = scan.nextInt();
        
        System.out.println("Where would you like to pass?");
        int passing = scan.nextInt();
   
        DijkstraSP sp = new DijkstraSP(graph, passing);
        double weight = 0;
        boolean pathExists;
        Stack<Integer> pathCA = new Stack<>();
        Queue<Integer> path = new Queue<>();
        
        System.out.println("DijkstraSP: ");
         if(sp.hasPathTo(starting)){
            pathExists = true;
            for(DirectedEdge e : sp.pathTo(starting)){
                pathCA.push(e.from());
                weight += e.weight();
            }
            pathCA.push(starting);
            
            while(!pathCA.isEmpty())
                path.enqueue(pathCA.pop());
        }
        else
            pathExists = false;
        
        if(sp.hasPathTo(destination) && pathExists){
            for(DirectedEdge e : sp.pathTo(destination)){
                path.enqueue(e.to());
                weight += e.weight();
            }
        }
        
        if(!pathExists)
            System.out.println("There is no such path");
        else{
            for(int i : path)
                System.out.print(i  + "→");
            System.out.print("\b\n");
            System.out.println("This path's weight is " + weight);
        }
        
    }
}