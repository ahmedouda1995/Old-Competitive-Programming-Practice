package LCA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_12238_AntsColony {

	static ArrayList<Pair> adj[];
	static final int N = (int) 1e5;
	static final int l = 16;
	static int dp[][] = new int[l + 1][N];
	static int dpth[] = new int[N];
	static long dist[] = new long[N];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((n = sc.nextInt()) != 0)
		{
			adj = new ArrayList[n];
			for(int i = 0; i < n; ++i) adj[i] = new ArrayList<Pair>();
			
			int v, w;
			
			for(int i = 1; i < n; ++i)
			{
				v = sc.nextInt();
				w = sc.nextInt();
				adj[i].add(new Pair(v, w));
				adj[v].add(new Pair(i, w));
			}
			
			for(int i = 0; i <= l; ++i) Arrays.fill(dp[i], -1);
			dpth[0] = 0;
			dist[0] = 0;
			dfs(0, -1);
			
			for(int i = 1; i <= l; ++i)
				for(int j = 0; j < n; ++j)
				{
					if(dp[i - 1][j] != -1)
						dp[i][j] = dp[i - 1][dp[i - 1][j]];
				}
			
			int q = sc.nextInt();
			
			while(q-- > 0)
				out.print(distance(sc.nextInt(), sc.nextInt()) + (q == 0?"\n":" "));
		}
		
		out.flush();
		out.close();
	}
	
	private static long distance(int a, int b) {
		int lca = getLCA(a, b);
		return 1L * dist[a] + dist[b] - 2L * dist[lca];
	}

	private static void dfs(int u, int p) {
		
		dp[0][u] = p;
		
		for(Pair v : adj[u])
		{
			if(v.a != p)
			{
				dpth[v.a] = dpth[u] + 1;
				dist[v.a] = dist[u] + v.b;
				dfs(v.a, u);
			}
		}
	}
	
	static int getKthAnc(int u, int k)
	{
		int d = dpth[u] - k;
		
		int nu;
		
		for(int j = l; j >= 0; j--)
		{
			nu = dp[j][u];
			if(nu == -1) continue;
			if(dpth[nu] == d) return nu;
			if(dpth[nu] > d) u = nu;
		}
		return -1;
	}
	
	static int getLCA(int a, int b)
	{
		if(dpth[a] > dpth[b]) a = getKthAnc(a, dpth[a] - dpth[b]);
		else if(dpth[a] < dpth[b]) b = getKthAnc(b, dpth[b] - dpth[a]);
		
		// depth[a] == depth[b]
		if(a == b) return a;
		
		for(int j = l; j >= 0; j--)
		{
			int na = dp[j][a], nb = dp[j][b];
			
			if(na != -1 && nb != -1 && na != nb) { a = na; b = nb;}
		}
		
		return dp[0][a];
	}

	static class Pair implements Comparable<Pair> {
		int a, b;
		
		public Pair(int x, int y) {
			a = x; b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			return (a == p.a)?b - p.b:a - p.a; 
		}
		
		@Override
		public String toString() {
			return a + " " + b;
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