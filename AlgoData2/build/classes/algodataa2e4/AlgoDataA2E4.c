/**
 * README
 *
 * TASK: 
 * Implement a function in C which takes an array of integers (both positive and negative) and orders the elements 
 * in the array so that all negative elements come before the positive. You are not allowed to sort the array only 
 * collect all negative values first. The algorithm should only use O(1) extra memory.
 * 
 * SOLUTION:
 * Need to find first positive element in arrray and save its index in a 'indexOfFirstPosElement'-variable 
 *  (using 0(1) extra memory). We need to save the first one because it is before this index we want all our
 *  negative values. They have to "come before the positive". If we were to save the last one, all negatives
 *  would come after...
 *
 * I do this by first assuming the index of the first positive element is at the first index in the array,
 *  which is 0. 
 * Then I traverse thourgh array to check for negative elements, 
 *  if I find one I save its value to the 'key'-variable.
 * Then I swap the places of the values of 'key'-index and 'indexOfFirstPosElement' and then
 *  add 1 to the 'indexOfFirstPosElement' (since 'firstPosElement' has been swapped and is now 
 *  in the next place in array)
 * 
 * If the first element in array is not a positive number, the swapping will be executed.
 * The first element, at index 0, will swap with the first positive element, also at index 0,
 *  meaning it won't change place but the 'indexOfFirstPosElement' will be added with 1,
 *  meaning the next element in list will be the first posititve element in array, 
 *  and if its not a positive element... >>> this process continues
 * 
 * If an positive element is found, the 'indexOfFirstPosElement' wont be updated and
 *  that index will be used for swapping when the next negative value is found.
 *
 */


#include <stdio.h>

void orderArray(int arr[], size_t size);
void printArray(int arr[], size_t size);


int main(int argc, char *argv[]) {
	//int arr[] = {-2, -7, 3, -6, -1, 4};
	int size;
	int value;
	
	printf("Enter size of array: ");
		scanf("%d", &size);
		
	int arr[size];
	
	printf("Enter values: \n");
	for(int i = 0; i< size; i++){
		scanf("%d", &value);
		arr[i] = value;
	}
	
	orderArray(arr, size);
}

/**
* Orders elements in array so that all negative elements come beofre the positive.
* @param arr Array to be ordered
* @param size Number of elements in array
*/
void orderArray(int arr[], size_t size){	
	int indexOfFirstPosElement = 0;
	
	for(int i = 0; i < size; i++){
		if (arr[i] < 0){
			int key = arr[i];
			
			arr[i] = arr[indexOfFirstPosElement];
			arr[indexOfFirstPosElement] = key;
			
			indexOfFirstPosElement++;
			//printArray(arr, size);
		}
	}
	printArray(arr, size);
}

/**
* Prints array.
* @param arr Array to be printed
* @size Number of elements in array
*/
void printArray(int arr[], size_t size){
	for(int i = 0; i < size; i++){
		printf("[%d] ", arr[i]);
	}
	printf("\n"); 
	
}