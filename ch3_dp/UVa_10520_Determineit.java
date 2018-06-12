package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10520_Determineit {

	static int N;
	static long memo[][] = new long[21][21];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			N = sc.nextInt();
			for(int i = 0; i <= N; ++i) Arrays.fill(memo[i], -1);
			memo[N][1] = sc.nextInt();
			
			out.println(a(1, N));
		}
		
		out.flush();
		out.close();
	}
	
	private static long a(int i, int j) {
		if(memo[i][j] != -1) return memo[i][j];
		if(i >= j) {
			long max = 0;
			if(i < N) {
				for(int k = i + 1; k <= N; ++k)
					max = Math.max(max, a(k, 1) + a(k, j));
			}
			long max2 = 0;
			if(j > 0) {
				for(int k = 1; k < j; ++k)
					max2 = Math.max(max2, a(i, k) +a(N, k));
			}
			return memo[i][j] = max + max2;
		}
		else {
			long max = 0;
			for(int k = i; k < j; ++k) {
				max = Math.max(max, a(i, k) + a(k + 1, j));
			}
			return memo[i][j] = max;
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