/**
 * README.
 * 
 * TASK:
 * Use the first N (N in the order of hundred words) words from the text to compare the running 
 * times of the ordered array ST (also known as binary search symbol table, see algorithm 3.2 in 
 * the book to the Binary Search Tree algorithm.
 * Use the FrequencyCounter from page 372 as test program
 * 
 * SOLUTION:
 * Reads in file and stores the first N (in the order of hundered) in an array which is sent as
 * input to the frequencyCounter test class.
 * 
 */
package algodataa3e2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA3E2 {
    public static int N = 2; //N in the order of hundred words, ex. N = 4 => first 400 words
    
    public static void main(String[] args) throws IOException {    
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e2/output_interpret.txt");
        Scanner word = new Scanner(file); 
        FrequencyCounterA3E2 fc = new FrequencyCounterA3E2();
        
        String[] words = new String[(int) 100 * N];
        //String[] words = new String[(int) file.length()];
        
        
        //read file word by word and store in array
        int i = 0;
        //while (word.hasNext()) {
        while (i < words.length) {
            words[i] = word.next().toLowerCase();
            //System.out.println(i + words[i]);
            i++;
        }
        System.out.println("Length: " + i);

        fc.FrequencyCounterOrdered(words);
        
        fc.FrequencyCounterBinary(words);
    
    } 
}
