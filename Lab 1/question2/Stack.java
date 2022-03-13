public class Stack<T>
{
	private Node firstNode;
	private Node lastNode;

	public Stack()
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

	public void printStack()
	{
		System.out.println(toString());
	}

	public void push(T thing)
	{
		if(firstNode == null)
		{
			firstNode = new Node(thing, null);
			lastNode = firstNode;
			lastNode.next= firstNode;
			return;
		}
		Node tempNode = new Node(thing, firstNode);
		lastNode.next = tempNode;
		lastNode = tempNode;
	}

	public T pop()
	{
		if(firstNode == null)
		{
			return null;
		}
		else if(firstNode.next == firstNode)
		{
			Node tempNode = firstNode;
			firstNode = null;
			lastNode = null;
			return (T)tempNode.item;
		}
		else
		{
			Node tempNode = firstNode;
			while(tempNode.next.next != firstNode)
			{
				tempNode = tempNode.next;
			}
			tempNode.next = firstNode;
			Node returnNode = lastNode;
			lastNode = tempNode;
			return (T)returnNode.item;
		}
	}
	public boolean isEmpty()
	{
		if(firstNode == null)
		{
			return true;
		}
		return false;
	}

}
