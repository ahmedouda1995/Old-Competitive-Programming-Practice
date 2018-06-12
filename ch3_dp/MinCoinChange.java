package ch3_dp;

import java.util.Arrays;

public class MinCoinChange {

	static int coin_den[];
	static int memo[];
	static int dp[];
	static int N;
	
	public static void main(String[] args) {
		int n = 4;
		coin_den = new int[]{4, 5, 3, 2};
		int amount = 7;
		N = n;
		memo = new int[amount + 1];
		dp = new int[amount + 1];
		Arrays.fill(memo, -1);
		System.out.println(solve(n, amount));
		System.out.println(solve(amount));
		System.out.println(Arrays.toString(dp));
	}
	// wrong without (int) 1e9
	private static int solve(int amount) {
		dp[0] = 0;
		for(int i = 1; i <= amount; ++i) {
			int min = (int) 1e9;
			for(int j = 0; j < N; ++j) {
				if(i >= coin_den[j])
					min = Math.min(min, dp[i - coin_den[j]]);
			}
			dp[i] = min + 1;
		}
		return dp[amount];
	}

	private static int solve(int n, int amount) {
		if(amount < 0)
			return (int) 1e9;
		if(amount == 0)
			return 0;
		if(memo[amount] != -1)
			return memo[amount];
		
		int min = (int) 1e9;
		for(int j = 0; j < n; ++j) {
			min = Math.min(min, solve(n, amount - coin_den[j]));
		}
		
		return memo[amount] = min + 1;
	}
}
