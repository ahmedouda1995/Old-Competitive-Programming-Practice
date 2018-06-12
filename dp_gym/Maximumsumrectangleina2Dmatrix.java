package dp_gym;

import java.util.Arrays;

public class Maximumsumrectangleina2Dmatrix {

	static final int INF = (int) 1e9;
	static int[][] mat = {{1, 2, -1}, {4, -2, 6}, {7, 9, -5}};
	
	public static void main(String[] args) {
		
		System.out.println(solve());
	}

	private static int solve() {
		
		for(int i = 1; i < mat.length; ++i) {
			for(int j = 0; j < mat[0].length; ++j)
				mat[i][j] += mat[i - 1][j];
		}
		
		int max = mat[0][0];
		int sum = mat[0][0];
		
		for(int i = 0; i < mat.length; ++i) {
			for(int j = i; j < mat.length; ++j) {
				sum = mat[j][0];
				sum -= (i == 0)?0:mat[i - 1][0];
				max = Math.max(sum, max);
				
				for(int k = 1; k < mat[0].length; ++k) {
					int next = mat[j][k];
					next -= (i == 0)?0:mat[i - 1][k];
					sum = Math.max(sum + next, next);
					max = Math.max(max, sum);
				}
			}
		}
		for(int i = 0; i < mat.length; ++i) System.out.println(Arrays.toString(mat[i]));
		return max;
	}
}
