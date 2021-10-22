/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1highergrade;

import algodataa1e2.StdInput;
import java.io.IOException;

/**
 *
 * @author Vera
 */
public class AlgoDataA1HigherGrade {
    public static void main (String[] args) throws IOException{
        StdInput stdin = new StdInput(System.in);
        ListHigherGrade<Character> list = new LinkedListHigherGrade();
        
     char c = stdin.readChar();
        while(!charNotValid(c)){
            list.enqueue(c);
            c = stdin.readChar();
        }
        
        list.print();
       
        list.checkForMatches('(', ')');
        list.checkForMatches('[', ']');
        list.checkForMatches('{', '}');
        
    }
    
    private static boolean charNotValid(char c) {
        return (c == (char) -1) || (c == '\n');
    }
}
