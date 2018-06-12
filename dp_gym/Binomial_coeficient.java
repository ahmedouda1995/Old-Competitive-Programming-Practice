package dp_gym;

import java.util.Arrays;

public class Binomial_coeficient {

	public static void main(String[] args) {
		System.out.println(solve(4, 4));
	}

	private static int solve(int n, int k) {
		int dp[] = new int[k + 1];
		
		dp[0] = 1;
		
		for(int i = 1; i <= n; ++i) {
			for(int j = Math.min(i, k); j > 0; j--) {
				dp[j] += dp[j - 1];
			}
		}
		System.out.println(Arrays.toString(dp));
		return dp[k];
	}
}
