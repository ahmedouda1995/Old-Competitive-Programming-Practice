package ch3_dp;

import java.util.Arrays;

public class Knapsack_0_1 {
	
	static int weights[] = {10, 4, 6, 12};
	static int val[] = {100, 70, 50, 10};
	static int W = 12;
	static int dp[][] = new int[4][W + 1];
	static int memo[][] = new int[4][W + 1];
	
	public static void main(String[] args) {
		System.out.println(solve());
		for(int i = 0; i < dp.length; ++i) {
			System.out.println(Arrays.toString(dp[i]));
		}
		for(int i = 0; i < memo.length; ++i) Arrays.fill(memo[i], -1);
		System.out.println(solve_TD(0, W));
		for(int i = 0; i < dp.length; ++i) {
			System.out.println(Arrays.toString(memo[i]));
		}
	}

	private static int solve_TD(int item, int w) {
		if(w <= 0 || item >= dp.length)
			return 0;
		if(memo[item][w] != -1)
			return memo[item][w];
		
		if(w >= weights[item])
			return memo[item][w] = Math.max(val[item] + solve_TD(item + 1, w - weights[item])
				, solve_TD(item + 1, w));
		else
			return memo[item][w]  = solve_TD(item + 1, w);
	}

	private static int solve() {
		for(int i = 0; i < dp[0].length; ++i)
			if(weights[0] <= i)
				dp[0][i] = val[0];
		
		for(int i = 1; i < dp.length; ++i) {
			
			for(int j = 0; j < dp[0].length; ++j) {
				if(j == 0)
					dp[i][j] = 0;
				else if(weights[i] <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], val[i] + dp[i - 1][j - weights[i]]);
				}
				else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}
		return dp[dp.length - 1][W];
	}
}
