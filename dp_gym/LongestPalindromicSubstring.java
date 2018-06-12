package dp_gym;

public class LongestPalindromicSubstring {

	static String s = "forgeeksskeegfor";
	static int N = s.length();
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		boolean dp[][] = new boolean[N][N];
		
		for(int i = 0; i < N; ++i) dp[i][i] = true;
		
		int max = 1;
		
		for(int l = 2; l <= N; ++l) {
			for(int i = 0; i < N - l + 1; ++i) {
				int j = i + l - 1;
				if(s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j - i == 1)){
					dp[i][j] = true;
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}
}
