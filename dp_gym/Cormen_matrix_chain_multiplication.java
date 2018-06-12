package dp_gym;

import java.util.Arrays;

public class Cormen_matrix_chain_multiplication {

	static final int INF = (int) 1e9;
	static int p[] = {30, 35, 15, 5, 10, 20, 25};
	static int dp[][] = new int[p.length - 1][p.length - 1];
	static int sol[][] = new int[p.length - 1][p.length - 1];
	
	// TD sol in package ch9_20
	
	public static void main(String[] args) {
		
		System.out.println(solve(p.length - 1));
		
		for(int i = 0; i < dp.length; ++i)
			System.out.println(Arrays.toString(dp[i]));
		
		System.out.println("-----------------------");
		
		for(int i = 0; i < sol.length; ++i)
			System.out.println(Arrays.toString(sol[i]));
		
		System.out.println();
		
		PRINT_OPTIMAL_PARENS(0, p.length - 2);
		
//		int [][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//		int [][] b = {{4, 5, 6}, {1, 2, 3}, {7, 8, 9}};
//		
//		int res[][] = mult(a, b);
//		
//		for(int i = 0; i < res.length; ++i)
//			System.out.println(Arrays.toString(res[i]));
	}
private static void PRINT_OPTIMAL_PARENS(int i, int j) {
		if(i == j) System.out.printf("mat[%d]", i + 1);
		else {
			System.out.print("(");
			PRINT_OPTIMAL_PARENS(i, sol[i][j]);
			PRINT_OPTIMAL_PARENS(sol[i][j] + 1, j);
			System.out.print(")");
		}
	}

	private static int solve(int n) {
		for(int i = 0; i < n; ++i) dp[i][i] = 0;
		
		for(int l = 2; l <= n; ++l) {
			for(int i = 0; i < n - l + 1; ++i) {
				int j = i + l - 1;
				
				dp[i][j] = INF;
				for(int k = i; k < j; ++k) {
					int q = dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if(q < dp[i][j]) {
						dp[i][j] = q;
						sol[i][j] = k;
					}
				}
			}
		}
		return dp[0][n - 1];
	}

	private static int[][] mult(int[][] a, int[][] b) {
		int [][] c = new int[a.length][b[0].length];
		
		for(int i = 0; i < a.length; ++i) {
			for(int j = 0; j < b[i].length; ++j) {
				c[i][j] = 0;
				
				for(int k = 0; k < a[i].length; ++k) c[i][j] += a[i][k] * b[k][j];
			}
		}
		return c;
	}
}
