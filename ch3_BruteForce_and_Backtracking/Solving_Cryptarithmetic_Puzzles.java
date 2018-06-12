package ch3_BruteForce_and_Backtracking;

import java.util.TreeMap;

public class Solving_Cryptarithmetic_Puzzles {

	public static void main(String[] args) {
		String s1 = "SEND", s2 = "MORE", s3 = "MONEY";
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		for(int i = 0; i < s1.length(); ++i)
			if(!map.containsKey(s1.charAt(i)))
				map.put(s1.charAt(i), -1);
		for(int i = 0; i < s2.length(); ++i)
			if(!map.containsKey(s2.charAt(i)))
				map.put(s2.charAt(i), -1);
		for(int i = 0; i < s3.length(); ++i)
			if(!map.containsKey(s3.charAt(i)))
				map.put(s3.charAt(i), -1);
		System.out.println(solve(map, s1, s2, s3));
	}

	private static boolean solve(TreeMap<Character, Integer> map, String s1,
			String s2, String s3) {
		//if(check(s1, s2, s3, map))
			//return true;
		
		return false;
	}
}
