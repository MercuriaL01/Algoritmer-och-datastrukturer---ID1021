/*README for lab 3 (also in pdf)
This Lab was very similar to the second one so I will just go more in detail about the differences instead.
The assignment was the same besides that instead of using a binary search symbol tree in lab three a binary search tree is used.
The main difference between those two is that the binary symbol table uses two arrays to store the keys and the values, while the binary search tree uses nodes.
A node in this case is an element which contains a key, a value, a size, a left and a right. The key and value we have already heard about however the rest are new.
The size is simply the number of nodes in the substree (so all nodes “under” a specific node),
the left is the node “down to the left” (which is smaller in key value) and the right is the node “down to the right” (which is larger in key value).
So basically it is called a tree since every node is linked to the left and the right somehow and
therefore we get kind of a tree-like structure with one node at the top and further down there are a lot more since most nodes have a left and right that go “down”.
Besides this the two assignments are almost identical besides that of course the methods have to be redone a bit to suit the different way of storing the keys and values in the binary search tree.
*/

import java.io.FileReader;
import java.util.Scanner;

public class FrequencyCounter_question3
{
    private FrequencyCounter_question3()
    {

	}

    public static void main(String[] args) throws Exception    //Has to throw exception for "FileNotFoundException"
    {
        int distinct = 0;
        int words = 0;
        BinarySearchTree_question3<String, Integer> binarySearchTree = new BinarySearchTree_question3<String, Integer>();
        FileReader file = new FileReader("the_text.txt");
		Scanner fileScan = new Scanner(file);
		Stopwatch_question3 stopwatch = new Stopwatch_question3();
        // compute frequency counts
        while(fileScan.hasNextLine())
        {
			String line = fileScan.nextLine();
			String newLine = "";
			char[] chars = new char[line.length()];
			for(int i = 0; i < line.length(); i++)   //For each character in line remove the ones that aren't letters and replace them with ' '
			{
				chars[i] = line.charAt(i);
				if(!Character.isLetter(chars[i]))
				{
					chars[i] = ' ';
 				}
				newLine += chars[i];
			}
			String[] allWords = newLine.split("\\s+"); // Will split the string into a string array with one or multiple spaces as the separator between words that are put into the array
			for(String s : allWords)
			{
				if(s.trim().length() <= 0) //Check if length == 0 when trimming the string (removing the whitespaces) if so don't use this string.
				{
					continue;
				}
        	    String key = s;
        	    words++;
        	    if (binarySearchTree.contains(key))
        	    {
        	        binarySearchTree.put(key, binarySearchTree.get(key) + 1);
        	    }
        	    else
        	    {
        	        binarySearchTree.put(key, 1);
        	        distinct++;
        	    }
        	    if(words == 1000)
        	    {
					break;
				}
			}
			if(words== 1000)
			{
				break;
			}
        }
        double firstTime = stopwatch.elapsedTime();
        stopwatch.reset();
        // find a key with the highest frequency count
        String max = "";
        int x = 0;
        for(String word : binarySearchTree.keys())
        {
			if(max == "")
			{
				max = word;
			}
            else if (binarySearchTree.get(word) > binarySearchTree.get(max))
            {
                max = word;
			}
			System.out.println(++x + " ord: " + word);
        }
        double secondTime = stopwatch.elapsedTime();
        System.out.println("max      = " + max + ", Value:" + binarySearchTree.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
		System.out.println("Time it took in seconds to find the max value word in the binary search symbol table: " + secondTime);
        System.out.println("Time it took in seconds to put in the words into the binary search symbol table: " + firstTime);
    }
}