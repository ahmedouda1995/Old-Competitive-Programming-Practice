package acp_dp;

import java.util.ArrayList;
import java.util.Arrays;

public class PalindromizationDiv1 {

	static int adjMat[][] = new int[26][26];
	static int add[] = new int[26];
	static int del[] = new int[26];
	static char s[];
	static int memo[][] = new int[50][50];
	static final int INF = (int) 1e9;
	
	public static void main(String[] args) {
		String op[] = {"change b a 100000", "change c a 100000", "change c d 50000", "change b e 50000", "erase d 50000", "erase e 49999"};
		System.out.println(getMinimumCost("caaaaaab", op));
	}
	
	public static int getMinimumCost(String word, String[] operations)
	{
		s = word.toCharArray();
		for(int i = 0; i < 50; ++i) Arrays.fill(memo[i], -1);
		
		Arrays.fill(add, INF);
		Arrays.fill(del, INF);
		
		for(int i = 0; i < 26; ++i)
			Arrays.fill(adjMat[i], INF);
		for(int i = 0; i < 26; ++i) adjMat[i][i] = 0;
		
		for(String op : operations)
		{
			String in[] = op.split(" ");
			
			if(in[0].charAt(0) == 'a')
				add[in[1].charAt(0) - 'a'] = Integer.parseInt(in[2]);
			else if(in[0].charAt(0) == 'e')
				del[in[1].charAt(0) - 'a'] = Integer.parseInt(in[2]);
			else
			{
				char c1 = in[1].charAt(0);
				char c2 = in[2].charAt(0);
				int val = Integer.parseInt(in[3]);
				adjMat[c1 - 'a'][c2 - 'a'] = val;
			}
		}
		
		for(int k = 0; k < 26; ++k)
			for(int i = 0; i < 26; ++i)
				for(int j = 0; j < 26; ++j)
				{
					if(adjMat[i][k] + adjMat[k][j] < adjMat[i][j])
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
				}
		
		for(int i = 0; i < 26; ++i)
			System.out.println(Arrays.toString(adjMat[i]));
		
		int ans = solve(0, s.length - 1);
		return (ans == INF)?-1:ans;
	}
	
	private static int solve(int i, int j) {
		System.out.println(i + " " + j);
		if(i >= j) return 0;
		
		int min = INF;
		if(s[i] == s[j])
			return memo[i][j] = solve(i + 1, j - 1);
		else
		{
			for(int k = 0; k < 26; ++k)
			{
				if(adjMat[s[i] - 'a'][k] + adjMat[s[j] - 'a'][k] < min)
					min = adjMat[s[i] - 'a'][k] + adjMat[s[j] - 'a'][k];
			}
			
			if(min != INF)
				min = Math.min(min, solve(i + 1, j - 1));
			
			// del
			for(int k = 0; k < 26; ++k)
				if(adjMat[s[i] - 'a'][k] != INF && del[k] != INF)
					min = Math.min(min, adjMat[s[i] - 'a'][k] + del[k] + solve(i + 1, j));
			
			for(int k = 0; k < 26; ++k)
				if(adjMat[s[j] - 'a'][k] != INF && del[k] != INF)
					min = Math.min(min, adjMat[s[j] - 'a'][k] + del[k] + solve(i, j - 1));
			
			// add
			for(int k = 0; k < 26; ++k)
				if(adjMat[k][s[i] - 'a'] != INF && add[k] != INF)
					min = Math.min(min, adjMat[k][s[i] - 'a'] + add[k] + solve(i + 1, j));
			
			for(int k = 0; k < 26; ++k)
				if(adjMat[k][s[j] - 'a'] != INF && add[k] != INF)
					min = Math.min(min, adjMat[k][s[j] - 'a'] + add[k] + solve(i, j - 1));
			System.out.println(min);
			return memo[i][j] = min;
		}
	}

	static class Pair implements Comparable<Pair>
	{
		char a, b;
		
		public Pair(char x, char y) {
			a = x;
			b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			if(Character.compare(a, p.a) == 0)
				return Character.compare(b, p.b);
			return Character.compare(a, p.a);
		}
	}
}
