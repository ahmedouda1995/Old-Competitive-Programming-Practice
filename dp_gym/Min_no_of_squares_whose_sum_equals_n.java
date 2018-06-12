package dp_gym;

public class Min_no_of_squares_whose_sum_equals_n {

	static int INF = (int) 1e9;
	
	public static void main(String[] args) {
		System.out.println(solve(6));
	}

	private static int solve(int n) {
		int dp[] = new int[n + 1];
		
		dp[0] = 0;
		
		for(int i = 1; i <= n; ++i) {
			int min = INF;
			for(int j = 1; j * j <= i; ++j) {
				min = Math.min(min, 1 + dp[i - j * j]);
			}
			dp[i] = min;
		}
		//System.out.println(Arrays.toString(dp));
		return dp[n];
	}
}
