package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_1112_MiceandMaze {

	static int INF = (int) 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[100];
	static int dist[] = new int[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, e, time, m;
		
		while(t-- > 0) {
			n = sc.nextInt(); e = sc.nextInt() - 1; time = sc.nextInt();
			for(int i = 0; i < n; ++i) {
				adj[i] = new ArrayList<Pair>();
				dist[i] = INF;
			}
			
			m = sc.nextInt();
			int a, b;
			for(int i = 0; i < m; ++i) {
				a = sc.nextInt() - 1; b = sc.nextInt() - 1;
				adj[b].add(new Pair(a, sc.nextInt()));
			}
			
			dijkstra(e);
			int res = 0;
			for(int i = 0; i < n; ++i) {
				if(dist[i] <= time) res++;
			}
			out.println(res);
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static void dijkstra(int s) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		dist[s] = 0; pq.offer(new Pair(s, 0));
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			
			if(u.y > dist[u.x]) continue;
			
			for(Pair e : adj[u.x]) {
				if(dist[u.x] + e.y < dist[e.x]) {
					dist[e.x] = dist[u.x] + e.y;
					pq.offer(new Pair(e.x, dist[e.x]));
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
			if(Integer.compare(this.y, p.y) == 0)
				return Integer.compare(this.x, p.x);
			return Integer.compare(this.y, p.y);
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