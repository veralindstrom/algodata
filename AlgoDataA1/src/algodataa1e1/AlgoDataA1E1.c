#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void readAndPrintReverse_rec();
void readAndPrintReverse_iter();

struct Stack* createStack(int capacity);
int isFull(struct Stack* stack);
int isFull(struct Stack* stack);
int isEmpty(struct Stack* stack);
void push(struct Stack* stack, char c);
char pop(struct Stack* stack);

int main(int argc, char *argv[]) {
	
	printf("Reversed with recursion: ");
	readAndPrintReverse_rec();
	
	printf("\nReversed with iteration: ");
	readAndPrintReverse_iter();
	
	return 0;
}



/*
* Function that reads character from stdin until end-of-file or new-line-character
* and prints in reverse order using recursion.
*
* When stdin reached EOF or \n function returns to its previous caller 
* and executes putchar(c).
*
* putchar prints the character read from the "penultimate" (näst sista) executed
* readAndPrintReverse_rec()
* which is why when/if "\n" is read, it will not be printed
*
*/
void readAndPrintReverse_rec() {
	char c = getchar();
	if (c == '\n' || c == EOF) {
		return;
	}
	readAndPrintReverse_rec();
	putchar(c);
}

/*
* Function that reads character from stdin until end-of-file or new-line-character
* and prints in reverse order using iteration.
*
* Creates stack, fixed size 30.
*
* Puts every character read from stdin to stack until end-of-file or new-line-character. 
* Meaning "\n" will not be put on stack. 
*
* Prints every character in stack to stdout, LIFO due to pop-method.
*/
void readAndPrintReverse_iter(){
	struct Stack* stack = createStack(30);
	
	char c = getchar();
	while (c != EOF && c != '\n') {
		push(stack, c);
		c = getchar();
	}
	
	while(!isEmpty(stack))
		putchar(pop(stack)); 
}

/*
* A struct to represent a stack
*/
struct Stack {
	int top;
	int capacity;
	char* array;
};

/* 
* Function to create a stack of given  
* capacity. It initializes size of stack as 0 
* Having only access to stack through 'top'.
*/
struct Stack* createStack(int capacity) 
{ 
	struct Stack* stack = (struct Stack*) malloc(sizeof(struct Stack)); 
	stack->capacity = capacity; 
	stack->top = -1; 
	stack->array = (char*) malloc(stack->capacity * sizeof(char)); 
	return stack; 
} 

/* 
* Stack is full when top is equal to the last index 
*/
int isFull(struct Stack* stack) { 
	if(stack->top == stack->capacity - 1)
		return 1; 
	else 
		return 0;
} 
  
/*
* Stack is empty when top is equal to -1 
*/
int isEmpty(struct Stack* stack) { 
	if (stack->top == -1)
		return 1; 
	else 
		return 0;
}

/*
* Function to add an item to stack.  
* It increases top by 1 
*/
void push(struct Stack* stack, char c) 
{ 
	if (isFull(stack)) 
		return;
	else 	 
		stack->array[++stack->top] = c; 
} 

/*
* Function to remove an item from stack, LIFO  
* It decreases top by 1 
*/
char pop(struct Stack* stack) 
{ 
	if (isEmpty(stack)) 
		return 0; 
	else
		return stack->array[stack->top--]; 
} 


