package dp;

import java.util.Arrays;

public class Minimum_coin_change {

	static int [] change = {1, 5, 6, 8};
	static int [][] dp = new int [change.length][1000];
	
	public static void main(String[] args) {
		int amount = 11;
		System.out.println(minCoins(amount));
	}

	private static int minCoins(int amount) {
		for (int j = 0; j <= amount; j++) {
			if(j >= change[0])
				dp[0][j] = 1 + dp[0][j - change[0]];
			else
				dp[0][j] = 0;
		}
		for (int i = 1; i < change.length; i++) {
			for (int j = 0; j <= amount; j++) {
				if(j == 0)
					dp[i][j] = 0;
				else {
					if(j >= change[i]){
						dp[i][j] = Math.min(dp[i-1][j], 1 + dp[i][j - change[i]]);
					}
					else
						dp[i][j] = dp[i-1][j];
				}
			}
		}
		for (int i = 0; i < change.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		return dp[change.length - 1][amount];
	}
}
