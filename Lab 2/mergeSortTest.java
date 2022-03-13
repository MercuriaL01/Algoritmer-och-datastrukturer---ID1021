import java.util.Scanner;

public class mergeSortTest
{
	public static void main(String[] args)
	{
		int[] inputArray = {10, 8, 6, 5, 5, 4, 3, 7, 5, 1};
		int[] returnArray = mergeSort(inputArray);
		printIntArray(returnArray);
	}

	//This method breaks down the array into smaller parts and calls merge to sort these parts. This creates a sorted array using the mergesort algoritm
	public static int[] mergeSort(int[] inputArray)
	{
		int[] firstHalf;
		//In this case there is something to sort since there is only one element in the array
		if(inputArray.length == 1)
		{
			return inputArray;
		}
		if(inputArray.length % 2 == 0)
		{
			firstHalf = new int[inputArray.length / 2];
		}
		//This else happens if ther is an odd number of elements, then the first array can hold the middle element of the inputArray
		else
		{
			firstHalf = new int[(inputArray.length / 2) + 1];
		}
		int[] secondHalf = new int[inputArray.length / 2];
		for(int i = 0; i < inputArray.length; i++)
		{
			if(i < firstHalf.length)
			{
				firstHalf[i] = inputArray[i];
			}
			else
			{
				secondHalf[i - firstHalf.length] = inputArray[i];
			}
		}
		firstHalf = mergeSort(firstHalf);
		secondHalf = mergeSort(secondHalf);

		return merge(firstHalf, secondHalf);
	}

	public static int[] merge(int[] firstHalf, int[] secondHalf)
	{
		int[] returnArray = new int[firstHalf.length + secondHalf.length];
		int i = 0;
		int a = 0;
		int b = 0;
		//In this while we put the numbers form firstHalf and secondHalf in ascending order into returnArray
		while(a < firstHalf.length && b < secondHalf.length)
		{
			if(firstHalf[a] > secondHalf[b])
			{
				returnArray[i++] = secondHalf[b++];
			}
			else
			{
				returnArray[i++] = firstHalf[a++];
			}
		}
		//Now we have gone through all the elments in either the first or second half array.
		//So the next step is simply seeing what array still has elements that we haven't added to the returnArray and add those elements to the returnArray
		while(a < firstHalf.length)
		{
			returnArray[i++] = firstHalf[a++];
		}
		while(b < secondHalf.length)
		{
			returnArray[i++] = secondHalf[b++];
		}
		return returnArray;
	}

	//This method prints out the contents of an int array and adds "," between the elements, besides the last elements of course
	public static void printIntArray(int[] inputArray)
	{
		int counter = 0;
		for(int i : inputArray)
		{
			if(++counter == inputArray.length)
			{
				System.out.print(i);
			}
			else
			{
				System.out.print(i + ", ");
			}
		}
		//New line in the end so we start on a new line next time we print out something
		System.out.println();
	}
}