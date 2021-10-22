/**
 * README.
 * 
 * TASK:
 * Compare the execution times for sorting large arrays of integers 
 * with quicksort and merge sort. When should one select quicksort over 
 * mergesort?
 * 
 * 
 * SOLUTION:
 * To compare the execution times for mergesort and quicksort,
 * quicksort was implemented. Both mergesort and quicksoart are divide 
 * and conquer sorting methods. Merge sort divides the array recursively
 * in the middle until it no longer can, when size is 1, base case, and 
 * then backtracks its recursion by merging and sorting all subarrays 
 * into their original subarray until only two subarrays are left to 
 * merge into the original full size array, where all elemnts are sorted.
 * 
 * Quicksort similarly to mergesort divides its fullsize array into
 * partions. However, quicksort, divides its partitions around a pivot. 
 * The pivot is chosen by initializing it to the first element in the 
 * array. The array is then traversed from the front (left -> right)
 * checking for any element that is larger than the pivot element, and 
 * from the back (right -> left) checking for any element that is smaller 
 * than the pivot element. If a larger element is found placed in the
 * array before a smaller element ([pivot .. larger .. smaller ..]) then 
 * these two elements are swapped ([pivot .. smaller .. larger ..]). Else, 
 * if the elements are found correctly ([pivot .. smaller .. larger ..]) 
 * in comparison to the pivot element, then the pivot element is directly
 * swapped with the smaller element ([smaller .. pivot .. larger ..]).
 * Now the array is ordered such that all elements before the pivot element
 * are smaller and all elements after are larger. The index of the pivot
 * is returned, and the quicksort splitting method continues to repeat this 
 * process for the partitions around the pivot element until the base case 
 * for the recursion of partitioning is met, where the right side is smaller 
 * or equal to the left side, in this case the array is sorted if one element
 * is left.
 */
package algodataa2hg2;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA2HG2 {
    //static int arraySize = 5; //Number of elements in the array
    //static final double MULTIPLE = 2.0; //After every iteration, the array size changes by this much
    //static final int RANGE = arraySize; //The values in the array are between 1 and RANGE
    static final int TIMES_SORTED = 5; //How many times to do the tests
    
     public static void main(String[] args) {
         
        Random rand = new Random(); 
        
        MergeSortA2HG2 merge = new MergeSortA2HG2();
        QuickSortA2HG2 quick = new QuickSortA2HG2();
        
        Scanner scan = new Scanner(System.in);
        
        for(int j = 0; j < TIMES_SORTED; j++){
            System.out.println("Size: ");
            int arraySize = scan.nextInt();
            
            int[] arrQuick = new int[arraySize];
            int[] arrMerge = new int[arraySize];
           
            
            for(int i = 0; i < arraySize; i++)
                arrMerge[i] = arrQuick[i]  = rand.nextInt(arraySize);
        
            System.out.println("Merge sort RANDOM INTS: ");
            long elapsedTimeMerge = merge.sort(arrMerge);
            System.out.println("Execution time: " + elapsedTimeMerge + "ns"); 
            System.out.println("Array size: " + arrMerge.length); 
        
            System.out.println("Quick sort RANDOM INTS: ");
            long elapsedTimeQuick = quick.sort(arrQuick);
            System.out.println("Execution time: " + elapsedTimeQuick + "ns");
            System.out.println("Array size: " + arrQuick.length); 
            
            compareTimes(elapsedTimeMerge, elapsedTimeQuick);
            
            System.out.println(""); 
            //arraySize *= MULTIPLE;
        }
        
        /*TESTING DESCENDING INTS
          aka worst case */
        /*
        
        
        for(int i = arrMerge.length-1, k = 0; i > 0; i--, k++){
            arrMerge[k] = arrQuick[k] = i; 
        }
        
       
            System.out.println("Merge sort DESCENDING INTS: ");
            long startTimeM = System.nanoTime();
            merge.sort(arrMerge);
           // long elapsedTimeMerge = merge.sort(arrIns);
           long elapsedTimeMerge = System.nanoTime() - startTimeM;
            System.out.println("Execution time: " + elapsedTimeMerge + "ns"); 
            System.out.println("Array size: " + arrMerge.length); 
        
            System.out.println("Quick sort DESCENDING INTS: ");
            long startTimeQ = System.nanoTime();
            quick.sort(arrQuick);
            long elapsedTimeQuick = System.nanoTime() - startTimeQ;
            //long elapsedTimeQuick = quick.sort(arrMer);
            System.out.println("Execution time: " + elapsedTimeQuick + "ns");
            System.out.println("Array size: " + arrQuick.length); 
            
            compareTimes(elapsedTimeMerge, elapsedTimeQuick);
        
            System.out.println(""); 
            //arraySize *= MULTIPLE;
        }*/
        
         /*TESTING ASCENDING INTS
          aka best case */
        /*
        for(int i = 0; i < arrMerge.length; i++){
            arrQuick[i] = arrMerge[i] = i; 
        }
        
            System.out.println("Merge sort ASCENDING INTS: ");
            long elapsedTimeMerge = merge.sort(arrMerge);
            System.out.println("Execution time: " + elapsedTimeMerge + "ns"); 
            System.out.println("Array size: " + arrMerge.length); 
        
            System.out.println("Quick sort ASCENDING INTS: ");
            long elapsedTimeQuick = quick.sort(arrQuick);
            System.out.println("Execution time: " + elapsedTimeQuick + "ns");
            System.out.println("Array size: " + arrQuick.length); 
            
            compareTimes(elapsedTimeMerge, elapsedTimeQuick);
                
            System.out.println(""); 
            arraySize *= MULTIPLE;
        }*/
        
     }
     
    public static void compareTimes(long time1, long time2){
        if(time1 < time2)
            System.out.println("Merge sort was faster");
        else if (time1 > time2)
            System.out.println("Qucik sort was faster");
        else
            System.out.println("Both were as fast");
    }
}
