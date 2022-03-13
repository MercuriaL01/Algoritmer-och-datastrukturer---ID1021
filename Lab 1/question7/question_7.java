import java.util.Scanner;

public class question_7
{
	public static void main(String[] args)
	{
		while(true)
		{
			Scanner scan = new Scanner(System.in);
   			String inputString = scan.nextLine();
			if(isBalanced(inputString))
			{
				System.out.println("Balanced");
			}
			else
			{
				System.out.println("Not balanced");
			}
			System.out.println();
		}
	}

	public static boolean isBalanced(String input)
	{
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < input.length(); i++)
		{
	    	char c = input.charAt(i);
			if (c == '(' || c == '[' || c == '{')
			{
	        	stack.push(c);
			}
	        else
	        {
	            if (stack.isEmpty())
	            {
	                return false;
				}
				char prv = stack.pop();
	            if (prv == '(' && c != ')')
	            {
	            	return false;
				}
	            if (prv == '[' && c != ']')
	            {
	            	return false;
				}
	            if (prv == '{' && c != '}')
	            {
	            	return false;
				}
	        }
	    }
	    return stack.isEmpty();
	}
}