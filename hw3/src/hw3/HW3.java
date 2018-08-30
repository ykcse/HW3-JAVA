package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HW3
{
	
	// Function to insertion-sort the array list.
	public static void insertionSort(ArrayList<String> arr)
	{
		for(int i = 1; i < arr.size(); i++)
		{
			String key = arr.get(i);
			int j;
			for(j = i - 1; j >= 0 && key.compareTo(arr.get(j)) < 0; j--)
			{
				arr.set(j + 1, arr.get(j));
			}
			arr.set(j + 1, key);
		}
	}
	
	// Function to apply binary search on the array list.
	public static boolean binarySearch(ArrayList<String> arr, int l, int h, String str)
	{
		if(h >= l)
		{
			int m = l + (h - l) / 2;
			if(arr.get(m).compareTo(str) == 0)
				return true;
			else if(arr.get(m).compareTo(str) > 0)
				return binarySearch(arr, l, m - 1, str);
			else
				return binarySearch(arr, m + 1, h, str);
		}
		return false;
	}
	
	public static void main(String[] arg) throws Exception
	{
		// Read the 'unsorted keywords' file first time.
		BufferedReader reader1;
		reader1 = new BufferedReader(new FileReader("C:\\Users\\HP G4\\Desktop\\New folder\\JAVA Lab\\csx-351-hw3-ykcse-master\\HW3-unsorted-keywords.txt"));
		int cnt = 0;
		String line1 = reader1.readLine();
		while(line1 != null)
		{
			cnt++;
			line1 = reader1.readLine();
		}
		System.out.println(cnt);
		System.out.println();
		reader1.close();
		// Dynamic memory allocation for array of strings.
		ArrayList<String> arr = new ArrayList<String>();
		// Read the 'unsorted keywords' file second time.
		BufferedReader reader2;
		reader2 = new BufferedReader(new FileReader("C:\\\\Users\\\\HP G4\\\\Desktop\\\\New folder\\\\JAVA Lab\\\\csx-351-hw3-ykcse-master\\\\HW3-unsorted-keywords.txt"));
		String line2 = reader2.readLine();
		while(line2 != null)
		{
			arr.add(line2);
			line2 = reader2.readLine();
		}
		reader2.close();
		System.out.println(arr.size());
		System.out.println();
		// Sorting the 'keyword' array list.
		insertionSort(arr);
		System.out.println(arr);
		System.out.println();
		// Read the 'C++ Program' input file.
		BufferedReader reader3;
		reader3 = new BufferedReader(new FileReader("C:\\Users\\HP G4\\Desktop\\New folder\\JAVA Lab\\csx-351-hw3-ykcse-master\\HW3-input-code.cpp"));
		String line3 = reader3.readLine();
		int lineNo = 0; 
		int count = 0;
		while(line3 != null)
		{
			// Increment the line number of 'C++ Program'.
			lineNo++;
			ArrayList<String> lineList = new ArrayList<String>();
			ArrayList<Integer> posList = new ArrayList<Integer>();
			int i, j;
			for(i = 0; i < line3.length(); i++)
			{
				if(line3.charAt(i) >= 'a' && line3.charAt(i) <= 'z')
				{
					for(j = i + 1; j < line3.length() && ((line3.charAt(j) >= 'a' && line3.charAt(j) <= 'z') || (line3.charAt(j) == '_') || (line3.charAt(j) >= '0' && line3.charAt(j) <= '9')); j++)
					{
						// Do nothing, just let 'j' increment itself.
					}
					String str = line3.substring(i, j);
					// Checking if the word is in the list of keywords.
					if(binarySearch(arr, 0, arr.size() - 1, str))
					{
						count++;
						lineList.add(str);
						posList.add(i);
					}
					i = j; // Here if I did 'i = j + 1', then for each line, only 1 keyword was showing even if there were many.
				}
				else if(line3.charAt(i) == '/' && line3.charAt(i + 1) == '/')
				{
					break;
				}
			}
			// Printing the output.
			if(!lineList.isEmpty())
			{
				System.out.print("Line " + lineNo + " : ");
				for(int n = 0; n < lineList.size(); n++)
				{
					System.out.print(lineList.get(n) + "(" + posList.get(n) + ") ");
				}
				System.out.println();
			}
			line3 = reader3.readLine();
		}
		System.out.println();
		System.out.println("Number of keywords found = " + count);
		reader3.close();
	}
	
}
