/**
 * README.
 * 
 * TASK
 * Implement insertion sort.
 * 
 * SOLUTION
 * Iteratie through array and comparing one element with it's previous.
 * If the previous element is greater than the element, swap places.
 * Then check if the next previous element is greater than the element... and so on.
 * Then do this for every element in list.
 * 
 * We skip the first element since it has no previous element.
 * 
 * ex.
 * [3][2][1][4]
 * 
 * element = index = 1 = [2]
 * previous = 3
 * if ([3] > [2]) true
 * [2][3][1][4] swapped
 * no next previous
 * 
 * check next element = index + 1 = 2 = [1]
 * if [3] > [1] true
 * [2][1][3][4] swapped
 * check next previous = 2
 * if [2] > [1] true
 * [1][2][3][4] swapped
 * no next previous
 * 
 * check next element = index + 1 = 3 = [4]
 * if [3] > [4] false
 * [1][2][3][4] no swap
 * 
 * array has been sorted.
 * 
 * Then I return the time for executing the algoritm.
 */
package algodataa2e5;

/**
 *
 * @author Vera
 */
public class InsertionSortA2E5 {
     
    /**
     * Sorts a given arrays elements in ascendig order.
     * Compares element (i) to previous element (j) and swaps if previous 
     * element is greater than element.
     * @param arr The array to be sorted.
     * @return Execution time
     */
    public long sort(int arr[]){
        long startTime = System.nanoTime();
        
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i -1;
            
            while(j>= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                
                j = j -1;
                arr[j + 1] = key;
                printArray(arr);
            }
        }
        
        long elapsedTime = System.nanoTime() - startTime;
        
        return elapsedTime;
    }
    
    
    /**
     * Prints elements of array.
     * @param arr Array to be printed.
     */
    public static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } 
    
    private static class Test{
        public static void main(String[] args) {
        InsertionSortA2E5 op = new InsertionSortA2E5(); 
        //int[] arr = {2, 4, 1, 3, 5};
        //int[] arr1 = {1, 2, 4, 3, 5, 0};
        
        int size = 10;
             
        int arr[] = new int[size];
            
        /*DESCENDING ORDER: worst case*/
        for(int i = arr.length, k = 0; i > 0; i--, k++){
          arr[k] = i; 
        }
        
        System.out.println("Elements to be sorted: ");
        printArray(arr);
        System.out.println("Sorting: ");
        System.out.println("Executiontime: " + op.sort(arr) + " ns"); 
    
        System.out.println("\nSorted array"); 
        printArray(arr);
        
    }
    }
}
