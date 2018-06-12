package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_318_DominoEffect {

	static LinkedList<Pair> adj[] = new LinkedList[500];
	static int vis[] = new int[500];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, systems = 1;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			Arrays.fill(vis, (int) 1e9);
			for(int i = 1; i <= n; ++i) adj[i] = new LinkedList<Pair>();
			for(int i = 1; i <= m; ++i) {
				int a = sc.nextInt(), b = sc.nextInt(), w = sc.nextInt();
				adj[a].add(new Pair(b, w)); adj[b].add(new Pair(a, w));
			}
			
			solve();
			double max = 0;
			int a = 1, b = -1; 
			
			for(int i = 1; i <= n; ++i) {
				
				for(Pair p : adj[i]) {
					if((vis[i] - vis[p.v] >= p.w && vis[i] > max)) {
						max = vis[i]; a = i; b = -1;
					}
					else if(vis[p.v] - vis[i] >= p.w && vis[p.v] > max) {
						max = vis[p.v]; a = p.v; b = -1;
					}
					else {
						double t = (p.w - Math.abs(vis[p.v] - vis[i])) / 2.0;
						double larger = (vis[i] > vis[p.v])?vis[i]:vis[p.v];
						if(larger + t > max) {
							max = larger + t; a = i; b = p.v;
						}
						
					}
				}
			}
			out.printf("System #%d\n", systems++);
			if(b == -1)
				out.printf("The last domino falls after %.1f seconds, at key domino %d.\n", max, a);
			else
				out.printf("The last domino falls after %.1f seconds, between key dominoes %d and %d.\n", max, a, b);
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		vis[1] = 0;
		
		while(!q.isEmpty()) {
			int V = q.poll();
			
			for(Pair e : adj[V]) {
				if(vis[e.v] > vis[V] + e.w) {
					vis[e.v] = vis[V] + e.w;
					q.offer(e.v);
				}
			}
		}
	}

	static class Pair {
		int v;
		int w;
		
		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
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