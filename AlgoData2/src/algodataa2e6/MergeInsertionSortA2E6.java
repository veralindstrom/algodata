/**
 * README.
 * 
 * TASK:
 * Experiment with the cut-off to insertionsort in merge. 
 * How is the execution time affected by different values for the cut-off? 
 * A suitable range for cut-off values to test with could be [0-30]. Upload code, tests and a graphs.
 * 
 * SOLUTION:
 * I implemented the merge sort algoritm but added a condition to when it should
 * use insertion sort instead. Combinimg these two algoritms makes the program even faster.
 * 
 * The cut-off value is a value to where we want to cut in our recursive calls. 
 * In this case we want every subarray smaller than size 'cutoff' to be sorted with insertion sort 
 * (since insertion sort is better for smaller arrays) and then merged and since it will already be sorted
 * the merge process will be much faster.
 * 
 * In my test and main class:
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

/**
 *
 * @author Vera
 */
public class MergeInsertionSortA2E6 {
    static int CUTOFF = 30;
    
    /**
     * Method that sorts arr[leftStart...rightEnd].
     * By recursive calls.
     * 
     * First divides the array in half, over and over as long as rightEnd > leftStart + cutoff,
     * so when size = cutoff or less then insertionsort().
     * 
     * Then merge back together using merge()
     * 
     * @param arr Array to be sorted
     * @param aux Array to be parted
     * @param leftStart First index of array
     * @param rightEnd Last index of array
     * @param cutoff Size of subarrays should be smaller or equal to cutoff
     */ 
    private void parting(int arr[], int aux[], int leftStart, int rightEnd, int cutoff) {      
        if(rightEnd <= leftStart + cutoff){
            insertion(arr, leftStart, rightEnd);
            return;
         }
          
         /*Find middle point*/
        int mid = (leftStart + rightEnd) / 2; 
        
        // Split first and second halves 
        parting(arr, aux, leftStart, mid, cutoff); 
        parting(arr, aux, mid + 1, rightEnd, cutoff); 
        
        // Merge the sorted halves 
        merge(arr, aux, leftStart, mid, rightEnd); 
    } 
    
     /**
     * Sorts a given arrays elements in ascendig order.
     * Compares element to previous element and swaps if previous 
     * element is greater than element.
     */
    private void insertion(int a[], int leftStart, int rightEnd){
       for(int i = leftStart; i < rightEnd + 1; i++){
            int key = a[i];
            int j = i -1;
            
            while(j>= 0 && a[j] > key){
                a[j+1] = a[j];
                
                j = j -1;
                a[j + 1] = key;
            }
            // System.out.println("EACH INSERT: ");
            //  printArray(arr);
        }
    }
    
    /**
     * Merges subarrays to a[]. 
     * Creates temporary array a[]
     * 
     * Copy all elements in a[] to aux[]
     * 
     * array:
     * [left start][...][...][mid][mid+1 = right start][...][right end]
     * 
     * i = left start 
     * j = right start
     * 
     * Iterate thorugh array
     * 
     * if left start > mid, then add from the right half of the array 
     * else if right start > right end, then add from the left half of the array
     * else if element in right array is smaller than element in left array, then add from right array
     * else, add from left array
     */
    private void merge(int a[], int[] aux, int leftStart, int mid, int rightEnd) 
    {   
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
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     * @param cutoff Size of subarrays should be smaller or equal to cutoff
     */
    public long sort(int[] a, int cutoff) {
        long startTime = System.nanoTime();
        int[] aux = new int[a.length];
        parting(a, aux, 0, a.length-1, cutoff);
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
              
              
            int arr[] = new int[20000]; 
            
            for(int cutoff = 0; cutoff <= CUTOFF; cutoff++){
                 
                /* DESCENDING ORDER */
                int k = 0;
                for(int i = arr.length; i > 0; i--){
                    arr[k] = i; 
                    k++;
                }
              
                System.out.println("CUTOFF: " + cutoff); 
                
                System.out.println("Given Array"); 
                //printArray(arr); 
  
                MergeInsertionSortA2E6 ob = new MergeInsertionSortA2E6(); 
                 //System.out.println("\nExecution time: " + ob.sort(arr, 0, arr.length - 1) + "ms"); 
               // long startTime = System.nanoTime();
               // ob.sort(arr, cutoff);
                //long elapsedTime = System.nanoTime() - startTime;
                System.out.println("Execution time: " + ob.sort(arr, cutoff)/1000000 + "ms");
                
                System.out.println("\nSorted array"); 
                //printArray(arr); 
             }
        }
    }
}
