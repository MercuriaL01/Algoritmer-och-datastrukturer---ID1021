public class Queue
{
	private Node firstNode;
	private Node lastNode;
	private int length;

	public Queue()
	{
		this.length = 0;
	}

	private class Node
	{
		public int item;
		public Node next;

		public Node(int item, Node next)
		{
			this.item = item;
			this.next = next;
		}
	}

	public String toString()
	{
		String returnString = "";
		Node printNode = firstNode;
		int x = -1;
		if(printNode.next == null)
		{
			returnString += "[" + printNode.item + "]";
		}
		else
		{
			while(printNode.next != null)
			{
				if(++x == 0)
				{
					returnString += "[" + printNode.item + "]";
				}
				else
				{
					returnString += ", [" + printNode.item + "]";
				}
				printNode = printNode.next;
			}
			returnString += ", [" + printNode.item + "]";
		}
		return returnString;
	}

	public void printQueue()
	{
		System.out.println(toString());
	}

	public void enqueue(int thing)
	{
		if(firstNode == null)
		{
			firstNode = new Node(thing, null);
			lastNode = firstNode;
			printQueue();
			length++;
			return;
		}
		Node newNode = new Node(thing, null);
		Node tempNode = firstNode;
		if(newNode.item < firstNode.item)
		{
			newNode.next = firstNode;
			firstNode = newNode;
			length++;
			printQueue();
			return;
		}
		else if(firstNode.next == null)
		{
			firstNode.next = newNode;
			length++;
			printQueue();
			return;
		}

		while(tempNode.next != null && newNode.item > tempNode.next.item)
		{
			tempNode = tempNode.next;
		}
		if(tempNode.next != null)
		{
			newNode.next = tempNode.next;
	    }
		tempNode.next = newNode;
		length++;
		printQueue();
	}

	public void dequeue(int x)
	{
		Node tempNode = firstNode;
		if(x == length)
		{
			firstNode = firstNode.next;
			length--;
			printQueue();
			return;
		}
		for(int i = 0; i < (length) - x - 1; i++)
		{
			tempNode = tempNode.next;
		}
		tempNode.next = tempNode.next.next;
		tempNode = tempNode.next;
		tempNode = null;
		length--;
		printQueue();
	}
}
