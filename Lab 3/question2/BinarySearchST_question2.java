//Anv?nde mycket ifr?n pricetons implementation av denna klass

public class BinarySearchST_question2<Key extends Comparable<Key>, Value>
{
	private static final int INIT_CAPACITY = 2;
	public Key[] keys;
	private Value[] values;
	private int n = 0;

	//Creates an empty symbol table
	public BinarySearchST_question2()
	{
		this(INIT_CAPACITY); //calls the constructor beneath
	}

	//Creates an empty symbol table
	//The input parameter is the max capacity
	public BinarySearchST_question2(int capacity)
	{
		//We cast the arrays to be able to assign them to keys and values.
		keys = (Key[])new Comparable[capacity];
		values = (Value[])new Object[capacity];
	}

	//This is made to resize the size of the array with keys and the one with values.
	//They are resized to the int "capacity" is set to
	private void resize(int capacity)
	{
		//Here we cast the arrays so they can be assigned to keys and values
		Key[] tempKeys = (Key[])new Comparable[capacity];
		Value[] tempValues = (Value[])new Object[capacity];
		for(int i = 0; i < n; i++)   //n is the size of the arrays (so the number of keys and values related to them)
		{
			tempKeys[i] = keys[i];
			tempValues[i] = values[i];
 		}
 		keys = tempKeys;
 		values = tempValues;
	}

	//Returns true if the size of the arrays keys and values is 0 (if n is 0), else this method returns false
	public boolean isEmpty()
	{
		return n == 0;
	}

	//This methods return true if the array with keys has the parameter key and else it returns false.
	public boolean contains(Key key)
	{
		//Here we simply check if the parameter key is null, since that isn't aloud we throw an IllegalArgumentException then (this exception since key is a arguments which is illegal)
		if(key == null)
		{
			throw new IllegalArgumentException("The argument is null, this is not allowed in contains()");
		}
		return get(key) != null;
	}

	//This method returns the value on the same position as the parameter key, if one exists. Otherwise the method returns null
	public Value get(Key key)
	{
		//Here we simply check if the parameter key is null, since that isn't aloud we throw an IllegalArgumentException then (this exception since key is a arguments which is illegal)
		if(key == null)
		{
			throw new IllegalArgumentException("Argument is null, which is not aloud in get()");
		}
		if(isEmpty())
		{
			return null;
		}
		int i = rank(key);
		if(i < n && keys[i].compareTo(key) == 0)   //the compareTo checks if the parameter key is equal to keys[i], keys[i] is where the parameter key fits in the keys array since rank(key) says the index where all keys to the left are less than paramter key
		{
			return values[i];
		}
		return null;   //here we return null since if the last if didn't happen it means the parameter key isn't in the array with keys
	}

	//This method returns the index at which every value to the left is smaller than the parameter key
	public int rank(Key key)
	{
		//Here we simply check if the parameter key is null, since that isn't aloud we throw an IllegalArgumentException then (this exception since key is a arguments which is illegal)
		if(key == null)
		{
			throw new IllegalArgumentException("Argument is null, which is not aloud in rank()");
		}

		int low = 0;
		int high = n-1;
		while(low <= high)
		{
			int mid = low + ((high - low) / 2);
			int compareValue = key.compareTo(keys[mid]);
			if(compareValue < 0)
			{
				high = mid - 1;
			}
			else if(compareValue > 0)
			{
				low = mid + 1;
			}
			else
			{
				return mid;
			}
		}
		return low;
	}

	//This method puts the key and value to the place they should go in the arrays
	public void put(Key key, Value value)
	{
		//Here we simply check if the parameter key is null, since that isn't aloud we throw an IllegalArgumentException then (this exception since key is a arguments which is illegal)
		if(key == null)
		{
			throw new IllegalArgumentException("key is null, which is not aloud in put()");
		}
		if(value == null)  //Delete the specified key if value is null since if a value is null it doesn't exist
		{
			delete(key);
			return;
		}

		int i = rank(key);

		if(i < n && keys[i].compareTo(key) == 0)   //Key is in table already since if compareTO returns 0 it is the same value
		{
			values[i] = value;
			return;
		}

		if(n == keys.length)  //if the keys array is full double its size using the resize method
		{
			resize(2*keys.length);
		}
		for(int j = n; j > i; j--)   //From top to bottom set key and values from j to j - 1 until we ge to i + 1 since we add keys[i] and values[i] after the for loop
		{
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		n++;
	}

	//this method deletes the key and value associated to it, coresponsing to the parameter key.
	public void delete(Key key)
	{
		if(key == null)
		{
			throw new IllegalArgumentException("Can't delete null, since its like deleting nothing");
		}
		if(isEmpty()) //Nothing to delete if there are no keys/values
		{
			return;
		}
		int i = rank(key);

		if(i == n || keys[i].compareTo(key) != 0) //If the key doesn't exist just return
		{
			return;
		}

		for(int j = i; j < n-1; j++)   //
		{
			keys[j] = keys[j + 1];
			values[j] = values[j + 1];
		}
		n--;
		keys[n] = null;
		values[n] = null;
	}

	//returns smallest key
	public Key min() throws Exception
	{
		if(isEmpty())
		{
			throw new Exception("No min if no keys");
		}
		return keys[0];
	}

	//returns largest key
	public Key max() throws Exception
	{
		if(isEmpty())
		{
			throw new Exception("No max if no keys");
		}
		return keys[n-1];
	}

	//return all keys in a way so their values and such can be gotten (they are returned Iterable)
	public Iterable<Key> keys() throws Exception
	{
		return keys(min(), max());
	}


	public Iterable<Key> keys(Key low, Key high)
	{
		if(low == null)
		{
			throw new IllegalArgumentException("first argument in keys() is null which isn't aloud");
		}
		if(high == null)
		{
			throw new IllegalArgumentException("second argument to keys() is null which isn't aloud");
		}

		Queue_question2<Key> queue = new Queue_question2<Key>();
		if(low.compareTo(high) > 0)
		{
			return queue;
		}
		for(int i = rank(low); i < rank(high); i++) //From lowest to highest an add all elements
		{
			queue.enqueue(keys[i]);
		}
		if(contains(high))
		{
			queue.enqueue(keys[rank(high)]);
		}
		return queue;
	}
}










