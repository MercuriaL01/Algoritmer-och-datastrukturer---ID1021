import java.util.NoSuchElementException;

public class BinarySearchTree_question3<Key extends Comparable<Key>, Value>
{
    private Node root;             // root of BST

    private class Node
    {
        private Key key;           // The tree is sorted by the keys
        private Value val;         // Data for each key
        private Node left;		   // Tree "branch" to the right
        private Node right;  	   // Tree "branch" to the right
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size)
        {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

	//Creates an empty symbol table
    public BinarySearchTree_question3()
    {

    }

    //Returns true if the size of the arrays keys and values is 0 (if n is 0), else this method returns false
    public boolean isEmpty()
    {
        return size() == 0;
    }

    // returns number of keys/values in total in the tree, so it looks at the root branch which is the one connected to all others and sees its size
    public int size()
    {
        return size(root);
    }

    // return number of keys/values in total at and after the Node, so all branch nodes "under" that node
    private int size(Node x)
    {
        if (x == null)
        {
        	return 0;
		}
        else
        {
        	return x.size;
		}
    }

	//This methods return true if the array with keys has the parameter key and else it returns false.
    public boolean contains(Key key)
    {
        if (key == null)
        {
        	throw new IllegalArgumentException("argument to contains() is null");
		}
        return get(key) != null;
    }

    //This method returns the value on the same position as the parameter key, if one exists. Otherwise the method returns null
    public Value get(Key key)
    {
        return get(root, key);
    }

	 //This method returns calls get until it reaches the right key (goes one step at a time from the root to the left or right until correct key)
    private Value get(Node x, Key key)
    {
        if (key == null)
        {
        	throw new IllegalArgumentException("Argument key is null, which is not aloud in get()");
		}
        if (x == null)
        {
        	return null;
		}
        int compare = key.compareTo(x.key);
        if(compare < 0)
        {
        	return get(x.left, key);
		}
        else if(compare > 0)
        {
        	return get(x.right, key);
		}
        else
        {
        	return x.val;
		}
    }

    //Set new key with the value in the parameter, if the key already exists replace it
    public void put(Key key, Value val) {
        if (key == null)
        {
        	throw new IllegalArgumentException("key is null, which is not aloud in put()");
		}
        if (val == null) //Delete the specified key if value is null since if a value is null it doesn't exist
        {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

	//Call this method until you have reached the correct key where the key value is equal to the first parameter node's key value.
    private Node put(Node x, Key key, Value val)
    {
        if (x == null) //In case this is the first added node
        {
        	return new Node(key, val, 1);
		}
        int compare = key.compareTo(x.key);
        if(compare < 0)
        {
    	    x.left  = put(x.left,  key, val);
		}
        else if(compare > 0)
        {
			x.right = put(x.right, key, val);
		}
        else
        {
			x.val   = val;
		}
        x.size = 1 + size(x.left) + size(x.right); //Set size to everything "down" to the left + "down" to the right plus the "root" element which we added
        return x;
    }


    //Deletes the smallest key and its value
    public void deleteMin()
    {
        if (isEmpty())
        {
        	throw new NoSuchElementException("No keys to remove");
		}
        root = deleteMin(root);
    }

    private Node deleteMin(Node x)
    {
        if(x.left == null) //Go left until no more
        {
        	return x.right;
		}
        x.left = deleteMin(x.left);   //left is set to x.left.right the last time which in some cases is null i there isn't any x.left.right
        x.size = size(x.left) + size(x.right) + 1;  //Size sets to size to the left plus size to the right plus the root element
        return x;
    }

    //Deletes the largest key and its value (reverse of deleteMin)
    public void deleteMax()
    {
        if (isEmpty())
        {
        	throw new NoSuchElementException("No keys to remove");
		}
		root = deleteMax(root);
    }

    private Node deleteMax(Node x)
    {
        if (x.right == null)
        {
        	return x.left;
		}
    	x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    //Delete the parameter key and the value associated to it
    public void delete(Key key)
    {
        if (key == null)
        {
        	throw new IllegalArgumentException("Can't delete null, since its like delting nothing");
		}
        root = delete(root, key);
    }

    private Node delete(Node x, Key key)
    {
        if (x == null)
        {
        	return null;
		}

        int compare = key.compareTo(x.key);
        if(compare < 0) //Here we go down to the left until we reach that the key is equal to the node x
        {
			x.left  = delete(x.left,  key);
		}
        else if(compare > 0) //Here we go down to the right until we reach that the key is equal to the node x
        {
			x.right = delete(x.right, key);
		}
        else //Here x is the key to remove
        {
            if(x.right == null) //if right == null we connect the node before x to the one left of x
            {
            	return x.left;
			}
            if (x.left  == null) //if left == null we connect the node before x to the one right of x
            {
				return x.right;
			}
            Node t = x;
            x = min(t.right);    //x = min of right
            x.right = deleteMin(t.right);   //x.right = min of right after previous min of right is x now
            x.left = t.left;   //x.left = same as before
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }


    //Returns smallest key
    public Key min()
    {
        if (isEmpty())
        {
        	throw new NoSuchElementException("No min if no keys");
		}
        return min(root).key;
    }

	//Go to the node most to the left and return it
    private Node min(Node x)
    {
        if (x.left == null)
        {
			return x;
		}
        else
        {
			return min(x.left);
		}
    }

    //Returns largest key
    public Key max()
    {
        if (isEmpty())
        {
			throw new NoSuchElementException("No max if no keys");
		}
        return max(root).key;
    }

	//Go to the node most to the right and return it
    private Node max(Node x)
    {
        if (x.right == null)
        {
			return x;
		}
        else
        {
			return max(x.right);
		}
	}

   //Return the key associated to the rank given as a parameter (if it exists)
    public Key select(int rank)
    {
        if (rank < 0 || rank >= size())
        {
            throw new IllegalArgumentException("This rank is out of bounds, not accepted for select()");
        }
        return select(root, rank);
    }

    //Find the key with correct rank value, this we do by going down to the left and right
    private Key select(Node x, int rank)
    {
        if (x == null) //If it doesn't exist return nothing
        {
			return null;
		}
        int leftSize = size(x.left);
        if(leftSize > rank)
        {
			return select(x.left,  rank);
		}
        else if(leftSize < rank)
        {
			return select(x.right, rank - leftSize - 1);
		}
        else //Here we find the Node with the key that has the correct rank value
        {
			return x.key;
		}
    }

    //This method returns the index at which every value to the left is smaller than the parameter key
    public int rank(Key key)
    {
        if (key == null)
        {
        	throw new IllegalArgumentException("Argument is null, which is not aloud in rank()");
		}
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x)
    {
        if (x == null) //If the node doesn't exist return 0
        {
			return 0;
		}
        int compare = key.compareTo(x.key);
        if(compare < 0)
        {
			return rank(key, x.left);
		}
        else if(compare > 0)
        {
			return 1 + size(x.left) + rank(key, x.right);
		}
        else   //Here we find the correct node and return the size which is the amount of nodes "under it" to the left and right
        {
			return size(x.left);
		}
    }

    //returns all keys that are in the "Tree"
    public Iterable<Key> keys()
    {
        if (isEmpty())
        {
			return new Queue_question3<Key>();
		}
        return keys(min(), max());
    }

    //returns all keys that are in the "Tree"
    public Iterable<Key> keys(Key low, Key high)
    {
        if (low == null)
        {
			throw new IllegalArgumentException("first argument in keys() is null which isn't aloud");
		}
        if (high == null)
        {
			throw new IllegalArgumentException("second argument in keys() is null which isn't aloud");
		}

        Queue_question3<Key> queue = new Queue_question3<Key>();
        keys(root, queue, low, high);
        return queue;
    }


    private void keys(Node x, Queue_question3<Key> queue, Key low, Key high)
    {
		if(x == null) //Ignore node if its null
		{
			return;
		}
		int compareLow = low.compareTo(x.key);
		int compareHigh = high.compareTo(x.key);
		if(compareLow < 0) //If not the lowest node
		{
			keys(x.left, queue, low, high);
		}
		if(compareLow <= 0 && compareHigh >= 0)	//will enqueue from smallest to biggest
		{
			queue.enqueue(x.key);
		}
		if(compareHigh > 0)  //if not the highest node

		{
			keys(x.right, queue, low, high);
		}
	}
}












