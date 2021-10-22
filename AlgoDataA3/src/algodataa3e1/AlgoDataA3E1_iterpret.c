#include<stdio.h>
#include<ctype.h> //for isalhpa()
/**
 * README
 *
 * TASK:
 * 1. Write a simple filter to clean a text, i.e. to remove all characters that
 * are not alphabetic, blank or newline - replacing every such character by a blank
 * to keep the number of characters constant to the original text. Hint: this is
 * easy to do in C using the "isalpha()" function (to find out more about it on a
 * unix/linux system type: man isalpha as a command to the shell).
 *
 * SOLUTION:
 * 1. The program starts by opening the path-way to the input file and the output
 * file. It checks that the files are correctly found before proceeding to any other
 * operations.
 * To clean the text from any characters non-alphabetic the isalpha function is
 * imported and used. The function returns 0 for all non-alphabetic characters.
 * The input file is iterated through till its end and each time a character
 * returns 0 via the isalpha function that character is substituted with a blank
 * space in the output file where all characters are written to, keeping the
 * number of characters from input to output constant.
 *
 * ex.
 * (Replaced blanks ' ' are now presented by _ to facilitate the illustration)
 *
 * Input =   Hello! It's a beautiful day!  -> 28 char
 * Output =  Hello_ It_s a beautiful day_  -> 28 char
 *
 */

#include <stdio.h>

int main(int argc, char *argv[]) {
	FILE *fileToRead;
	FILE *fileToWrite;
	char ch;
	int counter = 0;
	
	fileToRead = fopen("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e1/TheText.txt","r");
	fileToWrite = fopen("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e1/output_interpret.txt","w");
	
	if (fileToRead == NULL) {
		printf("File is not available \n");
	}
	
	else if(fileToWrite == NULL)
	{
		printf("Error");   
		return (0);             
	}
			
 	int countChange = 0;
		while ((ch = fgetc(fileToRead)) != EOF){
			if(isalpha(ch) == 0 && ch != '\n' && ch != ' '){
				ch = ' ';
				countChange++;
			}
			
			if(ch != '\n'){
				counter++;
			}
			
			printf("%c", ch);
			fprintf(fileToWrite,"%c",ch);
			
		}
		
	fclose(fileToRead);
	fclose(fileToWrite);

	printf("\nnon alphabetic: %d" , countChange);
	printf("\nCharachters: %d", counter);
	return 0;
}