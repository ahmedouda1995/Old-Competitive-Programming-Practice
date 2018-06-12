package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10130_SuperSale_rev {

	static int memo[][] = new int[1000][31];
	static int wt[], val[];
	static int N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); wt = new int[N]; val = new int[N];
			for(int i = 0; i < N; ++i) { val[i] = sc.nextInt(); wt[i] = sc.nextInt(); }
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			
			int g = sc.nextInt(), sum = 0;
			
			while(g-- > 0) {
				sum += solve(0, sc.nextInt());
			}
			out.println(sum);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int item, int w) {
		if(w < 0) return -1000_000_000;
		if(item == N || w == 0) return 0;
		if(memo[item][w] != -1) return memo[item][w];
		
		int next = -1;
		if(w >= wt[item]) next = val[item] + solve(item + 1, w - wt[item]);
		int max = Math.max(solve(item + 1, w), next);
		return memo[item][w] = max;
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