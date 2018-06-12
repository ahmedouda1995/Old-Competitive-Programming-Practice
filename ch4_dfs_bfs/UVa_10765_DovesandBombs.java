package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_10765_DovesandBombs {

	static int V = 10000, dfsNumberCounter;
	static LinkedList<Integer> adj[] = new LinkedList[V];
	static int dfs_num[] = new int[V];
	static int dfs_low[] = new int[V];
	static int ap[] = new int[V];
	static int parent[] = new int[V];
	static int dfs_root, root_children, N;
	static boolean vis[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			
			for(int i = 0; i < n; ++i) {
				adj[i] = new LinkedList<Integer>();
				dfs_num[i] = dfs_low[i] = parent[i] = 0;
				ap[i] = 0;
			}
			
			int a, b;
			
			while((a = sc.nextInt()) != -1 | (b = sc.nextInt()) != -1) {
				adj[a].add(b); adj[b].add(a);
			}
			
			dfsNumberCounter = 0;
			ArrayList<Pair> res = new ArrayList<Pair>();
			N = n;
			for(int i = 0; i < n; ++i) {
				if(dfs_num[i] == 0) {
					dfs_root = i; root_children = 0;
					articulationPoints(i);
					ap[i] = (root_children > 1)?root_children - 1:0;
				}
			}
			
			for(int i = 0; i < n; ++i) {
				ap[i]++;
				res.add(new Pair(i, ap[i]));
			}
			
			Collections.sort(res);
			int i;
			for(i = 0; i < m; ++i) {
				out.println(res.get(i).x + " " + res.get(i).y);
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
//	private static int cc(int i) {
//		int res = 0;
//		vis = new boolean[N];
//		vis[i] = true;
//		for(int j = 0; j < N; ++j) {
//			if(!vis[j]) {
//				res ++;
//				dfs(j);
//			}
//		}
//		return res;
//	}
//
//	private static void dfs(int i) {
//		vis[i] = true;
//		for(int v : adj[i]) {
//			if(!vis[v])
//				dfs(v);
//		}
//	}

	private static void articulationPoints(int i) {
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				if(i == dfs_root) root_children++;
				parent[v] = i;
				articulationPoints(v);
				if(dfs_num[i] <= dfs_low[v])
					ap[i]++;
				
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
			else if(v != parent[i])
				dfs_low[i] = Math.min(dfs_low[i], dfs_num[v]);
		}
	}

	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(p.y, this.y) == 0)
				return Integer.compare(this.x, p.x);
			return Integer.compare(p.y, this.y);
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