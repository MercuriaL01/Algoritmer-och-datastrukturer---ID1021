import java.util.Scanner;

/*
TIME COMPLEXITY OF QUESTION 2
The time complexity of question 2 is N * (N/2) since the outer loop loops N - 1 times and the inner one N/2 times. Where N is the length of the array
*/

public class question_1
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("How many numbers do you want in the array?");
		int length = scan.nextInt();
		//Scan nextLine so that we remove the enter-key press
		scan.nextLine();
		int[] inputArray = new int[length];
		for(int i = 0; i < length; i++)
		{
			System.out.print("Enter number " + (i + 1) + ": ");
			inputArray[i] = scan.nextInt();
			scan.nextLine();
		}
		countInversions(inputArray);
		int total= insertionSort(inputArray);
		System.out.println();
		System.out.println("The sorted array:");
		printIntArray(inputArray);
		System.out.println("Total number of swaps performed when sorting the array: " + total);
		System.out.println();
	}

	//This method sorts an int array (the method parameter), so it is sorted in ascending order
	//This methods returns an int since it returns the total number of swaps performed when sorting the array
	public static int insertionSort(int[] inputArray)
	{
		int tempInt = 0;
		int x = 0;
		int total = 0;
		System.out.print("Start Array: ");
		printIntArray(inputArray);
		//Starts at 1 since the first element is of course bigger than nothing. Go through all elements minus one since we start at 1 and not 0.
		for(int i = 1; i <= inputArray.length - 1; i++)
		{
			//Check if the number to the left of the current number we check is smaller, if so then switch places on them.
			//Do this until the number to the left is smaller or we reach that the number to the left is null
			while(i - 1 - x >= 0 && (inputArray[i - x] < inputArray[i - 1 - x]))
			{
				tempInt = inputArray[i - x];
				inputArray[i - x] = inputArray[i - 1 - x];
				inputArray[i - 1 - x] = tempInt;
				x++;
				total++;
				printIntArray(inputArray);
			}
			if(x != 0)
			{
				System.out.println("Number of swamps performed for this element: " + x);
			}
			x = 0;
		}
		return total;
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

	//Method that counts the number of pairs of keys that are out of order in an array
	public static void countInversions(int[] inputArray)
	{
		int counter = 0;
		String inversions = "";
		//Starts at 0 since we look from left to right and < length minus 1 so we dont look at the last element
		for(int i = 0; i < inputArray.length - 1; i++)
		{
			//Start at i + 1 since then we start at one to the right to i.
			//End at < inputArray.length since we want to go through every element besides the ones before and including the element i.
			//i + 1 since i starts at 0 so for example the first time we want -1 since we dont want to check one element (the frist one which is i)
			for(int j = i + 1; j < inputArray.length; j++)
			{
				if(inputArray[i] > inputArray[j])
				{
					inversions += "[" + i + ", " + inputArray[i] + "], [" + j + ", " + inputArray[j] + "]\n";
					counter++;
				}
			}
		}
		System.out.print("Start Array: ");
		printIntArray(inputArray);
		System.out.println();
		System.out.println("All inversions below");
		System.out.println(inversions);
		System.out.println("Number of inversions: " + counter);
		System.out.println();
	}
}