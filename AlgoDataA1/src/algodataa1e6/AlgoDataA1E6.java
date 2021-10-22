/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e6;

import algodataa1e2.StdInput;
import algodataa1e2.StdOutput;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA1E6 {
    public static void main (String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        StdInput stdin = new StdInput(System.in);
        StdOutput stdout = new StdOutput(System.out);
        QueueA1E6<Integer> queue = new OrderedQueueA1E6<>();
        
        for(int i = 0; i<6; i++){
            stdout.printString("Type element to be inserted: ");
            int element = in.nextInt();
            queue.operation(element);
            queue.print();
        }
        
        stdout.printString("Which element (int) to be removed: ");
            int k = in.nextInt();
            queue.dequeueKthElement(k);
        
            queue.print();
     }
    
}
