package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_1213 {

	static int N, M, K;
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static int memo[][][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		sieve(1120); M = primes.size();
		memo = new int[M][1121][15];
		for(int i = 0; i < M ; ++i)
			for(int j = 0; j < 1121; ++j)
				Arrays.fill(memo[i][j], -1);
		
		while((N = sc.nextInt()) != 0 | (K = sc.nextInt()) != 0) {
			if(N == 1 || N == 0)
				out.println(0);
			else
				out.println(solve(0, N, K));
		}
		
		out.flush();
		out.close();
	}
	
	
	
	private static int solve(int idx, int n, int k) {
		if(n == 0 && k == 0) return 1;
		if(k < 0) return 0;
		if(idx == M && (k != 0 || n > 0)) return 0;
		if(memo[idx][n][k] != -1) return memo[idx][n][k];
		
		if(n >= primes.get(idx))
			return memo[idx][n][k] = solve(idx + 1, n, k) + solve(idx + 1, n - primes.get(idx), k - 1);
		else
			return memo[idx][n][k] = solve(idx + 1, n, k);
	}



	private static void sieve(int n) {
		boolean prime[] = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = false; prime[1] = false;
		
		for(int i = 2; i <= n; ++i) {
			if(prime[i]) {
				for(int j = i * i; j <= n; j += i) prime[j] = false;
				primes.add(i);
			}
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