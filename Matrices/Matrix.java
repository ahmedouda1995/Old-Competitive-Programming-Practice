package Matrices;

import java.util.Arrays;

public class Matrix {

	// (A x B) * (B * C) => A * C Matrix
	
	public static void main(String[] args) {
		int [][] mat1 = {{1, 2}, {3, 4}};
		int [][] mat2 = {{1, 2, 3, 4}, {5, 6, 7, 8}};
		
		int fib[][] = {{1}, {0}};
		int rec[][] = {{1, 1}, {1, 0}};
		
		System.out.println(matExp(rec, 4)[0][0]);
	}
	
	private static int[][] sumPows(int mat[][], int k)
	{
		if(k == 0)
			return (new int[mat.length][mat[0].length]);
		
		if((k & 1) == 1)
		{
			int [][] tmp = sumPows(mat, k - 1);
			addIdentity(tmp);
			return mult(mat, tmp);
		}
		
		int [][] tmp = matExp(mat, k >> 1);
		addIdentity(tmp);
		
		return mult(sumPows(mat, k >> 1), tmp);
	}
	
	private static int[][] matExp(int[][] mat, int p) {
		if(p == 0)
			return identity(mat.length);
		
		int [][] ret = matExp(mat, p >> 1);
		ret = mult(ret, ret);
		
		if((p & 1) == 1)
			ret = mult(ret, mat);
		
		return ret;
	}
	
	static int[][] add(int [][] a, int [][] b)
	{
		int c[][] = new int[a.length][a[0].length];
		
		for(int i = 0; i < a.length; ++i)
			for(int j = 0; j < a[0].length; ++j)
				c[i][j] = a[i][j] + b[i][j];
		
		return c;
	}

	private static int[][] identity(int n) {
		
		int ret[][] = new int[n][n];
		
		for(int i = 0; i < n; ++i) ret[i][i] = 1;
		
		return ret;
	}

//	private static int[][] naiveExp(int[][] mat1, int p) {
//		
//		int [][] res = identity(mat1.length);
//		
//		for(int i = 0; i < p; ++i)
//			res = mult(res, mat1);
//		
//		return res;
//	}

	static void addIdentity(int [][] mat)
	{
		for(int i = 0; i < mat.length; ++i) mat[i][i]++;
	}
	
	static int[][] mult(int [][] a, int [][] b)
	{
		int n = a.length;
		int m = b[0].length;
		int [][] c = new int[n][m];
		
		// Optimizations => switch loop order if matrices are sparse
		// could make it n ^ 2 by checking if a[i][k] = 0 or b[k][j] = 0
		
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < m; ++j)
				for(int k = 0; k < b.length; ++k)
					c[i][j] += a[i][k] * b[k][j];
		
		return c;
	}
}
