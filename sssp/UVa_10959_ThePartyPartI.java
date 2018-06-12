package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_10959_ThePartyPartI {

	static ArrayList<Integer> adj[] = new ArrayList[1000];
	static int INF = (int) 1e9;
	static int dist[] = new int[1000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), p, d;
		
		while(t-- > 0) {
			p = sc.nextInt(); d = sc.nextInt();
			
			for(int i = 0; i < p; ++i) {
				adj[i] = new ArrayList<Integer>();
				dist[i] = INF;
			}
			int a, b;
			for(int i = 0; i < d; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				adj[a].add(b); adj[b].add(a);
			}
			
			bfs(0);
			for(int i = 1; i < p; ++i) out.println(dist[i]);
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int src) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(src); dist[src] = 0;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					dist[v] = dist[u] + 1;
					q.offer(v);
				}
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