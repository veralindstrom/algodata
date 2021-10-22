/**
 * README.
 * 
 * TASK:
 * Write an "index"-program which allows the user to ask questions "on which positions 
 * in the text (i.e. the number of characters from the beginning) you find the word X". 
 * The program should list the position of all occurrences of X as answer to a query. 
 * In this assignment you may use the Java library (built-in) lists. 
 * Questions to the index should be answered in time less or equal to O(log(N)) where N 
 * is the number of keys.
 * 
 * SOLUTION:
 * Reads in filtered file.
 * Appends whole file to one stringbuilder so that we always have access to next character.
 * Check character by character until first character of next word is reached, 
 * then store those characters in {@code word} stringbuilder
 * 
 * ex.
 * 'h' 'e' 'l' 'l' 'o' '_space_' 'b' y' 'e' => (1) {@code word} = "hello"
 *                                             (2) {@code word} = "bye"
 * Every character is counted.
 * Each word is put to an ordered symbol table with the word as a key and a list of the positions 
 * (number of characters from beginning) as value.
 * 
 * If word appears more than once:
 * Each value consist of a list. First time word appears it's list contains that position.
 * When word appaears again we get the respective list and adds the second position where it appeared.
 * Over and over for every time word appears.
 * 
 * User enters what word to find.
 * 
 * We check if symbol table contains word. 
 * If it does, we get the list of positions where word appears.
 * 
 * Newline is represented by three spaces
 */
package algodataa3e4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Vera
 */
public class AlgoData_A3E4 {
    public static int position = 0;
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e4/output_interpret.txt");
        Scanner input = new Scanner(file);
        Scanner scan = new Scanner(System.in);
        //787852
        //804330
        //804333
        
        //788062
        OrderedST_A3E4<String, List> st = new OrderedST_A3E4<>();
        StringBuilder theWholeText = new StringBuilder();
        
        System.out.println("What word would you like to find? ");
        String wordToFind = scan.nextLine();
        
        //append all lines in file to one stringbuilder
        while(input.hasNextLine()){
            theWholeText.append(input.nextLine().toLowerCase());
            
        }
        
        //int spaces = 0;
        int charCounter;
        int i = 0;
        int len = theWholeText.length();
        
        //for each word...
        while(i < len) {
            int spaces = 0;
            StringBuilder word = new StringBuilder();
            
            //...read stringbuilder character by character...
            while( position < theWholeText.length()){
                
                /*32 is ascii for _space_
                  if-statement is to not append _space_ to {@code word}
                  but we still need to count them as characters*/
                if((int)theWholeText.charAt(position) == 32){
                    while((int)theWholeText.charAt(position) == 32){
                        spaces++;
                        position++;
                    }
                   break;
                }
                //...and stores characters to {@code word} creating a word
                else{
                    word.append(theWholeText.charAt(position));
                    position++;
                }
                
            }
            
            
            charCounter = position + 1 - spaces; //represent the number of characters excluding spaces, making position start at 1 instead of 0
            String key = word.toString(); //converts word (stringbuilder) to a string
            
            
            if (st.contains(key)) {  //if word has appeared before
                List temp = st.get(key);
                temp.add(charCounter - key.length());
                st.put(key, temp);  //add 1 to value
            }
            else {
                List positions = new ArrayList();
                positions.add(charCounter - key.length());
                st.put(key, positions);  //if word has not appeared before, put value to 1
            }
            i++;
        }
        
        if(st.contains(wordToFind)){
        System.out.println("Word was found " + st.get(wordToFind).size() + 
                " times at positions: " + st.get(wordToFind));
        }
        else
            System.out.println("Word was not found");
    }
}

/*
File file = new File("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e3/output.txt");
        Scanner word = new Scanner(file);
        Scanner scan = new Scanner(System.in);
        
        
        OrderedST_A3E4<String, List> st = new OrderedST_A3E4<>();
        
        System.out.println("What word would you like to find? ");
        String wordToFind = scan.nextLine();
        
        //read file word by word and store in array
        int charCounter = 1;
        
        while (word.hasNext()) {
           
            String key = word.next().toLowerCase();
            //System.out.println(key);
            for(int i = 0; i < key.length(); i++){
                System.out.println(key.charAt(i));
                charCounter++;
            }
            
            if (st.contains(key)) {  //if word has appeared before
                List temp = st.get(key);
                temp.add(charCounter);
                st.put(key, temp);  //add 1 to value
            }
            else {
                List positions = new ArrayList();
                positions.add(charCounter);
                st.put(key, positions);  //if word has not appeared before, put value to 1
            }
            
        }
        
        List positionsOfWordToFind = new ArrayList();
        
        positionsOfWordToFind.addAll(st.get(wordToFind));
        
        System.out.println("Word was found " + positionsOfWordToFind.size() + 
                " times at positions: " + positionsOfWordToFind);
        
    
*/
