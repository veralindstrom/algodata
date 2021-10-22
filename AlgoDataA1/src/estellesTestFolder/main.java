/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

import java.io.IOException;
import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Vera
 */

public class main {
     public static void main(String[] args) {
        String word;
        System.out.println("Enter your Word:");
        Scanner input = new Scanner(System.in);
        word = input.nextLine();
        input.close();
        String reverse = reverseRec(word);
        System.out.println("your word using recursion is " + reverse);
        System.out.print("your word using iterative is ");
        reverseItr(word);

    }


    public static String reverseRec(String word) {
        if (word.isEmpty()) {
            return word;
        }
        //substring(), returnerar från specifierad till slutet av String
        //charAt returnerar char i en specifik index i en String
        return reverseRec(word.substring(1)) + word.charAt(0);
        }

    public static void reverseItr(String word) {
        //base case: om string är null eller tom
        if (word == null || word == "")
            return;

        char[] input = word.toCharArray();
        int size = input.length;

        //skapar en tom stack
        Stack stack = new Stack();

        //pusha varje char i stringen in i stacken
        //string.toCharArray converts given string to char array
        //char[] input = word.toCharArray();
        for (int i = 0; i < word.length(); i++)
            stack.push(input[i]);
    
        //int size = input.length;
        //pop char från stack tills den är tom
        while (!stack.isEmpty())
        {
            //assign each popped char back to character array
            System.out.print( stack.pop() );
        }
    }

}
