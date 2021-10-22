/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e2;

import java.io.PrintStream;

/**
 *
 * @author Vera
 * A PrintStream adds functionality to another output stream.
 */
public class StdOutput {
    private final PrintStream printstream;
    
    public StdOutput(PrintStream printstream){
        this.printstream = printstream;
    }
    
    public void printString(String s){
        printstream.print(s);
    }
    
    public void printChar(char c){
        printstream.print(c);
    }
    
    public void printInt(int i){
        printstream.print(i);
    }
}
