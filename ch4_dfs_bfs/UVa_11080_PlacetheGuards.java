package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_11080_PlacetheGuards {

	static LinkedList<Integer> adj[] = new LinkedList[200];
	static int vis[] = new int[200];
	static int ones, zeros;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), v, e;
		
		while(t-- > 0) {
			v = sc.nextInt(); e = sc.nextInt();
			for(int i = 0; i < v; ++i) {
				adj[i] = new LinkedList<Integer>();
				vis[i] = -1;
			}
			for(int i = 0; i < e; ++i) {
				int a = sc.nextInt(), b = sc.nextInt();
				adj[a].add(b); adj[b].add(a); 
			}
			for(int i = 0; i < v; ++i) vis[i] = -1;
			int res = 0;
			boolean printed = false;
			for(int i = 0; i < v; ++i) {
				if(vis[i] == -1) {
					zeros = 0; ones = 0;
					if(isBipartite(i, 0)) {
						if(ones == 0)
							res += zeros;
						else
							res += Math.min(zeros, ones);
					}
					else {
						out.println(-1);
						printed = true;
						break;
					}
				}
			}
			if(!printed)
				out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isBipartite(int i, int c) {
		vis[i] = c;
		if(c == 1) ones++; else zeros++;
		for(int v : adj[i]) {
			if(vis[v] == -1) {
				if(!isBipartite(v, 1 - c))
					return false;
			}
			else if(vis[v] == vis[i])
				return false;
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