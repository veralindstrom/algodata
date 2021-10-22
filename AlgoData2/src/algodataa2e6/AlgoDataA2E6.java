/**
 * README.
 * TASK:
 * Experiment with the cut-off to insertionsort in merge. 
 * How is the execution time affected by different values for the cut-off? 
 * A suitable range for cut-off values to test with could be [0-30]. Upload code, tests and a graphs.
 * 
 * SOLUTION:
 * I implemented the merge sort algoritm adding insertion sort as explained in the MergeInsertionSort-class's README-file
 * 
 * I chose the recommended range for cut-off [0-30].
 * I initialize a global static variable CUTOFF that holds the maximum cut-off value. In this case, 30.
 * 
 * Then I loop and test every cut-off value from 0-30 and printing the execution time each one.
 * Cut-off size 0 should be taking the longest time for a small array since only merge sort is used.
 * For larger arrays, "larger" cut-off sizes should improve the execution time.
 * But the execution time doesn't have to be improved just because the cut-off size increases.
 * There is a limit to where the subarrays of size 'cutoff' becomes a large array itself, where insertion sort is worthless.
 * 
 * Therefore the best cut-off size seems to be somewhere around 10.
 * 
 * If the array to be sorted is in descending order, then the execution time should grow for every increased cut-off size
 */
package algodataa2e6;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA2E6 {
    static final int CUTOFF = 30;
    
    public static void main(String[] args) {
        Random rand = new Random(); 
        Scanner scan = new Scanner(System.in);
        MergeInsertionSortA2E6 mergeInsertion = new MergeInsertionSortA2E6();
        
        System.out.println("Size of array: ");
        int arraySize = scan.nextInt();
        int[] arr = new int[arraySize];
        int[] a = new int[arraySize];
            
        /*RANDOM INTEGERS*/
        
        for(int i = 0; i < arraySize; i++){
            arr[i] = rand.nextInt(arraySize);
        }
        
        /*ASCENDING INTS*/
        /*
        for(int i = 0; i < arr.length; i++){
            arr[i] = i; 
        }*/
        /*DESCENDING INTS*/
        /*
        int k = 0;
        for(int i = arr.length; i > 0; i--){
            arr[k] = i; 
            k++;
        }
        */
        
        
        for(int cutoff = 0; cutoff <= CUTOFF; cutoff++){
            
            for(int i = 0; i < arraySize; i++){
                a[i] = arr[i];
            }
            
            System.out.println("CUTOFF: " + cutoff);     
                //System.out.println("MergeInsertion sort DESC INTS: ");
                //merge.printArray(arrMer);
                
                System.out.println("Execution time: " + mergeInsertion.sort(a,cutoff)/1000000 + "ms");
                
                System.out.println("Array size: " + a.length); 
                //merge.printArray(arrMer);
                System.out.println(""); 
            }
    }
}