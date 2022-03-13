import java.util.Scanner;

public class question_3
{
	public static void main(String[] args)
	{
		Queue<String> queue = new Queue<String>();
		while(true)
		{
			test(queue);
		}
	}

	public static void test(Queue inputQueue)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 1 to add an element to your queue");
		System.out.println("Enter 2 to remove an element from your queue");
		System.out.println("Enter 3 to view your queue");
		int input = scan.nextInt();
		scan.nextLine();
		switch(input)
		{
			case 1:
			System.out.println("Write what you want to add to the queue");
			String userInput = scan.nextLine();
			inputQueue.enqueue(userInput);
			 break;
			case 2:
			inputQueue.dequeue();
			 break;
			case 3:
			inputQueue.printQueue();
			 break;
			default:
			System.out.println("That wasns't a number between 1-3!");
			 break;
		}
		System.out.println();
	}
}