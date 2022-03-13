import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue_question3<Item> implements Iterable<Item>
{
    private Node<Item> first;    // first node in the queue
    private Node<Item> last;     // last node in the queue
    private int n;               // number of elements on queue

    // Node class, a node has an item and a next node
    private static class Node<Item>
    {
        private Item item;
        private Node<Item> next;
    }

    //creates an empty queue
    public Queue_question3()
    {
        first = null;
        last  = null;
        n = 0;
    }

    //returns true if there are no nodes in the queue
    public boolean isEmpty()
    {
        return first == null;
    }

    //return the number of nodes in the queue
    public int size()
    {
        return n;
    }

    //Adds an item to the queue (to the end obviously since its a queue)
    public void enqueue(Item item)
    {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;     //We add the parameter item to the end so the last nodes item must be the input parameter item
        last.next = null;
        if (isEmpty())     //If the queue was empty we only have one element now and then the last an first element will be the same
        {
			first = last;
		}
        else
        {
			oldlast.next = last;   //if a new item is added to the end the old last's next is last
		}
        n++;  //Added one node so we add one to n that counts how many nodes there are in the queue
    }

    //dequeues the first node and returns it
    public Item dequeue()
    {
        if (isEmpty())
        {
			throw new NoSuchElementException("Queue is empty so no nodes to remove from it");
		}
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())  //if empty we set last to null since it doesn't exist
        {
			last = null;
		}
		return item;
    }

    //Returns the queue as a string
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
        {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    //Returns an iterator that goes through the elements (FIFO order)
    public Iterator<Item> iterator()
    {
		//Creates a new LinkedIterator
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item>
    {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) //Constructor which sets the first node to current
        {
            current = first;
        }

        public boolean hasNext() //If you want to see if ther is an next element to grab hasNext looks if ther is a current element
        {
			return current != null;
        }

        public void remove() //Remove isn't needed
        {
			throw new UnsupportedOperationException();
		}

        public Item next() //Return the next element in the list(the current one)
        {
            if (!hasNext()) //If no current then we get an error, so we throw and exception
            {
				throw new NoSuchElementException();
			}
            Item item = current.item;    //Item item is set to the current item in the list
            current = current.next;   //Current is set to the next element in the list
            return item;   //Return the item we created with the previous current item
        }
    }
}