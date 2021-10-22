/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa2hg1;

import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA2HG1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        InsertionSortA2HG1 iSort = new InsertionSortA2HG1(); 
        
        System.out.println("Decide size of array: ");
        int[] arr = new int[scan.nextInt()];
        
        System.out.println("Type some inputs for array (int): ");
        for(int i = 0; i < arr.length; i++)
            arr[i] = scan.nextInt();
        
        System.out.println("Elements to be sorted in ascending order: ");
        iSort.printArray(arr);
        
        iSort.descendingOrder(arr); 
    
    }
}
