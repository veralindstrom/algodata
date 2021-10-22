/**
 * README.
 * 
 * TASK
 * Compare the execution times for sorting large arrays of integers with insertionsort and merge sort. 
 * When should one select mergesort over insertionsort? Upload code, tests and a graphs depicting 
 * the execution times as a function of input (what parameters in the input could be relevant?).
 * 
 * SOLUTION
 * Taking two arrays with the same elements of random generated numbers and sorting them in with
 * insertion sort and merge sort.
 * 
 * For both sorting methods the execution time is returned from the method.
 * 
 * Then printing the execution time for both and also comparing them to see which one was faster.
 */
package algodataa2e5;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA2E5 {
    //static int arraySize = 10000; //Number of elements in the array
    static final double MULTIPLE = 2.0; //After every iteration, the array size changes by this much
    //static final int RANGE = arraySize; //The values in the array are between 1 and RANGE
    static final int TIMES_SORTED = 5; //How many times to do the tests
    
     public static void main(String[] args) {
          Scanner scan = new Scanner(System.in);
        Random rand = new Random(); 
        
        InsertionSortA2E5 insertion = new InsertionSortA2E5();
        MergeSortA2E5 merge = new MergeSortA2E5();
        
        for(int j = 0; j < TIMES_SORTED; j++){
             System.out.println("Size: ");
            int arraySize = scan.nextInt();
            
            int[] arrIns = new int[arraySize];
            int[] arrMer = new int[arraySize];
            int[] a = new int[arraySize];
           
            for(int i = 0; i < arraySize; i++)
                arrIns[i] = arrMer[i]  = rand.nextInt(arraySize);
        
            System.out.println("Insertion sort RANDOM INTS: ");
            long elapsedTimeInsertion = insertion.sort(arrIns);
            System.out.println("Execution time: " + elapsedTimeInsertion/1000000 + "ms"); 
            System.out.println("Array size: " + arrIns.length); 
        
            System.out.println("Merge sort RANDOM INTS: ");
            long elapsedTimeMerge = merge.sort(arrMer);
            System.out.println("Execution time: " + elapsedTimeMerge/1000000 + "ms");
            System.out.println("Array size: " + arrMer.length); 
            
            if(elapsedTimeMerge < elapsedTimeInsertion)
                System.out.println("Merge sort was faster");
            else if (elapsedTimeMerge > elapsedTimeInsertion)
                System.out.println("Insertion sort was faster");
            else
                System.out.println("Both were as fast");
                
            System.out.println(""); 
            arraySize *= MULTIPLE;
        }
        
        /*TESTING DESCENDING INTS
          aka worst case */
        
        /*
        int k = 0;
        for(int i = arrIns.length-1; i > 0; i--){
            arrIns[k] = arrMer[k] = i; 
            k++;
        }
        
        System.out.println("\nInversion sort DESCENDING INTS: ");
            long elapsedTimeInsertion = insertion.sort(arrIns);
            System.out.println("Execution time: " + elapsedTimeInsertion/1000000 + "ms"); 
            System.out.println("Array size: " + arrIns.length); 
        
        System.out.println("Merge sort DESCENDING INTS: ");
        long elapsedTimeMerge = merge.sort(arrMer);
            System.out.println("Execution time: " + elapsedTimeMerge/1000000 + "ms");
            System.out.println("Array size: " + arrMer.length); 
        //merge.printArray(arrMer);
        }*/
        
         /*TESTING ASCENDING INTS
          aka best case */
        /*
        for(int i = 0; i < arrIns.length; i++){
            arrIns[i] = arrMer[i] = i; 
        }
        
        System.out.println("\nInversion sort DESCENDING INTS: ");
            long elapsedTimeInsertion = insertion.sort(arrIns);
            System.out.println("Execution time: " + elapsedTimeInsertion/1000000 + "ms"); 
            System.out.println("Array size: " + arrIns.length); 
        
        System.out.println("Merge sort DESCENDING INTS: ");
        long elapsedTimeMerge = merge.sort(arrMer);
            System.out.println("Execution time: " + elapsedTimeMerge/1000000 + "ms");
            System.out.println("Array size: " + arrMer.length); 
        //merge.printArray(arrMer);
        }*/
     }
}
