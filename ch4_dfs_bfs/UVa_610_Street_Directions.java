package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_610_Street_Directions {
	
	static LinkedList<Integer> adj[] = new LinkedList[1001];
	static int dfs_num[] = new int[1001];
	static int dfs_low[] = new int[1001];
	static int parent[] = new int[1001];
	static int dfsNumberCounter;
	static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int n, m, cases = 1;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			for(int i = 1; i <= n; ++i) {
				adj[i] = new LinkedList<Integer>();
				dfs_num[i] = dfs_low[i] = parent[i] = 0;
			}
			int a, b;
			for(int i = 0; i < m; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				adj[a].add(b); adj[b].add(a);
			}
			dfsNumberCounter = 0;
			out.println(cases++);
			out.println();
			for(int i = 1; i <= n; ++i) {
				if(dfs_num[i] == 0) {
					bridges(i);
				}
			}
			out.println("#");
		}
		
		out.flush();
		out.close();
	}
	
	private static void bridges(int i) {
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				parent[v] = i;
				bridges(v);
				
				if(dfs_num[i] < dfs_low[v]) {
					out.println(i + " " + v);
					out.println(v + " " + i);
				}
				else {
					out.println(i + " " + v);
				}
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
			else if(v != parent[i]) {
				if(dfs_num[v] < dfs_num[i])
					out.println(i + " " + v);
				dfs_low[i] = Math.min(dfs_low[i], dfs_num[v]);
			}
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
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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