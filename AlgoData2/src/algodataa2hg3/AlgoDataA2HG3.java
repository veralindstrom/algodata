/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa2hg3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vera
 */
public class AlgoDataA2HG3 {
        static final int TIMES_SORTED = 5; //How many times to do the tests
        
        public static void main(String[] args) {
        Random rand = new Random(); 
        
        QuickSortA2HG3_firstElement first = new QuickSortA2HG3_firstElement();
        QuickSortA2HG3_median3 median3 = new QuickSortA2HG3_median3();
        
        Scanner scan = new Scanner(System.in);
        
        for(int j = 0; j < TIMES_SORTED; j++){
            System.out.println("Size: ");
            int arraySize = scan.nextInt();
            
            int[] arrFirst = new int[arraySize];
            int[] arrMedian = new int[arraySize];
           
            
            for(int i = 0; i < arraySize; i++)
                arrFirst[i] = arrMedian[i]  = rand.nextInt(arraySize);
        
            System.out.println("Quick sort first element partitioning RANDOM INTS: ");
            long startTimeF = System.nanoTime();
            first.sort(arrFirst);
           // long elapsedTimeMerge = merge.sort(arrIns);
           long elapsedTimeFirst = System.nanoTime() - startTimeF;
            System.out.println("Execution time: " + elapsedTimeFirst + "ns"); 
            System.out.println("Array size: " + arrFirst.length); 
        
            System.out.println("Quick sort median-of-three partitioning RANDOM INTS: ");
            long startTimeM = System.nanoTime();
            median3.sort(arrMedian);
            long elapsedTimeMedian = System.nanoTime() - startTimeM;
            //long elapsedTimeQuick = quick.sort(arrMer);
            System.out.println("Execution time: " + elapsedTimeMedian + "ns");
            System.out.println("Array size: " + arrMedian.length); 
            
            if(elapsedTimeFirst < elapsedTimeMedian)
                System.out.println("Quick sort first element partitioningwas faster");
            else if (elapsedTimeFirst > elapsedTimeMedian)
                System.out.println("Quick sort median-of-three partitioning was faster");
            else
                System.out.println("Both were as fast");
                
            System.out.println(""); 
        }
    }
}
