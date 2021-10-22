/**
 * README.
 * 
 * TASK:
 * Compare the execution times of quicksort where the first element 
 * in each sub-array is selected as partitioning element to that of quicksort
 * with median-of-three partitioning.
 * 
 * SOLUTION:
 * Implementing quick sort. 
 * 
 * First select first element as a pivot. 
 * Then check if the pivot's next element in array is greater than the pivot and if the last
 * element in array is smaller than the pivot.
 * If true, swap places
 * If only one of the conditions is true, leave pointer at the element being true in its case and
 * move to next element for the one that was false.
 * Only swap when both are true.
 * 
 * Continuing this process until "left" and "right" pointer are pointing to the same element,
 * or when they've crossed.
 * 
 * When they point to the same element, or crossed, swap the "left"-element with the pivot.
 * 
 * Then sort all elements that are smaller than the pivot (on the left side/partition). 
 * If element i > j, swap.
 * 
 * When all elements on left is sorted, check right elements
 * by setting a new pivot to the last element in array, which will be the "left"-element that swapped
 * with the pivot.
 * 
 * Continue the process, now sorting the elments higher than the last pivot.
 * Continuing this until all elements are sorted.
 * 
 * ex. QuickSort
 *
 *    [3][1][7][6]-> pivot = 3 
 *                   check front: 1 < 3, yes; 7 < 3, no -> break nested loop
 *                   check back:  3 < 6, yes; 3 < 7, yes; 3 < 1, no -> break nested loop
 *      pivot        left[2] > right[1], break
 *        |          swap: 3 and 1 (pivot with right index)
 *   [1] [3] [7][6]  
 *    |         |       Left of pivot: (end = start)index -> sorted
 *    |         |       Right of pivot: (end[3] > start[2]) -> sortPartition
 *    |         |     
 *  Done       sort->   pivot = 7
 *    |         |       check front: 6 < 7, yes -> (6 = hi) -> break nested loop
 *    |         |       check back:  7 < 6, no -> break nested loop
 *    |         |       left[1] = right[1], break
 *    |   pivot |       swap: 7 and 6 (pivot with right index)
 *    |      |  |
 *  [1][3]  [6] [7] ->  Left of pivot: no elements
 *               |      Right of pivot: (end = start)index -> sorted
 *             Done
 *               |
 *     [1][3][6][7] 
 *
 *
 */
package algodataa2hg3;

/**
 *
 * @author Vera
 */
public class QuickSortA2HG3_firstElement {
     
    // quicksort the subarray from a[lo] to a[hi]
    /**
     * Split array into subarrays recursivly
     * @param a Array to be sorted/split
     * @param lo First element
     * @param hi Last element
     */
    private void sort(int[] a, int lo, int hi) { 
        if (hi <= lo) return; //if only one element left, consider it sorted
        
        int j = partition(a, lo, hi); //select new pivot 
        System.out.println("Pivot: " + a[j]);
        printArray(a);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    
    // exchange a[i] and a[j]
    private void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    /**
     * Sorting each partition.
     * @param a Array/partition
     * @param lo Last index of partition
     * @param hi First index of partition
     * @return Index of pivot value
     */
    private int partition(int[] a, int lo, int hi){
        int i = lo; //index of lowest (temp pivot?)
        int j = hi + 1; //right pointer
        int v = a[lo]; //lowest value
        
        while (true) { 
            
            // find item on lo to swap
           while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            swap(a, i, j);
        }

        // put partitioning item v at a[j]
        swap(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }
    
     /**
     * Prints array.
     * 
     * @param arr Array to be printed
     */
    public static void printArray(int arr[]) 
    { 
        for (int i = 0; i < arr.length; ++i) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    } 
    
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     * @return Execution time
     */
    public long sort(int[] a) {
        long startTime = System.nanoTime();
        sort(a, 0, a.length - 1);
        long elapsedTime = System.nanoTime() - startTime;
        return elapsedTime;
    }

     
    /**
     * A test class.
     */
    private static class Test{
          public static void main(String[] args) {
             
            //int arr[] = new int[10];
            int arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            
            /*DESCENDING ORDER*/
            /*for(int i = arr.length, k = 0; i > 0; i--, k++){
                arr[k] = i; 
            }*/
            
            System.out.println("Given Array"); 
            printArray(arr); 
  
            QuickSortA2HG3_firstElement op = new QuickSortA2HG3_firstElement();
            
            //op.sort(arr);
            System.out.println("Executiontime: " + op.sort(arr) + "ns"); 
  
            System.out.println("\nSorted array"); 
            printArray(arr); 
        }
    }
}
