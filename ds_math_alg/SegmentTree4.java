package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SegmentTree4 {
	
	static ArrayList<Integer> adj[];
	static int [] pos, left, right;
	static final int MAX = 100_100;
	static int res[];
	static SegmentTree st;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = sc.nextInt(), u;
		res = new int[n + 1];
		adj = new ArrayList[n + 1];
		for(int i = 1; i <= n; ++i) adj[i] = new ArrayList<Integer>();
		st = new SegmentTree(MAX + 1);
		
		for(int v = 2; v <= n; ++v)
		{
			u = sc.nextInt();
			adj[u].add(v);
		}
		
		pos = new int[n + 1];
		left = new int[n + 1];
		right = new int[n + 1];
		
		int diff;
		
		pos[1] = 1;
		
		for(int i = 2; i <= n; ++i)
		{
			pos[i] = sc.nextInt();
			diff = sc.nextInt();
			left[i] = Math.max(0, pos[i] - diff);
			right[i] = Math.min(MAX, pos[i] + diff);
		}
		
		dfs(1);
		for(int i = 2; i <= n; ++i) out.println(res[i]);
		
		out.flush();
	}
	
	
	private static void dfs(int u) {
		res[u] = st.query(left[u], right[u]);
		st.updatePoint(pos[u], 1);
		for(int v : adj[u]) dfs(v);
		st.updatePoint(pos[u], -1);
	}


	static class SegmentTree {

		int n, sTree[];
		
		public SegmentTree(int N) {
			n = N;
			int sz = getSz(n);
			sTree = new int[sz];
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}
		
		void updatePoint(int idx, int val)
		{
			updatePoint(1, idx, 0, n - 1, val);
		}

		void updatePoint(int node, int idx, int b, int e, int val) {
			if(b == e) sTree[node] += val;
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
		
		int query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi);
		}

		private int query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return 0;
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				int left = query(node << 1, b, mid, lo, hi);
				int right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return left + right;
			}
		}
	}

	static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}