/**
 * README.
 * TASK:
 * Implement a program which takes as input a text file and allows the user to 
 * (repeatedly without re-reading the input file) ask questions: 
 *   1) Which is the k:th most common word
 *   2) Which are the k:th to the k+n:th most common words
 * 
 * SOLUTION:
 * FrequencyCounter checks how many times each word appears in input.
 * If word appears once, it is put as key with the value of 1 in 
 * a hash symbol table.
 * 
 * If it appears again, the key's value is added with 1.
 * Making the value represent the frequency.
 * 
 * Two arrays are created, one for keys and one for values, with the size of distinct words since
 * each distinct word is put as key with one value.
 * 
 * When we copy the keys and values to respective arrays, we multiply each value by -1
 * so that when we later sort the array with quicksort they will be sorted in descending order,
 * making the key with the highest value (frequency) become first element.
 * 
 * To let the user repeatedly ask questions, without re-reading the file, 
 * we implement an infinite loop after we've read the file and sorted the key-value pairs.
 * 
 * The questions is then answered in contanst time since we can access the positions with 
 * hashvalues, meaning we do not have to loop through the whole symbol table every time we need to
 * find key-value pairs.
 * 
 * When printing the key-value pairs we multiply the value with -1, again, to get the
 * original value.
 * 
 * Time complexity for searching in hash tables is O(1), constant.
 */
package algodataa3hg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA3HG {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3hg/leipzigoutput.txt");
        Scanner input = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        QuickSort qs = new QuickSort();
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>(164860);
        //164,860 is the number of distinct words
        
        //Frequency Counter
        int distinct = 0;
        long startTime = System.currentTimeMillis();
        while(input.hasNext()) {
            String key = input.next().toLowerCase();
            if (key == null || key.length() < 1) continue; //if word is too small, skip
            //words++;
            if (st.contains(key)) {  //if word has appeared before
                st.put(key, st.get(key) + 1);  //add 1 to value
            }
            else {
                st.put(key, 1);  //if word has not appeared before, put value to 1
                distinct++;
            }
        }
        long endTime = System.currentTimeMillis()- startTime;
        
        System.out.println("Time to read file and build the data structure: " + formatBigNumbers(endTime) + " ms");
        
        //Create arrays for keys and values
        String keys[] = new String[distinct];
        Integer values[] = new Integer[distinct];
        
        //Insert all keys to {@code keys} array
        int i = 0;
        for (String key : st.keys()){
            keys[i] = key;
            //System.out.println("Keys[]: " + keys[i]);
            i++;
        } 
        
        /*Insert all values to {@code values} array
          Multiply value with -1 to get quicksort to sort 
          descending order.*/
        int j = 0;
        for (Integer value : st.values()) {
            values[j] = value * -1;
            //System.out.println("Values[]: " + values[j]);
            j++;
        }
        
        //sort arrays
        qs.sort(values, keys);
        
        //infinite loop to let user ask questions and
        //get the answers in constant time
        System.out.println("Distinct: " + formatBigNumbers(distinct));
        while(true){
            System.out.println("Which positions would you like to see? ");
            System.out.print("Start: ");
            int k = sc.nextInt();
            System.out.print("End: ");
            int n = sc.nextInt();
            
            for(int w = k; w <= n; w++)
                System.out.println("#" + w + " most common word: [" + keys[w-1] + "] with " + formatBigNumbers(values[w-1]*-1) + " occurrences");  
            
            System.out.println("");
        }
    }
    
    public static StringBuilder formatBigNumbers(long bigNumber) {
        StringBuilder sb = new StringBuilder();
        int counter = 1;

        while (bigNumber > 0) {
            sb.insert(0, bigNumber % 10 );
            bigNumber = bigNumber / 10;
            if(counter == 3){
                sb.insert(0, ",");
                counter = 0;
            }
            counter++;
        }
        if(sb.indexOf(",") == 0) {
            sb.delete(0,1);
        }
        return sb;
    }
}
