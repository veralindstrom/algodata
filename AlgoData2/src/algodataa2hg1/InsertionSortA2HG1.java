/**
 * README.
 * 
 * TASK:
 * Augment the test code from assignment 1 so that the array is sorted 
 * in descending order instead of ascending order (you may add O(N) operations)
 * Clarification: You should not change the sorting method, nor should you sort 
 * the array an extra time. You may traverse the array once before sorting and 
 * once after sorting. During these traversals you may not move any elements. 
 * (Hint: you need not and should not use any extra memory)
 * 
 * 
 * SOLUTION:
 * Implementing insertionsort by comparing the second element with 
 * the previous (first) element in the array. If first element is 
 * greater than second element, swap places in array, else continue
 * the same process with the next element. To not change the InsertionSort
 * method but still change the order into descending, the method 
 * DescendingInsertionSort traverses the array once before sorting multiplying
 * all element by (-1) making them negative, so that when sorted, instead of 3 
 * being smaller then 5, since negative, 5 will be smaller than 3.
 * After sorting the negative elements with InsertionSort in ascending order,
 * The array is traversed again turning the values back to positive.
 * Resulting in a descending ordered array, using an ascending order sorting method.
 * 
 * ex. 
 * 
 * [2][1][3][5][4]  (x -1)
 * 
 * [-2][-1][-3][-5][-4]  -2 !> -1, no swap
 * [-2][-1][-3][-5][-4]  -1 > -3, swap; -2 > -3, swap
 * [-3][-2][-1][-5][-4]  -1 > -5, swap; -2 > -5, swap; -3 > -5, swap
 * [-5][-3][-2][-1][-4]  -1 > -4, swap; -2 > -4, swap; -3 > -4, swap; -5 !> -4, no swap
 * [-5][-4][-3][-2][-1]  (x -1)
 * 
 * [5][4][3][2][1]  DONE
 * 
 * In the unit test in main the user is able to decide the size of 
 * the array and value of elements through standard input by importing
 * and using Java utils Scanner class. 
 * 
 */
package algodataa2hg1;

/**
 *
 * @author Vera
 */
public class InsertionSortA2HG1 {
      /**
     * Sorts a given arrays elements in ascendig order.
     * Compares element to previous element and swaps if previous 
     * element is greater than element.
     * @param arr The array to be sorted.
     */
    private void sort(int arr[]){
        int swaps = 0;
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i -1;
            
            while(j>= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                swaps++;
                
                j = j -1;
                arr[j + 1] = key;
                //printArray(arr);
            }
        }
        System.out.println("Number of swaps: " + swaps); 
    }
    
    /**
     * Sort array in descending order.
     * By making all elements negative.
     * Then calling sort() in ascending order.
     * Then make all elements positive again.
     * @param arr Array to be sorted.
     */
    public void descendingOrder(int arr[]){
      for (int i = 0; i < arr.length; i++) 
            arr[i] = arr[i] * (-1);
      
      sort(arr); 

      for (int i = 0; i < arr.length; i++) 
            arr[i] = arr[i] * (-1);    
      
      printArray(arr);
    }
    
    /**
     * Prints elements of array.
     * @param arr Array to be printed.
     */
    public void printArray(int arr[]) 
    { 
        for (int i = 0; i < arr.length; i++) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } 
    /*
     public void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = n - 1; i >= 0; --i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } */
    
    private static class Test{
        public static void main(String[] args) {
        InsertionSortA2HG1 ob = new InsertionSortA2HG1(); 
        int[] arr = {2, 4, 1, 3, 5};
        int[] arr1 = {1, 2, 4, 3, 5, 0};
        
        System.out.println("Elements to be sorted: ");
        ob.printArray(arr);
        System.out.println("Sorting: ");
        ob.descendingOrder(arr); 
    
    }
    }
}
