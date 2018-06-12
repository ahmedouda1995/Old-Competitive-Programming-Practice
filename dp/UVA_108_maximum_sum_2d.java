package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_108_maximum_sum_2d {

	int [][] memo = new int[101][101];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), maxSubRect = -127*100*100, subRect;
		int [][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
				if (i > 0) a[i][j] += a[i - 1][j];
				if (j > 0) a[i][j] += a[i][j - 1];
				if (i > 0 && j > 0) a[i][j] -= a[i - 1][j - 1];
			}
		}
		// O(n ^ 4)
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
			for (int k = i; k < n; k++) for (int l = j; l < n; l++) {
				subRect = a[k][l];
				if (i > 0) subRect -= a[i - 1][l];
				if (j > 0) subRect -= a[k][j - 1];
				if (i > 0 && j > 0) subRect += a[i - 1][j - 1];
				maxSubRect = Math.max(maxSubRect, subRect);
			}
		out.println(maxSubRect);
		out.flush();
		out.close();
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
