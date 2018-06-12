package hld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
	
	static int [] hld_sz, in, nxt, rin, val;
	static int N, l = 17;
	static int dp[][];
	static int dpth[];
	static int t = 0;
	static ArrayList<Integer> adj[];
	static SegmentTree tree;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		N = sc.nextInt();
		int q = sc.nextInt(), u, v, lca, op;
		
		init();
		
		for(int i = 0; i < N; ++i)
		{
			adj[i] = new ArrayList<Integer>();
			val[i] = sc.nextInt();
		}
		
		for(int i = 0; i < N - 1; ++i)
		{
			u = sc.nextInt() - 1;
			v = sc.nextInt() - 1;
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		
		dfs_sz(0, -1);
		nxt[0] = 0;
		dfs_hld(0, -1);
		
		int segIn[] = new int[N];
		for(int i = 0; i < N; ++i)
			segIn[i] = val[rin[i]];
		tree = new SegmentTree(segIn);
		
		for(int i = 0; i <= l; ++i) Arrays.fill(dp[i], -1);
		dpth[0] = 0;
		dfs(0, -1);
		
		for(int j = 1; j <= l; ++j)
			for(int i = 0; i < N; ++i)
				dp[j][i] = (dp[j - 1][i] == -1)?-1:dp[j - 1][dp[j - 1][i]];
		
		while(q-- > 0)
		{
			op = sc.nextInt();
			u = sc.nextInt() - 1;
			v = sc.nextInt() - 1;
			
			if(op == 1)
				tree.updatePoint(in[u], v + 1);
			else
			{
				lca = getLCA(u, v);
				out.println(query(lca, u) + query(lca, v) - tree.query(in[lca], in[lca]));
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static long query(int lca, int v) {
		int lcaHead = nxt[lca], vHead = nxt[v];
		
		long ans = 0;
		
		while(vHead != lcaHead)
		{
			ans += tree.query(in[vHead], in[v]);
			v = dp[0][nxt[v]];
			vHead = nxt[v];
		}
		
		ans += tree.query(in[lca], in[v]);
		
		return ans;
	}

	private static void init() {
		adj = new ArrayList[N];
		val = new int[N];
		hld_sz = new int[N];
		in = new int[N];
		nxt = new int[N];
		rin = new int[N];
		dp = new int[l + 1][N];
		dpth = new int[N];
	}

	static void dfs_hld(int u, int p)
	{
	    in[u] = t++;
	    rin[in[u]] = u;
	    for(int v: adj[u])
	    {
	    	if(v == p) continue;
	        nxt[v] = (v == adj[u].get(0) ? nxt[u] : v);
	        dfs_hld(v, u);
	    }
	}
	
	static void dfs_sz(int u, int p)
	{
		if(p == adj[u].get(0))
		{
			adj[u].set(0, adj[u].get(adj[u].size() - 1));
			adj[u].set(adj[u].size() - 1, p);
		}
		hld_sz[u] = 1;
		int v;
		for(int i = 0; i < adj[u].size(); ++i)
		{
			v = adj[u].get(i);
			if(v == p) continue;
			dfs_sz(v, u);
			hld_sz[u] += hld_sz[v];
			if(hld_sz[v] > hld_sz[adj[u].get(0)])
			{
				adj[u].set(i, adj[u].get(0));
				adj[u].set(0, v);
			}
		}
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
	
	static class SegmentTree {

		int n, arr[];
		long sTree[];
		
		public SegmentTree(int in[]) {
			n = in.length; arr = in;
			int sz = getSz(n);
			sTree = new long[sz];
			build(1, 0, n - 1);
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}

		void build(int node, int b, int e) {
			if(b == e)
				sTree[node] = arr[b];
			else
			{
				int mid = (b + e >> 1);
				build(node << 1, b, mid);
				build(node << 1 | 1, mid + 1, e);
				sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
			}
		}
		
		void updatePoint(int idx, int val)
		{
			updatePoint(1, idx, 0, n - 1, val);
		}

		void updatePoint(int node, int idx, int b, int e, int val) {
			if(b == e) sTree[node] = val;
			else
			{
				int mid = (b + e >> 1);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, val);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, val);
				sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
			}
		}
		
		long query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi);
		}

		private long query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return 0;
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				long left = query(node << 1, b, mid, lo, hi);
				long right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return left + right;
			}
		}
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		public Scanner(FileReader r){	br = new BufferedReader(r);}
		public String next() throws IOException { while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine()); return st.nextToken(); }
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		public long nextLong() throws IOException {return Long.parseLong(next());}
		public String nextLine() throws IOException {return br.readLine();}
		public double nextDouble() throws IOException { return Double.parseDouble(next()); }
		public boolean ready() throws IOException {return br.ready();}
	}
}