package ch6_4;

import java.util.Arrays;

public class LPS {
	
	// longest prefix suffix array computation

	public static void main(String[] args) {
		String s = "aaaa";
		System.out.println(Arrays.toString(solve(s)));
	}

	private static int[] solve(String s) {
		int [] lps = new int[s.length()];
		lps[0] = 0;
		int i = 1, j = 0;
		
		while(i < s.length()) {
			if(s.charAt(i) == s.charAt(j)) {
				lps[i] = j + 1;
				i++; j++;
			}
			else {
				if(j != 0) {
					j = lps[j - 1];
				}
				else {
					lps[i] = 0;
					j = 0; i++;
				}
			}
		}
		return lps;
	}
}
