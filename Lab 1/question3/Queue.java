public class Queue<T>
{
	private Node firstNode;
	private Node lastNode;

	public Queue()
	{
	}

	private class Node<T>
	{
		public T item;
		public Node previous;
		public Node next;

		public Node(T item, Node previous, Node next)
		{
			this.item = item;
			this.previous = previous;
			this.next = next;
		}
	}

	public String toString()
	{
		String returnString = "";
		Node printNode = firstNode;
		int x = -1;
		if(printNode.next == firstNode)
		{
			returnString += "[" + printNode.item.toString() + "]";
		}
		else
		{
			while(printNode.next != firstNode)
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
			firstNode = new Node(thing, null, null);
			lastNode = firstNode;
			lastNode.next= firstNode;
			lastNode.previous = firstNode;
			firstNode.next = firstNode;
			firstNode.previous = firstNode;
		}
		else
		{
		Node oldFirst = firstNode;
		Node newNode = new Node(thing, lastNode, oldFirst);
		oldFirst.previous = newNode;
		firstNode = newNode;
		lastNode.next = firstNode;
    	}
    	printQueue();
	}

	public void dequeue()
	{
		if(firstNode == null)
		{
			return;
		}
		else if(firstNode.next == firstNode)
		{
			firstNode = null;
			lastNode = null;
			return;
		}
		else
		{
			lastNode.previous.next = firstNode;
			firstNode.previous = lastNode.previous;
			lastNode = lastNode.previous;
		}
		printQueue();
	}
}
