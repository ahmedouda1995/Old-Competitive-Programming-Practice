package dp_gym;

public class Min_no_coins_make_value {

	static int coins[] = {9, 6, 5, 1};
	static int N = coins.length, INF = (int) 1e9;
	
	public static void main(String[] args) {
		System.out.println(solve(11));
	}

	private static int solve(int M) {
		int dp[][] = new int[N][M + 1];
		dp[0][0] = 0;
		for(int i = 1; i <= M; ++i) {
			if(i % coins[0] == 0)
				dp[0][i] = 1 + dp[0][i - coins[0]];
			else
				dp[0][i] = -1;
		}
		
		for(int i = 1; i < N; ++i) {
			for(int j = 1; j <= M; ++j) {
				dp[i][j] = INF;
				if(j >= coins[i] && dp[i][j - coins[i]] != -1)
					dp[i][j] = 1 + dp[i][j - coins[i]];
				if(dp[i - 1][j] != -1)
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
				if(dp[i][j] == INF)
					dp[i][j] = -1;
			}
		}
		//for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(dp[i]));
		return dp[N - 1][M];
	}
}
