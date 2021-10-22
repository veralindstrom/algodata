/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e4;

import algodataa1e2.StdInput;
import algodataa1e2.StdOutput;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA1E4 {
    public static void main (String[] args) throws IOException {
	CircularLinkedListA1E4<Character> queue = new CircularLinkedListA1E4<>();
	operations(queue);
		
    }	
    
    
    private static void operations (CircularLinkedListA1E4<Character> queue) throws IOException{
        enqueue(queue);
        dequeue(queue);
    }
    
    private static void enqueue(CircularLinkedListA1E4<Character> queue) throws IOException{
        StdInput stdinput = new StdInput(System.in);
        StdOutput stdout = new StdOutput(System.out);
        Scanner scanner = new Scanner(System.in);
        
        CircularLinkedListA1E4<Character> tempQueueForInput = new CircularLinkedListA1E4<>();
        
        stdout.printString("Please type elements to be inserted: \n");
        char c = stdinput.readChar();
        
        stdout.printString("Elements from input \n");
        while(!charNotValid(c)){
             tempQueueForInput.enqueueToBack(c);
             c = stdinput.readChar();
        }
         //stdout.printString("Size of tempQueue: " + tempQueueForInput.size);
         stdout.printString("\n");
         int count = tempQueueForInput.size - 1;
         
         for(char element:tempQueueForInput){
            if(queue.isEmpty())
                queue.enqueueToFront(element);
            else{
                stdout.printString("Do you wanna insert ["+ element +"] to front: press [f]  or back: press [b] of queue?\n");
                String placeToInsert = scanner.nextLine();
                
                switch (placeToInsert) {
                    case "f":
                       //CALL ENQUEUE TO FRONT
                        count--;
                       queue.enqueueToFront(element);
                       break;
                    case "b":
                        //CALL ENQUEUE TO BACK
                       count--;
                       queue.enqueueToBack(element);
                       break;
                 default:
                       stdout.printString("Invalid choice");
                       break;
                }
            if(count == 0)
                break; 
            }
         }
    }
    
    private static void dequeue(CircularLinkedListA1E4<Character> queue) throws IOException{
         StdOutput stdout = new StdOutput(System.out);
         Scanner scanner = new Scanner(System.in);
         
         stdout.printString("\n");
         
         while(!queue.isEmpty()){
            stdout.printString("Do you wanna delete element from front: press [f]  or back: press [b] of queue?\n");
                String placeToInsert = scanner.nextLine();
                switch(placeToInsert){
                    case "f":
                        //CALL ENQUEUE TO FRONT
                        queue.dequeueFromFront();
                        break;
                    case "b":
                        //CALL ENQUEUE TO BACK
                        queue.dequeueFromBack();
                        break;
                    default: 
                         stdout.printString("Wrong selection");
                }
        }
    }
    
    
    private static boolean charNotValid(char c) {
        return (c == (char) -1) || (c == '\n');
    }
    
    
}
