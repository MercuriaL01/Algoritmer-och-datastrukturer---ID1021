#include<stdio.h>

void recursiveReverseChars(void)
{
  char input;
  if((input = getchar()) != '\n')
  {
    recursiveReverseChars();
  }
  putchar(input);
  return;
}

void iterativeReverseChars()
{
  char input[5];
  char inputChar;
  int i = 0;
  int j;
  while((inputChar = getchar()) != '\n'
  {
    input[i] = inputChar;
    i++;
  }
  printf("\n");
  for(j = i - 1; j >= 0; j--)
  {
    putchar(input[j]);
  }
}

int main()
{
  recursiveReverseChars();
  printf("\n");
  printf("\n");
  printf("Now the recursive function starts");
  printf("\n");
  iterativeReverseChars();
  putchar('\n');
	return 0;
}
