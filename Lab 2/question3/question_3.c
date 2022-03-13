#include <stdio.h>

//This method sorts an array so that the negative numbers come first and the positive ones come after
void negativeSorter(int* inputArray, int size)
{
  int tempInt = 0;
  //Stop at sizeof(inputArray) - 1 since the last digit will always be correctly placed
  for(int i = 0; i < size - 1; i++)
  {
    if(inputArray[i] >= 0)
    {
      for(int j = i + 1; j < size; j++)
      {
        if(inputArray[j] < 0)
        {
          tempInt = inputArray[i];
          inputArray[i] = inputArray[j];
          inputArray[j] = tempInt;
          break;
        }
      }
    }
  }
}

int main()
{
  int size = 0;
  printf("Enter size of array: ");
  scanf("%d", &size);
  int inputArray[size];
  int number = 0;
  //Below we let the user input all the numbers that are going to be in the array
  for(int i = 0; i < size; i++)
  {
    printf("Enter number %d:", i + 1);
    scanf("%d", &number);
    inputArray[i] = number;
  }
  negativeSorter(inputArray, size);
  //Below we print out the sorted array
  for(int i = 0; i < size; i++)
  {
    printf("%d,", inputArray[i]);
  }
  printf("\n");
  return 0;
}
