/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e5;

import algodataa1e2.StdInput;
import algodataa1e2.StdOutput;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA1E5 {
     public static void main (String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        StdInput stdin = new StdInput(System.in);
        StdOutput stdout = new StdOutput(System.out);
        QueueA1E5<Character> queue = new GeneralizedQueueA1E5<>();
        
        stdout.printString("Type element to be inserted: ");
        char c = stdin.readChar();
        
        while(!charNotValid(c)){
             queue.enqueue(c);
             c = stdin.readChar();
        }
        queue.print();
        
        stdout.printString("Which element (int) to be removed: ");
        int k = in.nextInt();
            queue.dequeueKthElement(k);
        queue.print();
     }
    
    private static boolean charNotValid(char c) {
        return (c == (char) -1) || (c == '\n');
    }
    
}
