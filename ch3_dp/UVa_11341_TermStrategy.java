package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11341_TermStrategy {

	static final int INF = (int) 1e9;
	static int N, M;
	static int score[][] = new int[10][101];
	static int memo[][] = new int[10][101];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			for(int i = 0; i < N; ++i) memo[i][0] = -1;
			for(int i = 0; i < N; ++i)
				for(int j = 1; j <= M; ++j) {
					score[i][j] = sc.nextInt();
					memo[i][j] = -1;
				}
			int res = solve(0, M);
			if((res / (N * 1.0)) >= 5.0)
				out.printf("Maximal possible average mark - %.2f.\n", (res / (N * 1.0)));
			else
				out.println("Peter, you shouldn't have played billiard that much.");
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int idx, int timeLeft) {
		//if(timeLeft < 0) return -INF;
		if(idx == N && timeLeft >= 0) return 0;
		if(memo[idx][timeLeft] != -1) return memo[idx][timeLeft];
		
		int max = -INF;
		
		for(int j = 1; j <= Math.min(M, timeLeft - (N - idx - 1)); ++j) {
			if(score[idx][j] >= 5)
				max = Math.max(max, score[idx][j] + solve(idx + 1, timeLeft - j));
		}
		return memo[idx][timeLeft] = max;
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