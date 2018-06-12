package dp_gym;

public class Countwaystoreachthenthstair {

	public static void main(String[] args) {
		System.out.println(solve(5, 5));
	}

	private static int solve(int n, int m) {
		if(n <= 1) return 1;
		int dp[] = new int[n + 1];
		
		dp[0] = 1; dp[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			for(int j = 1; j <= m && j <= i; j++)
				dp[i] += dp[i - j];
		}
		return dp[n];
	}
}
