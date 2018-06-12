package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11566_LetsYumCha {

	// very important * 10 / 11 to avoid floating point error 11 * 5 / 1.1 = 49.9999 not 50
	// which is wrong by type casting becomes 49
	
	static final int INF = (int) 1e9;
	static int N, x, T, K;
	static int food[] = new int[100], favor[] = new int[100];
	static int memo[][][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while((N = sc.nextInt()) != 0) {
			N++;
			x = sc.nextInt(); T = sc.nextInt(); K = sc.nextInt();
			
			int remMoney = (N * x) * 10/ 11 - N * T;
			
			memo = new int[K][remMoney + 1][2 * N + 1];
			for(int i = 0; i < K; ++i) {
				for(int j = 0; j <= remMoney; ++j) Arrays.fill(memo[i][j], -1);
				food[i] = sc.nextInt();
				favor[i] = 0;
				for(int j = 0; j < N; ++j)
					favor[i] += sc.nextInt(); 
			}
			int res = solve(0, remMoney, 0);
			out.printf("%.2f\n", res / (N * 1.0));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int idx, int remMoney, int dishes) {
		if(remMoney < 0 || dishes > 2 * N) return -INF;
		if(idx == K) return 0;
		if(memo[idx][remMoney][dishes] != -1) return memo[idx][remMoney][dishes];
		
		int max = -INF;
		
		for(int i = 0; i <= 2; ++i) {
			max = Math.max(max, i * favor[idx] + solve(idx + 1, remMoney - i * food[idx], dishes + i));
		}
		return memo[idx][remMoney][dishes] = max;
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