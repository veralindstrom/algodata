/**
 * README.
 * 
 * TASK:
 * 2. Augment the above implementation so that it prints the number of swaps performed when sorting the array.
 * 
 * 3. Add a method which counts the number of inversions in the input array and prints a list 
 * of all inversions on the format [i,a[i]], [j, a[j]] where i and j are indices 
 * and a[i], a[j] are the values of the elements. 
 * Call the method from main() before the array is sorted. 
 * Calculates the time complexity for the algorithm.
 * 
 * SOLUTION:
 * 2. To print the number of swaps performed when sorting the array I add a swap-counter.
 * A variable that increases with 1 for each inner loop iteration. This holds the number of swaps 
 * and is printed at the end of method.
 * 
 * 3. Compares element to its previous element (as in exercise 1) but doesn't swap, only counts the inversions.
 * An inversion is when two elements are in disorder. Each time the method finds an inversion, 
 * an inversion-counter increases with 1, and the two elements are printed next to its indices. 
 * This continues til end of array.
 * The inversion-counter is returned.
 */

package algodataa2e1_e2_e3;

/**
 * Insertion sort.
 * 
 * @author Vera
 */
public class InsertionSortA2E1_E2_E3 {
    
    /**
     * Sorts a given arrays elements in ascendig order.
     * Compares element to previous element and swaps if previous 
     * element is greater than element.
     * @param arr The array to be sorted.
     * @return Number of swaps
     */
    public int sort(int arr[]){
        int swaps = 0;
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i -1;
            
            while(j>= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                swaps++;
                
                j = j -1;
                arr[j + 1] = key;
                printArray(arr);
            }
        }
        return swaps; 
    }
    
    /**
     * Prints a list of all inversions and returns the number of inversions.
     * Prints on the format [i,a[i]], [j, a[j]]
     * where i and j are indexes and a[i], a[j] are the values of the elements.
     * 
     * Traverses through array, checks if first index (i) is greater than
     * next (j)... if not, j++ until end of array. Then check for next index (i++)
     * 
     * @param arr Array where its elements inversions is to be printed/counted
     * @return Returns number of inversions
     */
    public int inversionCount(int arr[]) 
    { 
        int invCount = 0; 
        for (int i = 0; i < arr.length-1; i++) {
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    System.out.println("[" + i + ", [" + arr[i] + "]]" + 
                                       " <=> " +
                                       "[" + j + ", [" + arr[j] + "]]"); 
                    invCount++; 
                }
            }
        }
        return invCount; 
    }
    
    
    /**
     * Prints elements of array.
     * @param arr Array to be printed.
     */
    public void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } 
    
    private static class Test{
        public static void main(String[] args) {
        InsertionSortA2E1_E2_E3 ob = new InsertionSortA2E1_E2_E3(); 
        int[] arr = {1,2,3,5,4,0};
        
        System.out.println("Inversions: ");
        System.out.println("Inversion count: " + ob.inversionCount(arr)); 
        
        System.out.println("Elements to be sorted: ");
        ob.printArray(arr);
        System.out.println("Sorting: ");
        System.out.println("Number of swaps: " + ob.sort(arr));
    
    }
    }
}
