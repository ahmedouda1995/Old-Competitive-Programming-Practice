package dp_gym;

import java.util.Arrays;

public class SubsetSumProblem {

	static int set[] = {3, 34, 4, 12, 5, 2};
	
	public static void main(String[] args) {
		System.out.println(solve(35));
	}

	private static boolean solve(int n) {
		boolean dp[][] = new boolean[set.length][n + 1];
		
		for(int i = 0; i < set.length; ++i) dp[i][0] = true;
		
		if(set[0] <= n) dp[0][set[0]] = true;
		
		for(int i = 1; i < set.length; ++i)
			for(int j = 1; j <= n; ++j) {
				if(dp[i - 1][j])
					dp[i][j] = true;
				else if(j >= set[i])
					dp[i][j] = dp[i - 1][j - set[i]];
			}
		
		for(int i = 0; i < dp.length; ++i) System.out.println(Arrays.toString(dp[i]));
		
		return dp[set.length - 1][n];
	}
}
