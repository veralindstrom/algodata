/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e2;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Vera
 * InputStream is an abstract class and is the superclass of all classes representing an input stream of bytes.
 */
public class StdInput {
    private final InputStream input;
    
    public StdInput (InputStream input){
        this.input = input;
    }
    public char readChar() throws IOException {
        int c = input.read();
        return (char) c;
    }
    
    public int readInt() throws IOException {
        int c = input.read();
        return (int) c;
    }
}
