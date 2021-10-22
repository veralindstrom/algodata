/**
 * README.
 * 
 * TASK:
 * Write a program that shows how evenly the built-in hashcode() function 
 * for strings in Java distributes the hashcodes for the words found in the text.
 * (Hint it may be hard to use the hashcodes directly...)
 * 
 * SOLUTION:
 * First set an interval of how many hashes that should be distributed.
 * Read in file word by word.
 * Put every key in hash table with the value of (for example) 1.
 * In this exercise, the value is not important since we only want to check 
 * the keys distribution over hashes.
 * 
 * Every key with the same hashvalue is put to the same linked list which is accessed with the hashvalue.
 * 
 * Then loop through all hashes and check the size of each hash's linked list.
 * 
 * The sizes of each linked list is compared in a graph and the question is answered from the result.
 */
package algodataa3e3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Vera
 */
public class AlgoDataA3E3 {
    public static int INTERVAL = 9944; //number of distinct words
    public static void main(String[] args) throws FileNotFoundException{  
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e3/output_interpret.txt");
        Scanner input = new Scanner(file); 
        
        SeparateChainingHash_A3E3<String, Integer> st = new SeparateChainingHash_A3E3<>(INTERVAL);
        //141491 words
        //9944 uniqe (with toLowerCase)
        
        //read file word by word and store in array
        while (input.hasNext()) {
            String key = input.next().toLowerCase();
            st.put(key, 1);
        }
        
        for(int i = 0; i < INTERVAL; i++){
            
            System.out.println("In hash #" + (i+1) + "  there are  " + st.sizeOfList(i) + " nodes");
        }
    }
}