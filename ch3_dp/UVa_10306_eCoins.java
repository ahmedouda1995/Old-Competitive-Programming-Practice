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

public class UVa_10306_eCoins {

	static int x[], y[];
	static final int INF = (int) 1e9;
	static int memo[][][] = new int[301][301][40];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Pair> a[] = new ArrayList[90_001];
		
		for(int i = 0; i <= 300; ++i) {
			for(int j = 0; j <= 300; ++j) {
				int tmp = i * i + j * j;
				if(tmp <= 90_000) {
					if(a[tmp] == null) a[tmp] = new ArrayList<Pair>();
					a[tmp].add(new Pair(i, j));
				}
			}
		}
		
		int t = sc.nextInt(), m, s;
		
		while(t-- > 0) {
			m = sc.nextInt(); s = sc.nextInt();
			x = new int[m]; y = new int[m];
			for(int i = 0; i < m; ++i) { x[i] = sc.nextInt(); y[i] = sc.nextInt(); }
			
			int min = INF;
			
			for(int i = 0; i <= s; ++i)
				for(int j = 0; j <= s; ++j)
					Arrays.fill(memo[i][j], -1);
			
			for(Pair p : a[s * s]) {
				min = Math.min(min, solve(p.x, p.y, 0));
			}
			
			if(min == INF)
				out.println("not possible");
			else
				out.println(min);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int a, int b, int item) {
		if(item >= x.length) return INF;
		if(a < 0 || b < 0) return INF;
		if(a == 0 && b == 0) return 0;
		if(memo[a][b][item] != -1) return memo[a][b][item];
		return memo[a][b][item] =
				Math.min(1 + solve(a - x[item], b - y[item], item), solve(a, b, item + 1));
	}

	static class Pair { int x, y; Pair(int a, int b) { x = a; y = b; } }
	
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