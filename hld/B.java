package hld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class B {
	
	static int [] hld_sz, in, nxt, rin, val;
	static int N, l = 17;
	static int dp[][];
	static int dpth[];
	static int t = 0;
	static ArrayList<Integer> adj[];
	static ImpTreap treap;
	
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
		
		treap = new ImpTreap();
		for(int i = 0; i < N; ++i)
			treap.insert(val[rin[i]], i);
		
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
			
			if(op == 2)
			{
				lca = getLCA(u, v);
				if(isAncestor(u, v))
				{
					int valV = treap.query(in[v], in[v]);
					shiftDown(v, u, valV);
				}
				else
				{
					int lcaVal = treap.query(in[lca], in[lca]);
					shiftUp(u, lca, treap.query(in[v], in[v]));
					int newLcaVal = treap.query(in[lca], in[lca]);
					treap.delete(in[lca]);
					treap.insert(lcaVal, in[lca]);
					shiftDown(v, lca, -1);
					treap.delete(in[lca]);
					treap.insert(newLcaVal, in[lca]);
				}
			}
			else
			{
				lca = getLCA(u, v);
				out.println(Math.max(query(lca, u), query(lca, v)));
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isAncestor(int u, int v) {
		if(dpth[v] < dpth[u]) return false;
		return getKthAnc(v, dpth[v] - dpth[u]) == u;
	}

	private static void shiftDown(int v, int lca, int valV) {
		int lcaHead = nxt[lca], vHead = nxt[v];
		int deleted = -1;
		if(dp[0][vHead] != -1)
			deleted = treap.query(in[dp[0][vHead]], in[dp[0][vHead]]);
		
		while(vHead != lcaHead)
		{
			treap.delete(in[v]);
			treap.insert(deleted, in[vHead]);
			v = dp[0][nxt[v]];
			vHead = nxt[v];
			if(dp[0][vHead] != -1)
				deleted = treap.query(in[dp[0][vHead]], in[dp[0][vHead]]);
		}
		if(valV > -1)
		{
			treap.delete(in[v]);
			treap.insert(valV, in[lca]);
		}
		else
		{
			int u = in[lca] + 1;
			if(u <= in[v])
			{
				int tmp = treap.query(in[lca], in[lca]);
				treap.delete(in[v]);
				treap.insert(tmp, in[vHead]);
			}
		}
	}

	private static void shiftUp(int v, int lca, int deleted) {
		int lcaHead = nxt[lca], vHead = nxt[v];
		int tmp;
		while(vHead != lcaHead)
		{
			tmp = treap.query(in[vHead], in[vHead]);
			treap.delete(in[vHead]);
			treap.insert(deleted, in[v]);
			deleted = tmp;
			v = dp[0][nxt[v]];
			vHead = nxt[v];
		}
		treap.delete(in[lca]);
		treap.insert(deleted, in[v]);
	}

	private static int query(int lca, int v) {
		int lcaHead = nxt[lca], vHead = nxt[v];
		
		int ans = 0;
		
		while(vHead != lcaHead)
		{
			ans = Math.max(ans, treap.query(in[vHead], in[v]));
			v = dp[0][nxt[v]];
			vHead = nxt[v];
		}
		
		ans = Math.max(ans, treap.query(in[lca], in[v]));
		
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
	
	static class ImpTreap {
	    
		Node root;
		static final int INF = (int) 2e9;
		static Random rand = new Random();
		
		class TwoNodes
		{
			Node left, right;
			
			public TwoNodes(Node l, Node r) {
				left = l;
				right = r;
			}
		}
		
		static class Node
		{
			int val, prior, sz, max;
			Node l, r;
			
			public Node(int v, int p) {
				val = v;
				prior = p;
				sz = 1;
				max = val;
			}
		}
		
		static int getSz(Node n) {
			return n == null?0:n.sz;
		}
		
		static void updateSz(Node n)
		{
			if(n != null)
			{
				n.sz = getSz(n.l) + 1 + getSz(n.r);
				n.max = Math.max(n.val, Math.max(getMax(n.l), getMax(n.r)));
			}
		}


		private static int getMax(Node node) {
			if(node == null) return 0;
			return node.max;
		}

		TwoNodes split(Node node, int pos, int add)
		{
			if(node == null)
				return new TwoNodes(null, null);
			int curPos = add + getSz(node.l);
			if(curPos <= pos)
			{
				TwoNodes tmp = split(node.r, pos, curPos + 1);
				node.r = tmp.left;
				updateSz(node);
				return new TwoNodes(node, tmp.right);
			}
			else
			{
				TwoNodes tmp = split(node.l, pos, add);
				node.l = tmp.right;
				updateSz(node);
				return new TwoNodes(tmp.left, node);
			}
		}
		
		Node merge(Node left, Node right)
		{
			if(left == null || right == null)
				return left == null?right:left;
			if(left.prior > right.prior)
			{
				left.r = merge(left.r, right);
				updateSz(left);
				return left;
			}
			else
			{
				right.l = merge(left, right.l);
				updateSz(right);
				return right;
			}
		}
		
		void insert(int x, int idx)
		{
			TwoNodes tmp = split(root, idx - 1, 0);
			root = merge(tmp.left, new Node(x, rand.nextInt(INF)));
			root = merge(root, tmp.right);
		}

		int delete(int idx)
		{
			int val = -1;
			TwoNodes left = split(root, idx - 1, 0);
			TwoNodes right = split(left.right, 0, 0);
			val = right.left.val;
			root = merge(left.left, right.right);
			
			return val;
		}

		int search(int key)
		{
			Node cur = root;
			int rank = 0;
			
			while(cur != null)
			{
				if(cur.val == key)
					return rank + getSz(cur.l) + 1;
				else if(cur.val < key)
				{
					rank += getSz(cur.l) + 1;
					cur = cur.r;
				}
				else
					cur = cur.l;
			}
			return rank;
		}
		
		static int P;
		
		int query(int L, int R)
		{
			int ans = 0;
			TwoNodes rangeL = split(root, L - 1, 0);
			TwoNodes rangeR = split(rangeL.right, R - L, 0);
			ans = rangeR.left.max;
			root = merge(rangeR.left, rangeR.right);
			root = merge(rangeL.left, root);
			return ans;
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