package acp_dp;

import java.util.Arrays;

public class AdjacentSwaps {

	static int arr[];
	static int memo[][][][];
	static int N;
	static final int MOD = (int) 1e9 + 7;
	static int nCk[][];
	
	public static void main(String[] args) {
		System.out.println(theCount(new int[]	
				
				{2, 0, 3, 1}));
	}
	
	public static int theCount(int[] p)
	{
		arr = p;
		N = p.length;
		memo = new int[N][N][N][N];
		nCk = new int[51][51];
		for(int i = 0; i <= 50; ++i)
			nCk[i][i] = nCk[i][0] = 1;

		for(int i = 1; i <= 50; ++i)
			for(int j = 1; j < i; ++j)
				nCk[i][j] = (nCk[i - 1][j] + nCk[i - 1][j - 1]) % MOD;
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				for(int k = 0; k < N; ++k)
					Arrays.fill(memo[i][j][k], -1);
		
		return solve(0, N - 1, 0, N - 1);
	}

	private static int solve(int s, int e, int a, int b) {
		if(s == e) return (a == arr[s]?1:0);
		if(s + 1 == e) return (b == arr[s] && a == arr[e]?1:0);
		if(memo[s][e][a][b] != -1) return memo[s][e][a][b];
		
		int left = 0, right = 0;
		int ans = 0;
		int val1, val2;
		for(int i = s + 1; i <= e; ++i)
		{
			val1 = a; val2 = i;
			if(i - 1 == s)
				val1 = val2 = i;
			else if(i == e)
				val2 = b;
			left = solve(s, i - 1, val1, val2);
			
			val1 = i - 1; val2 = b;
			if(i - 1 == s)
				val1 = a;
			else if(i == e)
				val1 = val2 = i - 1;
			right = solve(i, e, val1, val2);
			
			ans = ans + (int) ((1L * left * right % MOD) * nCk[e - s - 1][e - i] % MOD);
			ans %= MOD;
		}
		return memo[s][e][a][b] = ans;
	}
}
