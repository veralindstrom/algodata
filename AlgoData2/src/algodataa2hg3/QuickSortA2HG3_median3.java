/**
 * README.
 * 
 * TASK:
 * Compare the execution times of quicksort where the first element 
 * in each sub-array is selected as partitioning element to that of quicksort
 * with median-of-three partitioning.
 * 
 * SOLUTION:
 * Implementing quick sort but with the pivot being the median of
 * first, middle and last element instead of first element.
 * 
 * If the pivot is the first element, it goes as assignment HG2.
 * If the pivot is the middle element, we swap the middle element(pivot) with the first element.
 * If the pivot is the last elemenet, we swap last element (pivot) with the first element.
 * 
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
 * 
 * ex. QuickSort median-of-three
 * 
 *    [3][1][7][6]  median of (3, 7, 6) = (3, 6, 7) -> pivot = 6
 *                  swap (median (last) with first)
 *   pivot   
 *     |             
 *    [6][1][7][3]  pivot = 6 
 *                  check front: 1 < 6, yes; 7 < 6, no -> break nested loop
 *   pivot          check back:  6 < 3, no -> break nested loop
 *     |            swap: 7 and 3 (left with right index)
 *    [6][1][3][7] -> continue while(true):
 *                    check front: 1 < 6, yes; 3 < 6, yes; 7 < 6, no -> break nested loop
 *                    check back:  6 < 7, yes; 6 < 3, no -> break nested loop
 *         pivot      left[3] > right[2], break
 *           |        swap: 6 and 3 (pivot with right index)
 *   [3][1] [6] [7] 
 *     |         |    Left of pivot: (end[1] > start[0])index -> sortPartition
 *     |         |    Right of pivot: (end = start) -> sorted
 *     |         |
 *   sort->      |    median of (3, 1) = (1, 1, 3) -> less method will return 1 (since 2 of them)
 *     |        Done                               -> pivot = 1
 *     |         |    swap (median (last/middle) with first)
 *pivot|         |
 *  |  |         |
 * [1][3]        |    pivot = 1
 *     |         |    check front: 3 < 1, no -> break nested loop 
 *     |         |    check back:  1 < 3, yes, 1 < 1  no + (1 = lo) -> break nested loop
 *     |         |    left[1] > right[0], break
 *     |         |    swap: 1 and 1 (pivot with right(=pivot) index)
 *pivot|         |
 *  |  |         |
 * [1] [3]    [6][7] ->  Left of pivot: no elements
 *      |                Right of pivot: (end = start)index -> sorted
 *    Done
 *      |
 *  [1][3][6][7]
 */
package algodataa2hg3;

/**
 *
 * @author Vera
 */
public class QuickSortA2HG3_median3 {
     
    // quicksort the subarray from a[lo] to a[hi]
    /**
     * Splits array into subarrays recursively.
     * 
     * 
     * Middle element is calculated by taking the first element in array
     * and adding to the last index in array then divide by 2.
     * lo + n/2, n = hi - lo + 1
     * 
     * @param a Array to be sorted.
     * @param lo First element in array
     * @param hi Last element in array
     */
    private void sort(int[] a, int lo, int hi) { 
        if (hi <= lo) return; //if only one element left, consider it sorted
        
        int n = hi - lo + 1; //last index in array
        int m = median3(a, lo, lo + n/2, hi);
        System.out.println("Median3:" + a[m]);
        
        //put median-of-three as first element
        swap(a, m, lo);
        //printArray(a);
        
        int j = partition(a, lo, hi);
        //System.out.println("Pivot: " + a[j]);
        //printArray(a);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    
    /**
     * Find median value of first, middle and last element.
     * 
     * if(first element is less than middle element)
     *      if(middle element is less than last element) return index of middle (j) 
     *      else if (first element is less than last element) return index of last (k)
     *      else return index of first (i)
     * else
     *      if(last element is less than middle element) return index of middle (j)
     *      else if(last element is less than first element) return index of last (k)
     *      else return index of first (i)
     * 
     * @param a Array to be sorted
     * @param i Index of first element in array
     * @param j Index of middle element in array
     * @param k Index of last element in array
     * @return The index of the median element among a[i], a[j], and a[k]
     * 
     */
    private static int median3(int[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }
    
    // exchange a[i] and a[j]
    private void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    /**
     * Sort partition.
     * @param a Array/partition
     * @param lo Last index of partition
     * @param hi First index of partition
     * @return Index of (next) pivot value
     */
    private int partition(int[] a, int lo, int hi){
        int i = lo; //index of lowest
        int j = hi + 1; //right pointer
        int v = a[lo]; //lowest value
        
        System.out.println("i: " + i);
        System.out.println("j: " + j);
        System.out.println("v: " + v);
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
            
            //System.out.println("swap i j: " + i + j);
            swap(a, i, j);
            //printArray(a);
        }

        // put partitioning item v at a[j]
        //System.out.println("swap pivot j " + v + j);
        swap(a, lo, j);
        //printArray(a);
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
            int arr[] = {3, 1, 7, 6};
            
            /*DESCENDING ORDER*/
            /*for(int i = arr.length, k = 0; i > 0; i--, k++){
                arr[k] = i; 
            }*/
            
            System.out.println("Given Array"); 
            printArray(arr); 
  
            QuickSortA2HG3_median3 op = new QuickSortA2HG3_median3();
            
            //op.sort(arr);
            System.out.println("Executiontime: " + op.sort(arr) + "ns"); 
  
            System.out.println("\nSorted array"); 
            printArray(arr); 
        }
    }
}
