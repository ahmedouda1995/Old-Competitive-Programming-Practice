package dp_gym;

import java.util.Arrays;

public class MinCostPath {

	static int N = 3;
	static int A = 1, B = 2;
	static int mat[][] = {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}};
	static int dp[][];
	static int dr[] = {-1, -1, 0};
	static int dc[] = {0, -1, -1};
	
	public static void main(String[] args) {
		dp = new int[N][N];
		
		System.out.println(solve());
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(dp[i]));
	}

	private static int solve() {
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				if(i == 0 && j == 0) dp[0][0] = mat[0][0];
				else if(i == 0) dp[0][j] = dp[0][j - 1] + mat[i][j];
				else if(j == 0) dp[i][0] = dp[i - 1][0] + mat[i][j];
				else {
					int x, y, min = 1000000;
					for(int k = 0; k < 3; ++k) {
						x = i + dr[k]; y = j + dc[k];
						if(isSafe(x, y)) {
							min = Math.min(min, dp[x][y]);
						}
					}
					dp[i][j] = min + mat[i][j];
				}
				
				if(i == A && j == B) return dp[i][j];
			}
		}
		return dp[A][B];
	}
	
	static boolean isSafe(int i, int j) {
		return i >= 0 && j >= 0;
	}
}
