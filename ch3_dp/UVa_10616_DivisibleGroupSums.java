package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10616_DivisibleGroupSums {

	static int N, D;
	static long arr[] = new long[200];
	static long memo[][][] = new long[200][20][11];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int q ,cases = 1;
		
		while((N = sc.nextInt()) != 0 | (q = sc.nextInt()) != 0) {
			for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
			
			out.printf("SET %d:\n", cases++);
			
			for(int k = 1; k <= q; ++k) {
				D = sc.nextInt();
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < 20; ++j)
						Arrays.fill(memo[i][j], -1);
				out.printf("QUERY %d: %d\n", k, solve(0, 0, sc.nextInt()));
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static long solve(int idx, int sum, int k) {
		if(sum == 0 && k == 0) return 1;
		if(k < 0) return 0;
		if(idx == N) return 0;
		
		if(memo[idx][sum][k] != -1) return memo[idx][sum][k];
		
		long tmp = (sum + arr[idx]) % D;
		if(tmp < 0) tmp += D;
		
		return memo[idx][sum][k] =
				solve(idx + 1, (int)tmp, k - 1) +
				solve(idx + 1, sum, k);
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