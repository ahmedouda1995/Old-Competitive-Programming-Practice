package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Advanced2 {
	
	static final int MOD = (int) 1e9 + 7;
	static int ans[]={1, 1, 3, 8, 21};
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		long n = sc.nextLong();
		if(n <= 5)
		{
			System.out.println(ans[(int)n - 1]);
			return;
		}
		
		int mat[][] = new int[4][4];
		int res[][] = new int[4][1];
		
		res[0][0] = 1; res[1][0] = 3; res[2][0] = 8; res[3][0] = 21;
		mat[0][1] = 1;
		mat[1][2] = 1;
		mat[2][3] = 1;
		mat[3][0] = 4;
		mat[3][1] = 3;
		mat[3][2] = 2;
		mat[3][3] = 1;
		out.println(Matrix.mult(Matrix.matExp(mat, n - 5), res)[3][0]);
		
		out.flush();
		out.close();
	}
	
	static class Matrix {

//		public static void main(String[] args) {
//			int [][] mat1 = {{1, 2}, {3, 4}};
//			int [][] mat2 = {{1, 2, 3, 4}, {5, 6, 7, 8}};
//			
//			int fib[][] = {{1}, {0}};
//			int rec[][] = {{1, 1}, {1, 0}};
//			
//			System.out.println(matExp(rec, 4)[0][0]);
//		}
		
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
		
		private static int[][] matExp(int[][] mat, long l) {
			if(l == 0)
				return identity(mat.length);
			
			int [][] ret = matExp(mat, l >> 1);
			ret = mult(ret, ret);
			
			if((l & 1) == 1)
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

//		private static int[][] naiveExp(int[][] mat1, int p) {
//			
//			int [][] res = identity(mat1.length);
//			
//			for(int i = 0; i < p; ++i)
//				res = mult(res, mat1);
//			
//			return res;
//		}

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
						c[i][j] = (int) (c[i][j] + (1L * a[i][k] * b[k][j] % MOD)) % MOD;
			
			return c;
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}