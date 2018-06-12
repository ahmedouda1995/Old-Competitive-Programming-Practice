package dp_gym;

import java.util.Arrays;

public class OptimalBinarySearchTree {

	static int keys[] = {10, 12, 16, 21};
    static int freq[] = {4, 2, 6, 3};
    static int N = freq.length;
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		int dp[][] = new int[N][N];
		int sol[][]= new int[N][N];
		int sum[][]= new int[N][N];
		
		for(int i = 0; i < N; ++i)
			for(int j = i; j < N; ++j) {
				if(i == j) sum[i][j] = freq[i];
				else sum[i][j] = sum[i][j - 1] + freq[j];
			}
		
		for(int i = 0; i < N; ++i) {
			dp[i][i] = freq[i];
			sol[i][i] = i;
		}
		
		for(int l = 2; l <= N; ++l) {
			for(int i = 0; i < N - l + 1; ++i) {
				int j = i + l - 1;
				
				int min = dp[i + 1][j];
				sol[i][j] = i;
				
				for(int k = i + 1; k < j; ++k) {
					if(dp[i][k - 1] + dp[k + 1][j] < min) {
						min = dp[i][k - 1] + dp[k + 1][j];
						sol[i][j] = k;
					}
				}
				
				if(dp[i][j - 1] < min) {
					min = dp[i][j - 1];
					sol[i][j] = j;
				}
				
				dp[i][j] = min + sum[i][j];
			}
		}
		
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(sum[i]));
		System.out.println();
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(sol[i]));
		System.out.println();
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(dp[i]));
		
		return dp[0][N - 1];
	}
}
