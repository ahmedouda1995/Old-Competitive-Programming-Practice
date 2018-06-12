package dp_gym;

public class LongestPalindromicSubsequence {

	static String s = "GEEKSFORGEEKS";
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		int dp[][] = new int[s.length()][s.length()];
		
		for(int i = 0; i < s.length(); ++i) dp[i][i] = 1;
		
		for(int l = 2; l <= s.length(); ++l) {
			for(int i = 0; i < s.length() - l + 1; ++i) {
				int j = i + l - 1;
				
				if(s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + dp[i + 1][j - 1];
				else dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
			}
		}
		return dp[0][s.length() - 1];
	}
}
