package acp_dp;

import java.util.Arrays;
import java.util.HashMap;

public class SentenceDecomposition {

	static String arr[];
	static String s;
	static int memo[][];
	static int N, INF = (int) 1e9;
	
	public static void main(String[] args) {
		String tst[] = {"separate","words"};
		System.out.println(decompose("sepawaterords", tst));
	}
	
	public static int decompose(String sentence, String[] validWords)
	{
		arr = validWords;
		s = sentence;
		N = s.length();
		memo = new int[N][N];
		for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
		
		int ans = solve(0, 0);
		if(ans == INF) return -1;
		else return ans;
	}

	private static int solve(int a, int b) {
		if(b == N)
		{
			if(a == b)
				return 0;
			else
				return INF;
		}
		
		if(memo[a][b] != -1) return memo[a][b];
		
		int res = solve(a, b + 1);
		
		String tmp = s.substring(a, b + 1);
		
		int res2 = INF;
		int cost = valid(tmp);
		if(cost != INF)
			res2 = cost + solve(b + 1, b + 1);
		return memo[a][b] = Math.min(res, res2);
	}
	
	private static int valid(String tmp) {
		
		int min = INF;
		
		for(int i = 0; i < arr.length; ++i)
		{
			if(arr[i].length() == tmp.length())
			{
				char a[] = tmp.toCharArray();
				Arrays.sort(a);
				char [] b = arr[i].toCharArray();
				Arrays.sort(b);
				String f = new String(a);
				String sec = new String(b);
				if(new String(f).equals(new String(sec)))
					min = Math.min(min, cost(tmp, arr[i]));
			}
		}
		return min;
	}

	static int cost(String a, String b)
	{
		int ret = 0;
		
		for(int i = 0; i < a.length(); ++i)
		{
			if(a.charAt(i) != b.charAt(i))
				ret++;
		}
		return ret;
	}
}
