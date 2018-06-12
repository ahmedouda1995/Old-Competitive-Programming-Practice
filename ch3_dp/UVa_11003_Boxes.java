package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11003_Boxes {

	static int N, INF = (int) 1e9;
	static int wt[], ld[];
	static int memo[][] = new int[1000][6001];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while((N = sc.nextInt()) != 0) {
			wt = new int[N]; ld = new int[N];
			
			for(int i = 0; i < N; ++i) {
				wt[i] = sc.nextInt();
				ld[i] = sc.nextInt();
				Arrays.fill(memo[i], -1);
			}
			
			out.println(solve(0, 6000));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int item, int maxLoad) {
		if(item == N) return 0;
		if(memo[item][maxLoad] != -1) return memo[item][maxLoad];
		if(wt[item] <= maxLoad)
			return memo[item][maxLoad] =
				Math.max(solve(item + 1, maxLoad),
						1 + solve(item + 1, Math.min(ld[item], maxLoad - wt[item])));
		else
			return memo[item][maxLoad] = solve(item + 1, maxLoad);
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