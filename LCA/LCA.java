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

public class LCA {
	
	static final int N = (int) 1e5;
	static final int l = 16;
	static int n;
	static int dp[][] = new int[l + 1][N];
	static int dpth[] = new int[N];
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), m, u, v;
		int cases = 1;
		
		while(t-- > 0) {
			n = sc.nextInt();
			
			adj = new ArrayList[n];
			for(int i = 0; i < n; ++i) adj[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < n; ++i) {
				m = sc.nextInt();
				
				while(m-- > 0) {
					v = sc.nextInt() - 1;
					adj[i].add(v);
					adj[v].add(i);
				}
			}
			
			for(int i = 0; i <= l; ++i) Arrays.fill(dp[i], -1);
			dpth[0] = 0;
			dfs(0, -1);
			
			for(int j = 1; j <= l; ++j)
				for(int i = 0; i < n; ++i)
					dp[j][i] = (dp[j - 1][i] == -1)?-1:dp[j - 1][dp[j - 1][i]];
			
			m = sc.nextInt();
			out.printf("Case %d:\n", cases++);
			while(m-- > 0) {
				u = sc.nextInt() - 1;
				v = sc.nextInt() - 1;
				
				out.println(getLCA(u, v) + 1);
			}
		}
		
		out.flush();
		out.close();
	}
	
	static void dfs(int u, int p) {
		dp[0][u] = p;
		
		for(int v : adj[u]) {
			if(v != p) {
				dpth[v] = dpth[u] + 1;
				dfs(v, u);
			}
		}
	}
	
	static int getKthAnc(int u, int k) {
		int d = dpth[u] - k;
		
		for(int j = l; j >= 0; --j) {
			int nu = dp[j][u];
			if(nu == -1) continue;
			if(dpth[nu] == d) return nu;
			else if(dpth[nu] > d) u = nu;
		}
		return -1;
	}
	
	static int getLCA(int a, int b) {
		if(dpth[a] > dpth[b]) a = getKthAnc(a, dpth[a] - dpth[b]);
		else if(dpth[b] > dpth[a]) b = getKthAnc(b, dpth[b] - dpth[a]);
		
		// depth[a] == depth[b]
		if(a == b) return a;
		
		for(int j = l; j >= 0; j--) {
			int na = dp[j][a], nb = dp[j][b];
			
			if(na != -1 && nb != -1 && na != nb) {
				a = na; b = nb;
			}
		}
		
		return dp[0][a];
	}

	static class Pair implements Comparable<Pair> {
		int a, b;
		
		public Pair(int x, int y) {
			a = x;
			b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			return p.b - b;
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