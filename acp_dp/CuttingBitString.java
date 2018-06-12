package acp_dp;

import java.util.Arrays;

public class CuttingBitString {

	static int memo[][];
	static int N;
	static final int INF = (int) 1e9;
	static final double EPS = 1e-9;
	static String S;
	
	public static void main(String[] args) {
		System.out.println(getmin("101101101"));
	}
	
	public static int getmin(String S)
	{
		N = S.length();
		memo = new int[N][N];
		CuttingBitString.S = S;
		
		for(int i = 0; i < N; ++i)
			Arrays.fill(memo[i], -1);
		
		int tmp = solve(N - 1, N - 1);
		if(tmp >= INF) return -1; else return tmp;
	}

	private static int solve(int st, int end) {
		if(st == 0)
		{
			if(check(S.substring(st, end + 1))) return 1;
			else return INF;
		}
		if(memo[st][end] != -1) return memo[st][end];
		
		int min = solve(st - 1, end);
		if(check(S.substring(st, end + 1)))
			min = Math.min(min, 1 + solve(st - 1, st - 1));
		
		return memo[st][end] = min;
	}

	private static boolean check(String s) {
		long num = Long.parseLong(s, 2);
		if(num == 0 || s.charAt(0) == '0') return false;
		
		double c = lg(num);
		
		if(Math.abs(Math.floor(c) - c) < EPS)
			return true;
		return false;
	}
	
	static double lg(long n)
	{
		return Math.log(n) / Math.log(5);
	}
}
