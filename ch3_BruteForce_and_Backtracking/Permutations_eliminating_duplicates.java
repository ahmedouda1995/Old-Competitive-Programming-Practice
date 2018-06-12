package ch3_BruteForce_and_Backtracking;

import java.util.Arrays;

public class Permutations_eliminating_duplicates {

	static int x = 1;
	
	public static void main(String[] args) {
		String s = "BAB";
		sortedPermutations(s.toCharArray());
	}
	
	public static void sortedPermutations(char[] str) {
		Arrays.sort(str);
		// Print permutations one by one
	    boolean isFinished = false;
	    while(!isFinished){
	    	System.out.print(x++ + " "); System.out.println(str);
	    	
	    	 // Find the rightmost character which is smaller than its next
	        // character. Let us call it 'first char'
	        int i;
	        for (i = str.length - 2; i >= 0; --i)
	            if (str[i] < str[i+1])
	                break;
	 
	        // If there is no such chracter, all are sorted in decreasing order,
	        // means we just printed the last permutation and we are done.
	        if (i == -1)
	            isFinished = true;
	        else
	        {
	            // Find the ceil of 'first char' in right of first character.
	            // Ceil of a character is the smallest character greater than it
	            int ceilIndex = findCeil(str, str[i], i + 1, str.length - 1);
	 
	            // Swap first and second characters
	            swap(i, ceilIndex, str);
	 
	            // Sort the string on right of 'first char'
	            Arrays.sort(str, i + 1, str.length - 1);
	        }
	    }
	}

	// This function finds the index of the smallest character
	// which is greater than 'first' and is present in str[l..h]
	private static int findCeil(char str[], char first, int l, int h)
	{
	    // initialize index of ceiling element
	    int ceilIndex = l;
	 
	    // Now iterate through rest of the elements and find
	    // the smallest character greater than 'first'
	    for (int i = l+1; i <= h; i++)
	        if (str[i] > first && str[i] < str[ceilIndex])
	            ceilIndex = i;
	 
	    return ceilIndex;
	}
	
	private static void swap(int i, int j, char[] chars) {
		char tmp = chars[i]; chars[i] = chars[j]; chars[j] = tmp;
	}
}
