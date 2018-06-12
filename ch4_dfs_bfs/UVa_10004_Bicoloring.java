package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_10004_Bicoloring {

	static LinkedList<Integer> adj[] = new LinkedList[200];
	static int vis[] = new int[200];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, l;
		
		while((n = sc.nextInt()) != 0) {
			for(int i = 0; i < n; ++i) {
				vis[i] = -1;
				adj[i] = new LinkedList<Integer>();
			}
			l = sc.nextInt();
			int a, b;
			for(int i = 0; i < l; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				adj[a].add(b); adj[b].add(a);
			}
			
			if(isBipartite())
				out.println("BICOLORABLE.");
			else
				out.println("NOT BICOLORABLE.");
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isBipartite() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0); vis[0] = -1;
		
		while(!q.isEmpty()) {
			int i = q.poll();
			for(int v : adj[i]) {
				if(vis[v] == -1) { q.add(v); vis[v] = 1 - vis[i]; }
				else if(vis[v] == vis[i]) return false;
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