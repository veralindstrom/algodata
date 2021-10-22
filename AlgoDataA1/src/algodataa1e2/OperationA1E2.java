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
public class OperationA1E2 {

    StdInput stdinput = new StdInput(System.in);
    StdOutput stdoutput = new StdOutput(System.out);
    
    public OperationA1E2() {
    }

    
    /**
     * Iterative function that reads characters from stdin until a newline character is 
     * read and then prints them on stdout in reverse order.
     * @param stack The stack characters are stored in.
     * @throws java.io.IOException Bc InputStream.read()
     */
    public void iterative(StackA1E2<Character> stack) throws IOException{
        char c = stdinput.readChar();
        while(!charNotValid(c)){
            stack.push(c);
            c = stdinput.readChar();
        }
        while(!stack.isEmpty()){
            char next = stack.pop();
            stdoutput.printString("[");
            stdoutput.printChar(next);
            stdoutput.printString("]");
        }
    }
    
    /**
     * Recursive function that reads characters from stdin until a newline character is 
     * read and then prints them on stdout in reverse order.
     * Base case: StdIn reads newline character.
     * @param stack The stack characters are stored in.
     * @throws java.io.IOException
     */
    public void recursive (StackA1E2<Character> stack) throws IOException{
        char c = stdinput.readChar();
       
        if(!charNotValid(c)){
            stack.push(c);
            recursive(stack);
        }
        else{
            printFromStack(stack);
        }
    }
    
    /**
     * Prints all elements in stack, LIFO, recursive.
     * Base case: if stack is empty
     * @param stack The stack characters are stored in.
     * @param size The size of the stack, aka number of characters in stack.
     */
    
    private void printFromStack(StackA1E2<Character> stack){
        if(stack.isEmpty())
            stdoutput.printString("\nStacken är tom");
        else{
            stdoutput.printString("[");
            stdoutput.printChar(stack.pop());
            stdoutput.printString("]");
            printFromStack(stack);
        }
    }
    
      private boolean charNotValid(char c) {
        return ((c == (char) -1) || (c == '\n'));
    }
    
}
