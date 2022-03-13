/* README
ABOUT LAB 2 (also in text pdf sent)
In this lab the main complexity was creating a binary search symbol table and using it together with the frequency counter.
The binary search symbol table has three class variables: keys, values and n.
Keys is an array with all the keys (keys are a Comparable type object), values is an array as well (with comparable objects as well)
and lastly n is simply the amount of keys/values in the binary search symbol table.
You can either create a binary search symbol table by not having any parameters.
The most important methods in this class are: put, get, delete I would say, therefore I will explain them.
What put does is simply to add a key and value to the symbol table (in its correct place since the keys are in sorted order),
this is done by checking where they key parameter should be put using a rank method that checks what index it should be put to,
after this we simply check if the key is already in the keys array or not and if not we add it and if needed we size the array up so it fits.
The get method is used to return the value that the parameter key has bound to it,
this is done by checking where the parameter key would be set in the array using the rank method and then if that index has the same key that key’s value is returned, otherwise null is returned since the key doesn’t exist.
Lastly the last very important method is delete, which as it sounds like deletes the key that is sent in as a parameter and also its value associated with it.
This is done by checking the parameter key’s rank and then seeing if the key in that index is the same as the parameter key,
if no we simply do nothing since there is no key to remove but if it is the same key then we remove that key by setting every index from that point in both keys and values to the index after it, and deleting the one we started with.

The second most important part of this assignment was the frequency counter so now I will explain how that class works.
This class has the main method in it, which is from where I read “the text”, filter the text so I get all the words in it,
and then put the first 1000 words into a binary search symbol table,
lastly the  main method finds the word that was most common and also prints some stuff in the end that I will explain more in detail soon.
The binary search symbol tree that is created is created with Strings as the keys and Integers as the values since the keys are going to be
the words and the Integers are going to be the amount of times the word has been found in the first 1000 words of “the text”.
The file is read using a scanner that scans the next line until there aren’t more lines to scan,
and for each line scanned the line is examined by a for-loop that goes through every character in the line and checks
if it is a letter (using the Character.isLetter(char) method), if it is then its added to a string and if not it is simply ignored.
This string is then split by “(\\s+)” since this splits the string into a string array with one or multiple spaces as the separator between words
that are put into each index of the array. Then a for-each loop goes through every word in the array and basically adds it to the created binary search symbol tree,
if the key already existed 1 is added to the value and otherwise it starts at 1. This is done until 1000 words have been added to the binary search symbol tree.
Worth noting is also that a stopwatch class was created before beginning to read the first 1000 words from the file and after being done with it the time is saved as a double.
The next thing in the main method is that every word in the binary search symbol table is gone through by a for-each loop and if that word has the highest value connected to is recorded it is saved as the max value.
This examination of finding the max value is stopwatched in the same way as reading and inserting the words was.
Lastly the main method prints out the max value and the key (word) connected to it,
the number of distinct words(added 1 to a counter every time a new distinct word got added),
the number of words and the time it to to get the max value and also the time it took to read and insert all the words.
*/

import java.io.FileReader;
import java.util.Scanner;

public class FrequencyCounter_question2
{
    private FrequencyCounter_question2()
    {

	}

    public static void main(String[] args) throws Exception    //Has to throw exception for "FileNotFoundException"
    {
        int distinct = 0;
        int words = 0;
        BinarySearchST_question2<String, Integer> binarySearch = new BinarySearchST_question2<String, Integer>();
        FileReader file = new FileReader("the_text.txt");
		Scanner fileScan = new Scanner(file);
		Stopwatch_question2 stopwatch = new Stopwatch_question2();
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
        	    if (binarySearch.contains(key))
        	    {
        	        binarySearch.put(key, binarySearch.get(key) + 1);
        	    }
        	    else
        	    {
        	        binarySearch.put(key, 1);
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
        for(String word : binarySearch.keys())
        {
			if(max == "")
			{
				max = word;
			}
            else if (binarySearch.get(word) > binarySearch.get(max))
            {
                max = word;
			}
			System.out.println(++x + " ord: " + word);
        }
        double secondTime = stopwatch.elapsedTime();
        System.out.println("max      = " + max + ", Value:" + binarySearch.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
        System.out.println("Time it took in seconds to find the max value word in the binary search symbol table: " + secondTime);
        System.out.println("Time it took in seconds to put in the words into the binary search symbol table: " + firstTime);
    }
}