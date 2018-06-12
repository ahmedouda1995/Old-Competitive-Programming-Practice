package dp_gym;

import java.util.Arrays;

public class PalindromPartitioning {

	static String s = "abacddc";
	static int N = s.length();
	static int INF = (int) 1e9;
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		int dp[][] = new int[N][N];
		int sol[][] = new int[N][N];
		
		for(int i = 0; i < N; ++i) dp[i][i] = 0;
		
		for(int l = 2; l <= N; ++l) {
			
			for(int i = 0; i < N - l + 1; ++i) {
				int j = i + l - 1;
				
				if(s.charAt(i) == s.charAt(j) && isPalin(i, j)) dp[i][j] = 0;
				
				else {
					int min = INF;
					
					for(int k = i; k < j; ++k) {
						if(1 + dp[i][k] + dp[k + 1][j] < min)
						min = 1 + dp[i][k] + dp[k + 1][j];
						sol[i][j] = k;
					}
					dp[i][j] = min;
				}
			}
			
		}
		
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(dp[i]));
		System.out.println();
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(sol[i]));
		return dp[0][N - 1];
	}

	private static boolean isPalin(int i, int j) {
		while(i < j) {
			if(s.charAt(i) != s.charAt(j)) return false;
			i++; j--;
		}
		return true;
	}
}
