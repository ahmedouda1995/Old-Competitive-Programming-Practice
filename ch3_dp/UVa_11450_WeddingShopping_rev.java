package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_11450_WeddingShopping_rev {

	static int M, C;
	static ArrayList<Integer> gar[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			M = sc.nextInt(); C = sc.nextInt();
			gar = new ArrayList[C];
			for(int i = 0; i < C; ++i) {
				gar[i] = new ArrayList<Integer>();
				int m = sc.nextInt();
				
				while(m-- > 0) gar[i].add(sc.nextInt());
			}
			
			int res = solve();
			
			out.println(res == -1?"no solution":M - res);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		boolean dp[][] = new boolean[C][M + 1];
		
		for(int i : gar[0]) {
			if(i <= M)
				dp[0][M - i] = true;
		}
		
		for(int i = 0; i < C - 1; ++i)
			for(int j = 0; j <= M; ++j) {
				if(dp[i][j]) {
					for(int k : gar[i + 1])
						if(k <= j)
							dp[i + 1][j - k] = true;
				}
			}
		
		int res = -1;
		
		for(int i = 0; i <= M; ++i) if(dp[C - 1][i]) { res = i; break; }
		
		return res;
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