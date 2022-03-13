import java.util.Scanner;

public class question_2
{
	public static void main(String[] args)
	{
		recursiveReverseChars("", 0);
		System.out.println();
		System.out.println("Now the iterative method starts");
		iterativeReverseChars();
	}

	public static void recursiveReverseChars(String charString, int x)
	{
		if(x == 0)
		{
			Scanner scan = new Scanner(System.in);
			charString = scan.nextLine();
		}
		if(x == charString.length() + 1)
		{
			System.out.println();
			return;
		}
		if(x != 0)
		{
			System.out.print(charString.charAt(charString.length() - x));
		}
		recursiveReverseChars(charString, ++x);
	}

	public static void iterativeReverseChars()
	{
		Scanner scan = new Scanner(System.in);
		Stack<String> stack = new Stack<String>();
		int counter = 0;
		String input = scan.nextLine();
		boolean flag = true;
		String temp = "";
		for(int i = 0; i < input.length(); i++)
		{
			stack.push(String.valueOf(input.charAt(i)));
		}
		for(int i = 0; i < input.length(); i++)
		{
			System.out.print(stack.pop());
		}
		System.out.println();
	}
}