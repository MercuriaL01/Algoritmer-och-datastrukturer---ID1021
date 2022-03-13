import java.io.File;
import java.util.Scanner;

public class question_5
{
	static private int cutoff = 0;
	public static void main(String[] args) throws Exception    //Has to throw exception for "FileNotFoundException"
	{
		while(true)
		{
			Scanner scan = new Scanner(System.in);
			Stopwatch_question5 stopwatch = new Stopwatch_question5();
			System.out.println("Enter 1 to sort 1,000 numbers");
			System.out.println("Enter 2 to sort 10,000 numbers");
			System.out.println("Enter 3 to sort 100,000 numbers");
			System.out.println("Enter 4 to sort 1,000,000 numbers");
		   	int choice = scan.nextInt();
		   	scan.nextLine();  	//This is needed to remove the enter-key that was input in the end.
		   	System.out.print("Enter desired cutoff value: ");
		   	cutoff = scan.nextInt();
		   	scan.nextLine();
		   	int[] inputArray;
		   	switch(choice)
		    {
				case 1:
				inputArray = getArray("random1000.txt", 1000);
				stopwatch.reset();
				System.out.println("Mergesort took: " + stopwatch.elapsedTime());
				stopwatch.reset();
				 break;
				case 2:
				inputArray = getArray("random10000.txt", 10000);
				stopwatch.reset();
				mergeSort(inputArray);
				System.out.println("Mergesort took: " + stopwatch.elapsedTime());
				stopwatch.reset();
				 break;
				case 3:
				inputArray = getArray("random100000.txt", 100000);
				stopwatch.reset();
				mergeSort(inputArray);
				System.out.println("Mergesort took: " + stopwatch.elapsedTime());
				stopwatch.reset();
				 break;
				case 4:
				inputArray = getArray("random1000000.txt", 1000000);
				stopwatch.reset();
				mergeSort(inputArray);
				System.out.println("Mergesort took: " + stopwatch.elapsedTime());
				stopwatch.reset();
				 break;
				default:
				 break;
			}
			System.out.println();
		}
	}

	public static int[] getArray(String fileName, int fileLength) throws Exception    //Has to throw exception for "FileNotFoundException"
	{
		File file = new File(fileName);
		Scanner fileScan = new Scanner(file);
		int[] inputArray = new int[fileLength];
		int i = 0;
		while(fileScan.hasNextLine())
		{
			inputArray[i++] = fileScan.nextInt();
			fileScan.nextLine(); //This is needed to remove the "enter-key", which in this case is a new line
		}
		return inputArray;
	}

	//This method sorts an int array (the method parameter), so it is sorted in ascending order
	public static int[] insertionSort(int[] inputArray)
	{
		int tempInt = 0;
		int x = 0;
		//Starts at 1 since the first element is of course bigger than nothing. Go through all elements minus one since we start at 1 and not 0.
		for(int i = 1; i <= inputArray.length - 1; i++)
		{
			//Check if the number to the left of the current number we check is smaller, if so then switch places on them.
			//Do this untilthe number to the left isn't smaller or we reach that the number to the left is null
			while(i - 1 - x >= 0 && (inputArray[i - x] < inputArray[i - 1 - x]))
			{
				tempInt = inputArray[i - x];
				inputArray[i - x] = inputArray[i - 1 - x];
				inputArray[i - 1 - x] = tempInt;
				x++;
			}
			x = 0;
		}
		return inputArray;
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
		//Here we check if the length of the array is our cutoff value, in this case we simply sort it using the insertionSort method
		if(inputArray.length == cutoff)
		{
			return insertionSort(inputArray);
		}
		if(inputArray.length % 2 == 0)
		{
			firstHalf = new int[inputArray.length / 2];
		}
		//This else happens if there is an odd number of elements, then the first array can hold the middle element of the inputArray
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