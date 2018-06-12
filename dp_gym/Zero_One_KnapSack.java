package dp_gym;

public class Zero_One_KnapSack {

	static int N = 3;
	static int val[] = {60, 100, 120};
	static int wt[] = {10, 20, 30};
	
	public static void main(String[] args) {
		System.out.println(solve(50));
		System.out.println(unboundedKnapsack(100));
	}

	private static int unboundedKnapsack(int W) {
		int dp[] = new int[W + 1];
		
		for(int i = 0; i <= W; ++i) {
			for(int j = 0; j < N; ++j) {
				if(i >= wt[j])
					dp[i] = Math.max(dp[i], val[j] + dp[i - wt[j]]);
			}
		}
		
		return dp[W];
	}

	private static int solve(int W) {
		int dp[][] = new int[N][W + 1];
		
		for(int i = 0; i <= W; ++i) {
			dp[0][i] = (i >= wt[0])?val[0]:0;
		}
			
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j <= W; ++j) {
				if(j == 0) dp[i][j] = 0;
				else if(j >= wt[i])
					dp[i][j] = Math.max(dp[i - 1][j], val[i] + dp[i - 1][j - wt[i]]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		
		return dp[N - 1][W];
	}
}
