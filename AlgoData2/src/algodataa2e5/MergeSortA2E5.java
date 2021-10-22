/**
  * README.
  * 
  * TASK:
  * Implement merge sort.
  * 
  * SOLUTION:
  * First: create two arrays, one "working array" and one array that will be the sorted array.
  * 
  * Then, by recursive calls, I divide the array into subarrays by parting the array over and over until leftStart >= rightEnd,
  * which would be when the size of each subarray is 1. Then i return to the caller.
  * 
  * When calling a method recursivly everything after the recursive call is being executed when returned for every caller.
  * Meaning, for every parting except the last one, there is a merge.
  * (numberOfMerge()Calls = numberOfParting()Calls - 1)
  * 
  * In the merge-method, the working array is needed to save all values that shall be sorted.
  * I therefore need to copy all elements of the array to the working array.
  * 
  * Then I check where in the array to put what element from the working array.
  * 
  * if there is no more elements in the left subarray, then add all elements left from the right subarray 
  * else if there is no more elements in the right subarray, then add all elements left from the left subarray
  * 
  * else if first element in right subarray is smaller than first element in left subarray, then add from right subarray
  * else, add from left subarray
  * 
  * Then merge back together using merge()
  * ex.
  * 1.    [5][2][3][1] (parting)
  *         ↓      ↓
  * 2.   [5][2]  [3][1] (parting)
  *     ↓    ↓    ↓   ↓
  * 3. [5]  [2]  [3]  [1] (parting)
  *        ↓        ↓
  * 4.   [2][5]  [1][3] (merge) is 2 smaller than 5? yes, then add from right subarray, then add rest from left since there is no more elements right subarray. 
  *         ↓      ↓            is 1 smaller than 3? yes, then add from right subarray, then add rest from left since there is no more elements right subarray. 
  * 5.    [1][2][3][5]  (merge) is 1 smaller than 2? yes, then add from right subarray. 
  *                             is 3 smaller than 2? no, then add from left subarray.
  *                             is 3 smaller than 5? yes, then add from right subarray, then add rest from left since there is no more elements right subarray. 
  * 
  * Then I check the execution time of this process by counting the nanoseconds from that moment
  * I called the function from Main to when it returned.
  */
package algodataa2e5;

/**
 *
 * @author Vera
 */
public class MergeSortA2E5 {
    
    /**
     * Method that sorts arr[leftStart...rightEnd].
     * By recursive calls.
     * 
     * First divides the array in half, over and over until leftStart >= rightEnd,
     * which would be when the size of each subarray is 1.
     * 
     * Then merge back together using merge()
     * ex.
     * 1.    [5][2][3][1]
     *         ↓      ↓
     * 2.   [5][2]  [3][1] (sort)
     *     ↓    ↓    ↓   ↓
     * 3. [5]  [2]  [3]  [1] (sort)
     *        ↓        ↓
     * 4.   [2][5]  [1][3] (merge)
     *         ↓      ↓
     * 5.    [1][2][3][5]  (merge)
     * 
     * 
     * @param arr Array to be sorted
     * @param leftStart First index of array
     * @param rightEnd Last index of array
     * @param aux 
     */ 
    private void parting(int[] a, int[] aux, int leftStart, int rightEnd) {
        if (rightEnd <= leftStart) return; //Consider size = 1 as sorted
        int mid = leftStart + (rightEnd - leftStart) / 2;
        parting(a, aux, leftStart, mid);
        parting(a, aux, mid + 1, rightEnd);
        merge(a, aux, leftStart, mid, rightEnd);
    }

    
    /**
     * Merges subarrays to arr[]. 
     * Creates temporary array a[]
     * 
     * Copy all elements in arr[] to a[]
     * 
     * array:
     * [left start][...][...][mid][mid+1 = right start][...][right end]
     * 
     * i = left start 
     * j = right start
     * 
     * Iterate thorugh array
     * 
     * if (leftstart) i > mid, then add all thats left from the right half of the array 
     * else if (rightstart) j > right end, then add all thats left from the left half of the array
     * 
     * else if first element in right subarray is smaller than first element in left subarray, then add from right subarray
     * else, add from left subarray
     */
    private void merge(int[] a, int[] aux, int leftStart, int mid, int rightEnd) {
        // copy to aux[] 
        for (int k = leftStart; k <= rightEnd; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = leftStart, j = mid+1;
        for (int k = leftStart; k <= rightEnd; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > rightEnd)         a[k] = aux[i++];
            else if ((aux[j] < aux[i]))    a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    
    /**
     * Rearranges the array in ascending order, calling parting.
     * @param a the array to be sorted
     * @return Execution time
     */
    public long sort(int[] a) {
        long startTime = System.nanoTime();
        int[] aux = new int[a.length];
        parting(a, aux, 0, a.length-1);
        
        long elapsedTime = System.nanoTime() - startTime;
        return elapsedTime;
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
     * A test class.
     */
    private static class Test{
          public static void main(String[] args) {
            int size = 10;
             
            //int arr[] = new int[size];
            int arr[] = {5,2,3,1};
            
            /*DESCENDING ORDER*/
            /*for(int i = arr.length, k = 0; i > 0; i--, k++){
                arr[k] = i; 
            }*/
            
            System.out.println("Given Array"); 
            printArray(arr); 
  
            MergeSortA2E5 op = new MergeSortA2E5();
            
            System.out.println("Executiontime: " + op.sort(arr) + "ns"); 
  
            System.out.println("\nSorted array"); 
            printArray(arr); 
        }
    }
}
