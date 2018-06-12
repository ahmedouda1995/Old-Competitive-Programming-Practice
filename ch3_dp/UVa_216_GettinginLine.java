package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_216_GettinginLine {

	static final double INF = 1e9;
	static int N;
	static double dist[][] = new double[8][8];
	static double memo[][] = new double[8][1 << 8];
	static int next[][] = new int[8][1 << 8];
	static PrintWriter out = new PrintWriter(System.out);
	static int [] posX = new int[8], posY = new int[8];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			for(int i = 0; i < N; ++i) { posX[i] = sc.nextInt(); posY[i] = sc.nextInt(); }
			
			for(int i = 0; i < N; ++i) {
				for(int j = i + 1; j < N; ++j) {
					dist[i][j] = dist[j][i] = h(posX[i], posY[i], posX[j], posY[j]) + 16.0;
				}
			}
			
			out.println("**********************************************************");
			out.printf("Network #%d\n", cases++);
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			for(int i = 0; i < N; ++i) Arrays.fill(next[i], -1);
			double min = solve(0, 1);
			StringBuilder sb = new StringBuilder();
			print(0, 1, sb);
			String connections = sb.toString();
			
			for(int v = 1; v < N; ++v) {
				for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
				for(int i = 0; i < N; ++i) Arrays.fill(next[i], -1);
				double tmp = solve(v, (1 << v));
				if(tmp < min) {
					min = tmp;
					sb = new StringBuilder();
					print(v, (1 << v), sb);
					connections = sb.toString();
				}
			}
			out.print(connections);
			out.printf("Number of feet of cable required is %.2f.\n", min);
		}
		
		out.flush();
		out.close();
	}
	
	private static void print(int i, int mask, StringBuilder sb) {
		if(mask == ((1 << N) - 1)) return;
		
		int to = next[i][mask];
		sb.append(String.format("Cable requirement to connect (%d,%d) to (%d,%d) is %.2f feet.\n",
				posX[i], posY[i], posX[to], posY[to], dist[i][to]));
		print(to, mask | (1 << to), sb);
	}
	
	private static double solve(int vertex, int mask) {
		if(mask == ((1 << N) - 1)) return 0;
		if(memo[vertex][mask] != -1) return memo[vertex][mask];
		
		double min = INF;
		
		for(int i = 0; i < N; ++i) {
			if(((1 << i) & mask) == 0) {
				double tmp = dist[vertex][i] + solve(i, mask | (1 << i));
				if(tmp < min) {
					min = tmp;
					next[vertex][mask] = i;
				}
			}
		}
		return memo[vertex][mask] = min;
	}

	private static double h(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
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