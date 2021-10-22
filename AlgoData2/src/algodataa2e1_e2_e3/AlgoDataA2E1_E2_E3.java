/**
 * README.
 * 
 * TASK:
 * 1. Implement insertionsort. Augment the sorting process so that all the content 
 * of the array that is being sorted is printed after each inner loop iteration. 
 * Write a unit test in main() which allows the user to define the size of the 
 * input (N) and then input (N) integers from stdin which is to be sorted.
 * 
 * 2. Augment the above implementation so that it prints the number of swaps performed when sorting the array.
 * 
 * 3. Add a method which counts the number of inversions in the input array and prints a list 
 * of all inversions on the format [i,a[i]], [j, a[j]] where i and j are indices 
 * and a[i], a[j] are the values of the elements. 
 * Call the method from main() before the array is sorted. 
 * Calculates the time complexity for the algorithm.
 * 
 * SOLUTION:
 * 1. Implementing insertionsort by first comparing the second element in array,
 * to the previous (first) element in array. If first element is greater than second element,
 * swap places in array. Then i continue this process with the next elements. Print after each swap.
 * ex.
 * [2][1][3][5][4] 2 > 1, swap
 * [1][2][3][5][4] 2 !> 3, no swap (shall not be printed)
 * [1][2][3][5][4] 3 !> 5, no swap (shall not be printed)
 * [1][2][3][5][4] 5 > 4, swap
 * [1][2][3][4][5] Sorted array
 * 
 * In the unit test in main, user is able to define size of array and value of elements thorugh
 * standard input by importing and using Java utils Scanner class.
 * 
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

import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA2E1_E2_E3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        InsertionSortA2E1_E2_E3 iSort = new InsertionSortA2E1_E2_E3(); 
        
        System.out.println("Decide size of array: ");
        int[] arr = new int[scan.nextInt()];
        
        System.out.println("Type some inputs for array (int): ");
        for(int i = 0; i < arr.length; i++)
            arr[i] = scan.nextInt();
        
        System.out.println("Elements to be sorted: ");
        iSort.printArray(arr);
        
        System.out.println("Number of swaps: " + iSort.sort(arr));
    
    }
    
}
