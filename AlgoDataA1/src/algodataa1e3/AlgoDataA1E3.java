/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e3;

import algodataa1e2.StdInput;
import algodataa1e2.StdOutput;
import java.io.IOException;


/**
 *
 * @author Vera
 */
public class AlgoDataA1E3 {

    public static void main(String[] args) throws IOException {
        QueueA1E3<Character> queue = new DoubleLinkedQueueA1E3(); 
        readAndPrintQueue(queue);        
    }
    
    private static void readAndPrintQueue (QueueA1E3<Character> queue ) throws IOException{
        
        StdInput stdin = new StdInput(System.in);
        StdOutput stdout = new StdOutput(System.out);
        
        stdout.printString("Type element to be inserted: ");
        char c = stdin.readChar();
        
        while(!charNotValid(c)){
             queue.enqueue(c);
             c = stdin.readChar();
        }
       
        while(!queue.isEmpty()){
            queue.dequeue();
        }
    }
    
    private static boolean charNotValid(char c) {
        return (c == (char) -1) || (c == '\n');
    }
    
}
