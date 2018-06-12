package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_12047_HighestPaidToll {

	static int P, INF = (int) 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[10001];
	static ArrayList<Pair> adjRev[] = new ArrayList[10001];
	static int dist[];
	static int distRev[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt(), n, m, s, t;
		
		while(tc-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			s = sc.nextInt(); t = sc.nextInt();
			P = sc.nextInt();
			
			dist = new int[n + 1]; distRev = new int[n + 1];
			
			for(int i = 1; i <= n; ++i) {
				adj[i] = new ArrayList<Pair>();
				adjRev[i] = new ArrayList<Pair>();
				dist[i] = INF; distRev[i] = INF;
			}
			
			int u, v, c;
			
			for(int i = 0; i < m; ++i) {
				u = sc.nextInt(); v = sc.nextInt(); c = sc.nextInt();
				adj[u].add(new Pair(v, c));
				adjRev[v].add(new Pair(u, c));
			}
			
			dijkstra(s, false); dijkstra(t, true);
			
			int max = -1;
			
			for(int i = 1; i <= n; ++i) {
				for(Pair edge : adj[i]) {
					if(edge.y > max && dist[i] + edge.y + distRev[edge.x] <= P) {
						max = edge.y;
					}
				}
			}
			
			out.println(max);
		}
		
		out.flush();
		out.close();
	}

	private static void dijkstra(int src, boolean isRev) {
		ArrayList<Pair> a[]; int d[];
		if(isRev) {	a = adjRev; d = distRev; }
		else { a = adj; d = dist; }
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		d[src] = 0; pq.offer(new Pair(src, 0));
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			
			if(u.y > d[u.x]) continue;
			
			for(Pair v : a[u.x]) {
				if(d[v.x] > d[u.x] + v.y) {
					d[v.x] = d[u.x] + v.y;
					pq.offer(new Pair(v.x, d[v.x]));
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
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public int compareTo(Pair p) {
			return Integer.compare(y, p.y);
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