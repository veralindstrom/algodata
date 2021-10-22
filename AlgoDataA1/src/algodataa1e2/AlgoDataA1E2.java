/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e2;

import java.io.IOException;

/**
 *
 * @author Vera
 */
public class AlgoDataA1E2 {
   
    /**
     * Main method calling run().
     * Main is a static method, so a non-static method, 
     * such as iterativeStack() and recursiveStack()
     * can not be reference from a static method.
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        OperationA1E2 op = new OperationA1E2();
        
        run(op);
        
    }
    
    
    private static void run(OperationA1E2 op) throws IOException {
  
        /* -- ITERATIVE -- */
        System.out.print("ITERATIVE\n");
        StackA1E2<Character> iterativeStack = new StackA1E2<>();
        op.iterative(iterativeStack);
         
        /* -- RECURSIVE -- */
        System.out.print("\nRECURSIVE\n");
        StackA1E2<Character> recursiveStack = new StackA1E2<>();
        op.recursive(recursiveStack);
        
    }
}
    
