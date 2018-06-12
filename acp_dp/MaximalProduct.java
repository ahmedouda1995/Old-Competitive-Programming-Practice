package acp_dp;

import java.util.Arrays;

public class MaximalProduct {

	static final int INF = (int) 1e9;
	static long memo[][][] = new long[101][101][21];
	
	public static long maximalProduct(int s, int k) {
		for(int i = 0; i <= 100; ++i)
			for(int j = 0; j <= 100; ++j)
				Arrays.fill(memo[i][j], -1);
		return solve(1, s, k);
	}

	private static long solve(int i, int s, int k) {
		if(s < 0 || k < 0) return 0;
		if(s == 0 && k == 0) return 1;
		if(i > 100) return 0;
		if(memo[i][s][k] != -1) return memo[i][s][k];
		return memo[i][s][k] =
				Math.max(i * solve(i, s - i, k - 1), solve(i + 1, s, k));
	}
}
