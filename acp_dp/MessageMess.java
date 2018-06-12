package acp_dp;

import java.util.Arrays;
import java.util.HashSet;

public class MessageMess {

	static String arr[];
	static String s;
	static int N;
	static HashSet<String> set = new HashSet<String>();
	static long memo[][];
	static boolean done = false;
	
	public static void main(String[] args) {
		String dict[] = {"A", "AA", "AAA", "AAAA", "AAAAA", "AAAAAA", "AAAAAAA", "AAAAAAAA", "AAAAAAAAA", "AAAAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAAA", "AAAAAAAAAAAAA", "AAAAAAAAAAAAAA", "AAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};
		System.out.println(restore(dict, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
	}
	
	public static String restore(String[] dictionary, String message)
	{
		arr = dictionary;
		s = message;
		N = s.length();
		memo = new long[N][N];
		
		for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
		
		for(int i = 0; i < dictionary.length; ++i) set.add(dictionary[i]);
		long ans = solve(0, 0);
		System.out.println(ans);
		if(ans == 0)
			return "IMPOSSIBLE!";
		else if(ans > 1)
			return "AMBIGUOUS!";
		else
			return construct(0, 0).trim();
	}

	private static String construct(int i, int j) {
		if(j == N)
			return "";
		
		int count = 0;
		
		if(set.contains(s.substring(i, j + 1)))
			count += solve(j + 1, j + 1);
		if(count == solve(i, j))
			return  " " + s.substring(i, j + 1) + construct(j + 1, j + 1);
		else
			return construct(i, j + 1);
	}

	private static long solve(int i, int j) {
		//if(done) return 2;
		if(j == N)
			return (i == j)?1:0;
		
		if(memo[i][j] != -1) return memo[i][j];
		
		long count = 0;
		
		if(set.contains(s.substring(i, j + 1)))
			count += solve(j + 1, j + 1);
		count += solve(i, j + 1);
		//if(count > 1)
			//done = true;
		return memo[i][j] = count;
	}
}
