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

public class UVA_10938_Fleacircus {

	static final int N = 5000;
	static final int l = 12;
	static int dp[][] = new int[l + 1][N];
	static int dpth[] = new int[N];
	static ArrayList<Integer> adj[] = new ArrayList[N];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n, q;
		
		while((n = sc.nextInt()) != 0)
		{
			for(int i = 0; i < n; ++i) adj[i] = new ArrayList<Integer>();
			
			int u, v;
			
			for(int i = 1; i < n; ++i)
			{
				u = sc.nextInt() - 1;
				v = sc.nextInt() - 1;
				adj[u].add(v);
				adj[v].add(u);
			}
			
			for(int i = 0; i <= l; ++i) Arrays.fill(dp[i], -1);
			dpth[0] = 0;
			dfs(0, -1);
			
			for(int j = 1; j <= l; ++j)
				for(int i = 0; i < n; ++i)
					dp[j][i] = (dp[j - 1][i] == -1)?-1:dp[j - 1][dp[j - 1][i]];
			
			
			q = sc.nextInt();
			int a, b;
			
			while(q-- > 0)
			{
				a = sc.nextInt() - 1;
				b = sc.nextInt() - 1;
				
				if(a == b)
					out.printf("The fleas meet at %d.\n", (a + 1));
				else
				{
					int lca = getLCA(a, b);
					
					int d1 = dpth[a] - dpth[lca];
					int d2 = dpth[b] - dpth[lca];
					
					int m = Math.min(d1, d2);
					a = getKthAnc(a, m);
					b = getKthAnc(b, m);
					int d = Math.max(d1, d2) - m;
					
					if((d1 + d2) % 2 == 0)
					{
						if(dpth[a] > dpth[b])
							a = b = getKthAnc(a, (d >> 1));
						else if(dpth[a] < dpth[b])
							a = b = getKthAnc(b, (d >> 1));
						
						out.printf("The fleas meet at %d.\n", (a + 1));
					}
					else
					{
						if(dpth[a] > dpth[b])
						{
							a = getKthAnc(a, (d >> 1));
							b = getKthAnc(a, 1);
						}
						else if(dpth[a] < dpth[b])
						{
							b = getKthAnc(b, (d >> 1));
							a = getKthAnc(b, 1);
						}
						
						int x = Math.min(a, b);
						int y = Math.max(a, b);
						out.printf("The fleas jump forever between %d and %d.\n", (x + 1), (y + 1));
					}
				}
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
		
		if(k == 0) return u;
		
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