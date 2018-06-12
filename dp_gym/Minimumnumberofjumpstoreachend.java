package dp_gym;

public class Minimumnumberofjumpstoreachend {

	static final int INF = (int) 1e9;
	static int arr[] = {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		int dp[] = new int[arr.length];
		for(int i = 0; i < dp.length; ++i) dp[i] = INF;
		dp[0] = 0;
		
		for(int i = 0; i < arr.length; ++i) {
			if(dp[i] == INF) break;
			for(int j = i + 1; j < arr.length && j <= i + arr[i]; ++j) {
				if(dp[i] + 1 < dp[j]) dp[j] = dp[i] + 1;
			}
		}
		
		return dp[arr.length - 1];
	}
}
