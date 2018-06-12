package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_11396_ClawDecomposition {

	static LinkedList<Integer> adj[] = new LinkedList[301];
	static int vis[] = new int[301];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((n = sc.nextInt()) != 0) {
			int a, b;
			for(int i = 1; i <= n; ++i) {
				adj[i] = new LinkedList<Integer>();
				vis[i] = -1;
			}
			while((a = sc.nextInt()) != 0 | (b = sc.nextInt()) != 0) {
				adj[a].add(b); adj[b].add(a);
			}
			
			boolean isBipartite = true;
			
			for(int i = 1; i <= n && isBipartite; ++i) {
			if(vis[i] == -1)
				if(!dfs(i, 0))
					isBipartite = false;
			}
			
			if(isBipartite)
				out.println("YES");
			else
				out.println("NO");
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean dfs(int i, int c) {
		vis[i] = c;
		
		for(int v : adj[i]) {
			if(vis[v] == -1) {
				if(!dfs(v, 1 - c))
					return false;
			}
			else {
				if(vis[v] == vis[i])
					return false;
			}
		}
		return true;
	}
	
	public static int gcd(int n, int m) {
	    return (n % m) == 0? m : gcd(m, n % m);
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