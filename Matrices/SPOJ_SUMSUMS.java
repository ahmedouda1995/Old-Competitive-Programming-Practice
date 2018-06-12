package Matrices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SPOJ_SUMSUMS {

	static final int MOD = (int) 98_765_431; 
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		
		out.flush();
		out.close();
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
					c[i][j] = (int) ((1L * c[i][j] + (1L * a[i][k] * b[k][j] % MOD)) % MOD);
		
		return c;
	}
	
	private static int[][] identity(int n) {
		
		int ret[][] = new int[n][n];
		
		for(int i = 0; i < n; ++i) ret[i][i] = 1;
		
		return ret;
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

	static class Pair implements Comparable<Pair> {
		int a, b;
		
		public Pair(int x, int y) {
			a = x; b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			return (a == p.a)?b - p.b:a - p.a; 
		}
		
		@Override
		public String toString() {
			return a + " " + b;
		}
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}