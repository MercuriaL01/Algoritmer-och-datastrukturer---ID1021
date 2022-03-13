import java.util.Scanner;

public class question_4
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
			System.out.println("Enter 1 to add it to the start if you enter something else it's added to the end");
			String startOrNot = scan.nextLine();
			if(startOrNot.charAt(0) == '1')
			{
				inputQueue.enqueue(true, userInput);
			}
			else
			{
				inputQueue.enqueue(false, userInput);
			}
			 break;
			case 2:
			System.out.println("Enter 1 to remove it at the start if you enter something else it's removed at the end");
			String removeAtStart = scan.nextLine();
			if(removeAtStart.charAt(0) == '1')
			{
				inputQueue.dequeue(true);
			}
			else
			{
				inputQueue.dequeue(false);
			}
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