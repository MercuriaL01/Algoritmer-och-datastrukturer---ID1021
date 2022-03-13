import java.io.File;
import java.util.Scanner;

public class question_6
{
	public static void main(String[] args) throws Exception    //Has to throw exception for "FileNotFoundException"
	{
		while(true)
		{
			Scanner scan = new Scanner(System.in);
			Stopwatch_question6 stopwatch = new Stopwatch_question6();
			System.out.println("Enter 1 to sort 1,000 numbers");
			System.out.println("Enter 2 to sort 10,000 numbers");
			System.out.println("Enter 3 to sort 100,000 numbers");
			System.out.println("Enter 4 to sort 1,000,000 numbers");
	   		int choice = scan.nextInt();
	   		//Line below is needed to remove the enter-key that was input in the end.
	   		scan.nextLine();
	   		int[] inputArray;
	   		switch(choice)
	   		{
				case 1:
				inputArray = getArray("random1000.txt", 1000);
				stopwatch.reset();
				quickSortFirst(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with first element partitioning took: " + stopwatch.elapsedTime());
				printIntArray(inputArray);
				inputArray = getArray("random1000.txt", 1000);
				stopwatch.reset();
				quickSortMedian(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with median-of-three partitioning took: " + stopwatch.elapsedTime());
				 break;
				case 2:
				inputArray = getArray("random10000.txt", 10000);
				stopwatch.reset();
				quickSortFirst(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with first element partitioning took: " + stopwatch.elapsedTime());
				inputArray = getArray("random10000.txt", 10000);
				stopwatch.reset();
				quickSortMedian(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with median-of-three partitioning took: " + stopwatch.elapsedTime());
				 break;
				case 3:
				inputArray = getArray("random100000.txt", 100000);
				stopwatch.reset();
				quickSortFirst(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with first element partitioning took: " + stopwatch.elapsedTime());
				inputArray = getArray("random100000.txt", 100000);
				stopwatch.reset();
				quickSortMedian(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with median-of-three partitioning took: " + stopwatch.elapsedTime());
				 break;
				case 4:
				inputArray = getArray("random1000000.txt", 1000000);
				stopwatch.reset();
				quickSortFirst(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with first element partitioning took: " + stopwatch.elapsedTime());
				inputArray = getArray("random1000000.txt", 1000000);
				stopwatch.reset();
				quickSortMedian(inputArray, 0, inputArray.length - 1);
				System.out.println("Quicksort with median-of-three partitioning took: " + stopwatch.elapsedTime());
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

	public static void quickSortFirst(int[] inputArray, int left, int right)
	{
		if(left < right)
		{
			int pivot = quickAlgoritmFirst(inputArray, left, right);
	//			System.out.println(left, pivot);
				quickSortFirst(inputArray, left, pivot - 1);
				quickSortFirst(inputArray, pivot + 1, right);
		}
	}

/*	public static int quickAlgoritmFirst(int[] inputArray, int left, int right)
	{
		int temp = 0;
		//Start with pivot most to the left, the first element
		int pivot = inputArray[left];
		int counter = left; //This counter is left cause the first time its used its switched to counter++ so its equal to left + 1 then at the start (so the pivot lets to be alone at the "left" element)
		for(int i = left + 1; i <= right; i++)  //It can start at low + 1 since we want to ignore the pivot element at the "left" index
		{
			//Here we go from the left to the rigth and if the element in smaller than the pivot element we switch places on it and the element to the left that is inputArray[++counter]
			if(inputArray[i] < pivot)
			{
				temp = inputArray[i];
				inputArray[i] = inputArray[++counter];
				inputArray[counter] = temp;
			}
		}
		//Below we set the element to the right of inputArray[counter] to the pivot element since the one counter points to is the last one that is smaller in value than the pivot element
		temp = inputArray[counter + 1];
		inputArray[counter + 1] = pivot;
		inputArray[left] = temp;

		return counter + 1;
	}
*/	public static int quickAlgoritmFirst(int[] inputArray, int left, int right)
	{
		int temp = 0;
		//Start with pivot most to the left, the first element
		int pivot = inputArray[left];
		int counter = left; //This counter is left cause the first time its used its switched to counter++ so its equal to left + 1 then at the start (so the pivot lets to be alone at the "left" element)
		for(int i = right; i >= 0; i--)  //It can start at low + 1 since we want to ignore the pivot element at the "left" index
		{
			//Here we go from the left to the rigth and if the element in smaller than the pivot element we switch places on it and the element to the left that is inputArray[++counter]
			if(inputArray[i] < pivot)
			{
				temp = inputArray[i];
				inputArray[i] = inputArray[counter++];
				inputArray[counter] = temp;
			}
		}
		//Below we set the element to the right of inputArray[counter] to the pivot element since the one counter points to is the last one that is smaller in value than the pivot element
		temp = inputArray[counter];
		inputArray[counter] = pivot;
		inputArray[left] = temp;

System.out.println(counter + 1);
return counter + 1;
	}

	public static void quickSortMedian(int[] inputArray, int left, int right)
	{
		if(left < right)
		{
			int pivot = quickAlgoritmMedian(inputArray, left, right);
			quickSortMedian(inputArray, left, pivot - 1);
			quickSortMedian(inputArray, pivot + 1, right);
		}
	}

	public static int quickAlgoritmMedian(int[] inputArray, int left, int right)
	{
		int temp = 0;
		int pivot = 0;
		//Set pivot to the median of the left, right and middle element (isn't exactly middle element in the case that there is a even number of elements)
		//Then we switch place between the pivot element and the element in "right", so we get the pivot element in the end of the array
		if((inputArray[left] > inputArray[inputArray.length / 2] && inputArray[left] < inputArray[right]) || (inputArray[left] < inputArray[inputArray.length / 2] && inputArray[left] > inputArray[right]))
		{
			pivot = inputArray[left];
			inputArray[left] = inputArray[right];
			inputArray[right] = pivot;
		}
		else if((inputArray[right] > inputArray[inputArray.length / 2] && inputArray[right] < inputArray[left]) || (inputArray[right] < inputArray[inputArray.length / 2] && inputArray[right] > inputArray[left]))
		{
			pivot = inputArray[right];
		}
		else
		{
			pivot = inputArray[inputArray.length / 2];
			inputArray[inputArray.length / 2] = inputArray[right];
			inputArray[right] = pivot;
		}
		int counter = left; //This counter is left cause the first time its used its switched to counter++ so its equal to left + 1 then at the start (so the pivot lets to be alone at the "left" element)
		for(int i = left; i <= right - 1; i++)  //It can end at one before right since right is the pivot element
		{
			//Here we go from the left to the rigth and if the element in smaller than the pivot element we switch places on it and the element to the left that is inputArray[++counter]
			if(inputArray[i] < pivot)
			{
				temp = inputArray[i];
				inputArray[i] = inputArray[++counter];
				inputArray[counter] = temp;
			}
		}
		//Below we set the element to the right of inputArray[counter] to the pivot element since the one counter points to is the last one that is smaller in value than the pivot element
		temp = inputArray[counter + 1];
		inputArray[counter + 1] = pivot;
		inputArray[right] = temp;
		return counter + 1;
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