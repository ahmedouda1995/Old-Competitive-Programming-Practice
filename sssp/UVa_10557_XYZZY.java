package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10557_XYZZY {

	static boolean vis[] = new boolean[100];
	static int N, INF = (int) 1e9, test;
	static ArrayList<Pair> adj[] = new ArrayList[100];
	static int dist[] = new int[100];
	static int a[] = new int[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while((N = sc.nextInt()) != -1) {
			for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Pair>();
			Arrays.fill(dist, INF);
			
			for(int i = 0; i < N; ++i) {
				a[i] = sc.nextInt();
				int k = sc.nextInt();
				while(k-- > 0) {
					adj[i].add(new Pair(sc.nextInt() - 1, -1));
				}
			}
			
			for(int i = 0; i < N; ++i) {
				for(Pair e : adj[i]) {
					e.y = -a[e.x];
				}
			}
			test = -1;
			if(bellmanFord()) {
				Arrays.fill(vis, false);
				dfs(0, N - 1);
				if(vis[N - 1]) {
					Arrays.fill(vis, false);
					dfs(test, N - 1);
					if(vis[N - 1] || dist[N - 1] != INF)
						out.println("winnable");
					else
						out.println("hopeless");
				}
				else
					out.println("hopeless");
			}
			else {
				if(dist[N - 1] == INF)
					out.println("hopeless");
				else
					out.println("winnable");
			}
		}
		
		out.flush();
		out.close();
	}
	
	public static void dfs(int i, int j) {
		vis[i] = true;
		for(Pair e : adj[i]) {
			if(!vis[e.x]) {
				dfs(e.x, j);
			}
		}
	}
	
	private static boolean bellmanFord() {
		
		dist[0] = -100;
		
		for(int i = 0; i < N - 1; ++i) {
			for(int j = 0; j < N; ++j) {
				for(Pair e : adj[j]) {
					if(dist[j] != INF && dist[j] < 0 && dist[j] + e.y < dist[e.x]) {
						dist[e.x] = dist[j] + e.y;
					}
				}
			}
		}
		
		for(int j = 0; j < N; ++j) {
			for(Pair e : adj[j]) {
				if(dist[j] != INF && dist[j] < 0 && dist[j] + e.y < dist[e.x]) {
					test = e.x;
					return true;
				}
			}
		}
		
		return false;
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