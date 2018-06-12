package dp_gym;

import java.util.Arrays;

public class BooleanParenthesizationProblem {

	// less code with total tik & totalkj
	//int tik = T[i][k] + F[i][k];
    //int tkj = T[k+1][j] + F[k+1][j];
	
	static boolean exp[] = {true, true, false, true};
	static char op[] = {'|', '&', '^'};
	static int N = exp.length;
	static int dpTrue[][] = new int[N][N];
	static int dpFalse[][] = new int[N][N];
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		
		for(int i = 0; i < N; ++i) {
			if(exp[i])
				dpTrue[i][i] = 1;
			else
				dpFalse[i][i] = 1;
		}
		
		for(int l = 2; l <= N; ++l) {
			for(int i = 0; i < N - l + 1; ++i) {
				int j = i + l - 1;
				for(int k = i; k < j; ++k) {
					check(i, k, k + 1, j);
				}
			}
		}
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(dpTrue[i]));
		System.out.println();
		for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(dpFalse[i]));
		return dpTrue[0][N - 1];
	}

	private static void check(int a, int b, int c, int d) {
		char ch = op[b];
		
		switch(ch) {
		case '&':
			dpTrue[a][d] += dpTrue[a][b] * dpTrue[c][d];
			
			dpFalse[a][d] += dpFalse[a][b] * dpFalse[c][d];
			dpFalse[a][d] += dpFalse[a][b] * dpTrue[c][d];
			dpFalse[a][d] += dpTrue[a][b] * dpFalse[c][d];
			break;
		case '|':
			
			dpTrue[a][d] += dpTrue[a][b] * dpTrue[c][d];
			dpTrue[a][d] += dpTrue[a][b] * dpFalse[c][d];
			dpTrue[a][d] += dpTrue[c][d] * dpFalse[a][b];
			
			dpFalse[a][d] += dpFalse[a][b] * dpFalse[c][d];
			break;
		case '^': 
			dpTrue[a][d] += dpTrue[a][b] * dpFalse[c][d];
			dpTrue[a][d] += dpTrue[c][d] * dpFalse[a][b];
			
			dpFalse[a][d] += dpTrue[a][b] * dpTrue[c][d];
			dpFalse[a][d] += dpFalse[a][b] * dpFalse[c][d];
			
			break;
		}
	}
}
