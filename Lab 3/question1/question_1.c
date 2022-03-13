/*
README
 DESCRIPTION OF WHAT HAS BEEN DONE IN THIS ASSIGNMENT (also in the text document)
 To solve this assignment I made a recursive function that lets the user input chars until he/she enters the enter-key
 (assignment didn’t specify when to stop letting the user input so I thought this was a logical way to let the user stop writing).
In every recursion of the function it is checked if the input is a letter, blank space or newline(can only be a newline the last time),
if so we print out the char with “putchar” and if else we “putchar” a blank space as the assignments explains that you should.
 What the function does is to check if the input was a newline, if so we stop the recursion and if it is not a new line we simply call the function again (recursion happens).
*/


#include <stdio.h>
#include <ctype.h>   //This is for isaplha

//This function filters a text so only the letter, blankspaces and newlines are printed
//It stops when the user enter a newline since the question doesn't specify when to stop I thought this was a natural place to stop.
void recursiveWriter(void)
{
  char input = getchar();
  //If isalpha returns 0 it is not a letter
  if((isalpha(input) != 0) || (input == ' ') || (input == '\n'))
  {
    putchar(input);
  }
  else
  {
    //If not a letter or newline or blankspace then print out a blankspace as the assignment says
    putchar(' ');
  }
  if(input != EOF)
  {
    recursiveWriter();
  }
  return;
}

int main()
{
  printf("Write something: ");
  recursiveWriter();
  printf("\n");
  return 0;
}
