/**
 * README.
 * 
 * TASK:
 * Use the FrequencyCounter from page 372 as test program 
 * (you may need to change how you read the words if you do not use the libraries from Sedgewick&Wayne). 
 * Show tables or graphs of your measurements.
 * 
 * SOLUTION:
 * FrequencyCounter checks how many times each word appears in input.
 * If word appears once, it is put as key with the value of 1 in 
 * one ordered symbol table and one binary search tree.
 * 
 * If it appears again, the key's value is added with 1.
 * Making the value represent the frequency.
 * 
 * The execution time is measured for insertion and searching for each data structure.
 * The put()-method operates insertion.
 * The contains() and get()-method operate searching.
 * 
 * The execution times is compared and put in graph.
 */
package algodataa3e2;

/**
 *
 * @author Vera
 */
public class FrequencyCounterA3E2 {
    static int MINLEN = 1; //minimum size of word to search for
    
    // Do not instantiate.
    public FrequencyCounterA3E2() { }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param input The text
     */
    public static void FrequencyCounterBinary(String[] input) {
        int distinct = 0, words = 0;
        BinarySearchTreeA3E2<String, Integer> st = new BinarySearchTreeA3E2<>();
         
        long iterationStartTimeSearch = 0;
        long iterationEndTimeSearch = 0;
        
        long iterationStartTimeInsert;
        long iterationEndTimeInsert;
        
        long executionTimeInsert = 0;
        long executionTimeSearch = 0;
        // compute frequency counts
        for(String key : input) {
            if (key == null || key.length() < MINLEN) continue; //if word is too small, skip
            
            words++;
            
            iterationStartTimeSearch = System.nanoTime();
                boolean contains = st.contains(key);
            iterationEndTimeSearch = System.nanoTime() - iterationStartTimeSearch;
            
            if (contains) {  //if word has appeared before
                
                int newValue = st.get(key) + 1; //add 1 to value
                
                iterationStartTimeInsert = System.nanoTime();
                    st.put(key, newValue);  
                iterationEndTimeInsert =System.nanoTime() - iterationStartTimeInsert;
            }
            else {
                iterationStartTimeInsert = System.nanoTime();
                    st.put(key, 1);  //if word has not appeared before, put value to 1
                iterationEndTimeInsert =System.nanoTime() - iterationStartTimeInsert;
                distinct++;
            }
            
            executionTimeInsert+=iterationEndTimeInsert;
            executionTimeSearch+=iterationEndTimeSearch;
        }
        
        
        // find a key with the highest frequency count
        String highestFrequency = "";
        st.put(highestFrequency, 0);
        for (String key : input) {
            if (key == null || key.length() < MINLEN) continue;
            if (st.get(key) > st.get(highestFrequency))
                highestFrequency = key;
        }
        
        System.out.println("Binary Search Tree insert execution time: " + formatBigNumbers(executionTimeInsert) + "ns");
        System.out.println("Binary Search Tree search execution time: " + formatBigNumbers(executionTimeSearch) + "ns");

        System.out.println("Most frequent word is \"" + highestFrequency + "\" and it is used " + st.get(highestFrequency) + " times");
        System.out.println("Distinct words = " + distinct);
        System.out.println("Total words    = " + words);
        System.out.println("MINLEN         = " + MINLEN);
    }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param input
     */
    public static void FrequencyCounterOrdered(String[] input) {
        int distinct = 0, words = 0;
        OrderedSTA3E2<String, Integer> st = new OrderedSTA3E2<>();
        
        long iterationStartTimeSearch = 0;
        long iterationEndTimeSearch = 0;
        
        long iterationStartTimeInsert;
        long iterationEndTimeInsert;
        
        long executionTimeInsert = 0;
        long executionTimeSearch = 0;
        
        // compute frequency counts
        for(String key : input) {
            if (key == null || key.length() < MINLEN) continue;
            words++;
            
            iterationStartTimeSearch = System.nanoTime();
                boolean contains = st.contains(key);
            iterationEndTimeSearch = System.nanoTime() - iterationStartTimeSearch;
            
            if (contains) {
               int newValue = st.get(key) + 1; //add 1 to value
                
                iterationStartTimeInsert = System.nanoTime();
                    st.put(key, newValue);  
                iterationEndTimeInsert =System.nanoTime() - iterationStartTimeInsert;
            }
            else {
                iterationStartTimeInsert = System.nanoTime();
                    st.put(key, 1);  //if word has not appeared before, put value to 1
                iterationEndTimeInsert =System.nanoTime() - iterationStartTimeInsert;
                distinct++;
            }
            executionTimeInsert+=iterationEndTimeInsert;
            executionTimeSearch+=iterationEndTimeSearch;
        }
        
        // find a key with the highest frequency count
        String highestFrequency = "";
        st.put(highestFrequency, 0);
        for (String key : input) {
            if (key == null || key.length() < MINLEN) continue;
            if (st.get(key) > st.get(highestFrequency))
                highestFrequency = key;
            
        }
        
        //long executionTime = System.currentTimeMillis() - startTime;
        System.out.println("Ordered Array ST insert execution time: " + formatBigNumbers(executionTimeInsert) + "ns");
        System.out.println("Ordered Array ST search execution time: " + formatBigNumbers(executionTimeSearch) + "ns");
         
        System.out.println("Most frequent word is \"" + highestFrequency + "\" and it is used " + st.get(highestFrequency) + " times");
        System.out.println("Distinct words = " + distinct);
        System.out.println("Total words    = " + words);
        System.out.println("MINLEN         = " + MINLEN);
    }
    
    /**
     * Formats big number.
     * ex.
     * x,xxx,xxx
     * @param bigNumber
     * @return 
     */
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


    /**
     * A test class.
     */
    private static class Test {
       public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = 3;
        BinarySearchTreeA3E2<String, Integer> st = new BinarySearchTreeA3E2<>();
        //OrderedSTA3E2<String, Integer> st = new OrderedSTA3E2<>();
        //Scanner scan = new Scanner(System.in);
        
        String[] input = {"hej", "då", "hej", "jaha", "jaha", "jaha", "jaha", "jaha", "jaha", "jaha"};
        
        // compute frequency counts
        for(String key : input) {
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String highestFrequency = "";
        st.put(highestFrequency, 0);
        for (String key : input) {
            if (key.length() < minlen) continue; //if word is too small, skip
               
            if (st.get(key) > st.get(highestFrequency))  //if key with higher value(number of words) is found
                highestFrequency = key;   //set that key to highest frequency
        }

        System.out.println("Most frequent word is \"" + highestFrequency + "\" and it is used " + st.get(highestFrequency) + " times");
        System.out.println("Distinct words = " + distinct);
        System.out.println("Total words    = " + words);
    }
    }
}
