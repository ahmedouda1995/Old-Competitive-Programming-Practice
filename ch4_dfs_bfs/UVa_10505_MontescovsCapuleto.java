package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_10505_MontescovsCapuleto {

	static LinkedList<Integer> adj[] = new LinkedList[201];
	static int vis[] = new int[201];
	static boolean isBipartite;
	static int zeros, ones;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n;
		
		while(t-- > 0) {
			n = sc.nextInt();
			for(int i = 1; i <= n; ++i) {
				adj[i] = new LinkedList<Integer>();
				vis[i] = -1;
			}
			for(int i = 1; i <= n; ++i) {
				int m = sc.nextInt();
				for(int j = 0; j < m; ++j) {
					int p = sc.nextInt();
					if(p <= n && p > 0) {
						adj[i].add(p);
						adj[p].add(i);
					}
				}
			}

			int res = 0;
			
			for(int i = 1; i <= n; ++i) {
				zeros = 0; ones = 0;
				isBipartite = true;
				if(vis[i] == -1) {
					dfs(i, 0);
					if(isBipartite) {
						res += Math.max(ones, zeros);
					}
					else {
						zeros = 0; ones = 0;
					}
				}
			}
			
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i, int c) {
		vis[i] = c;
		if(c == 0) zeros++; else ones++;
		for(int v : adj[i]) {
			if(vis[v] == -1) {
				dfs(v, 1 - c);
			}
			else {
				if(vis[v] == vis[i])
					isBipartite = false;
			}
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