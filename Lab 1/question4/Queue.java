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

	//if addToFront is true then add to the front i addToFront is false add to the end
	public void enqueue(boolean addToFront, T thing)
	{
		if(firstNode == null)
		{
			firstNode = new Node(thing, null);
			lastNode = firstNode;
			lastNode.next= firstNode;
			printQueue();
			return;
		}
		else if(addToFront)
		{
			Node newNode = new Node(thing, firstNode);
			firstNode = newNode;
			lastNode.next = firstNode;
			printQueue();
			return;
		}
		Node tempNode = new Node(thing, firstNode);
		lastNode.next = tempNode;
		lastNode = tempNode;
		printQueue();
	}

	//if removeAtFront is true then remove at the front i removeAtFront is false remove at the end
	public void dequeue(boolean removeAtFront)
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
		else if(removeAtFront)
		{
			firstNode = firstNode.next;
			lastNode.next = firstNode;
		}
		else
		{
			Node tempNode = firstNode;
			while(tempNode.next.next != firstNode)
			{
				tempNode = tempNode.next;
			}
			tempNode.next = firstNode;
			lastNode = tempNode;
		}
		printQueue();
	}
}
