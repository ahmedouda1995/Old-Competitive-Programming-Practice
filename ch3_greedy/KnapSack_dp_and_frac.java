package ch3_greedy;

import java.util.Arrays;

public class KnapSack_dp_and_frac {
	static int W = 50;
	static int [] w = {20, 10, 30}, v = {100, 60, 120};
	static int dp[][] = new int[3][51];
	
	public static void main(String[] args) {
		
		System.out.println(bu());
		for (int i = 0; i < dp.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
	}

	private static int bu() {
		for(int i = 0; i < w.length; ++i) {
			for(int j = 0; j <= W; ++j) {
				if(j == 0)
					dp[i][j] = 0;
				else if(i == 0){
					if(j >= w[0])
						dp[i][j] = v[0];
				}
				else {
					if(w[i] <= j)
						dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
					else
						dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		return dp[w.length - 1][W];
	}
}
