package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_990_DivingforGold {

	static int d[] = new int[30];
	static int v[] = new int[30];
	static int dp[][] = new int[30][10001];
	static int T, N, W;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		StringTokenizer st;
		boolean first = true;
		while((s = sc.nextLine()) != null) {
			if(!first)
				out.println();
			if(first) {
				first = false;
			}
			st = new StringTokenizer(s);
			T = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			s = sc.nextLine();
			N = Integer.parseInt(s);
			
			for(int i = 0; i < N; ++i) { d[i] = sc.nextInt(); v[i] = sc.nextInt(); }
			
			if(N == 0)
				out.println(0);
			else {
				int res = solve();
				out.println(res);
				construct(res, out);
			}
			sc.nextLine();
		}
		
		out.flush();
		out.close();
	}
	
	private static void construct(int res, PrintWriter out) {
		Stack<Integer> s = new Stack<Integer>();
		
		int i = N - 1, j = T;
		while(i > 0 && res > 0) {
			if(dp[i][j] == dp[i - 1][j])
				i--;
			else {
				s.push(v[i]); s.push(d[i]);
				j -= ((d[i] * W * 3));
				res -= v[i];
				i--;
			}
		}
		int size = (res > 0)?s.size() / 2 + 1:s.size() / 2;
		out.println(size);
		if(res > 0) {
			out.println(d[0] + " " + v[0]);
		}
		while(!s.isEmpty()) {
			out.println(s.pop() + " " + s.pop());
		}
	}

	private static int solve() {
		if(N == 0)
			return 0;
		for(int i = 0; i <= T; ++i) {
			if(i >= (d[0] * W * 3))
				dp[0][i] = v[0];
			else
				dp[0][i] = 0;
		}
		
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j <= T; ++j) {
				if(j >= (d[i] * W * 3))
					dp[i][j] = Math.max(v[i] + dp[i - 1][j - (d[i] * W * 3)], dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		
		return dp[N - 1][T];
	}

	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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