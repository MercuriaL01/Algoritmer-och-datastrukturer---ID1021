public class Queue<T>
{
	private Node firstNode;
	private Node lastNode;
	private int length;

	public Queue()
	{
		this.length = 0;
	}

	private class Node<T>
	{
		public T item;
		public Node next;

		public Node(T item, Node next)
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
			returnString += "[" + printNode.item.toString() + "]";
		}
		else
		{
			while(printNode.next != null)
			{
				if(++x == 0)
				{
					returnString += "[" + printNode.item.toString() + "]";
				}
				else
				{
					returnString += ", [" + printNode.item.toString() + "]";
				}
				printNode = printNode.next;
			}
			returnString += ", [" + printNode.item.toString() + "]";
		}
		return returnString;
	}

	public void printQueue()
	{
		System.out.println(toString());
	}

	public void enqueue(T thing)
	{
		if(firstNode == null)
		{
			firstNode = new Node(thing, null);
			lastNode = firstNode;
			printQueue();
			length++;
			return;
		}
		Node tempNode = new Node(thing, null);
		lastNode.next = tempNode;
		lastNode = tempNode;
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
