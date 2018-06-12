package dp_gym;

public class CoinChange {

	static int den[] = {6, 5, 3, 2};
	
	public static void main(String[] args) {
		System.out.println(solve(10));
		System.out.println(solve_space_O_n(10));
	}

	private static int solve_space_O_n(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 1;
		for(int i = 1; i <= n; ++i) {
			if(i >= den[0] && i % den[0] == 0)
				dp[i] = 1;
			else
				dp[i] = 0;
		}
		
		for(int i = 1; i < den.length; ++i) {
			for(int j = den[i]; j <= n; ++j) {
				dp[j] += dp[j - den[i]];
			}
		}
		return dp[n];
	}

	private static int solve(int n) {
		int dp[][] = new int[den.length][n + 1];
		
		dp[0][0] = 1;
		
		for(int i = 1; i <= n; ++i) {
			if(i >= den[0] && i % den[0] == 0)
				dp[0][i] = 1;
			else
				dp[0][i] = 0;
		}
		
		for(int i = 1; i < dp.length; ++i) {
			for(int j = 0; j <= n; ++j) {
				if(j == 0) dp[i][j] = 1;
				else
					dp[i][j] = dp[i - 1][j] + ((j >= den[i])?dp[i][j - den[i]]:0);
			}
		}
		return dp[dp.length - 1][n];
	}
}
