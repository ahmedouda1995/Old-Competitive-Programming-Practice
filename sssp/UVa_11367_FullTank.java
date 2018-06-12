package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// VERY VERY IMPORTANT State-Space graph

public class UVa_11367_FullTank {

	static int N;
	static int INF = (int) 1e9;
	static LinkedList<Edge> adj[];
	static int price[] = new int[1000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		N = sc.nextInt();
		int m = sc.nextInt();
		adj = new LinkedList[N];
		for(int i = 0; i < N; ++i) adj[i] = new LinkedList<Edge>();
		for(int i = 0; i < N; ++i) price[i] = sc.nextInt();
		
		for(int i = 0; i < m; ++i){
			int u = sc.nextInt(), v = sc.nextInt(), d = sc.nextInt();
			adj[u].add(new Edge(v, d));
			adj[v].add(new Edge(u, d));
		}

		int q = sc.nextInt(), c, s, e;
		for(int i = 0; i < q; ++i) {
			c = sc.nextInt(); s = sc.nextInt(); e = sc.nextInt();
			int res = dijkstra(s, e, c);
			if(res == INF)
				out.println("impossible");
			else
				out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	public static int dijkstra(int st, int end, int capacity) {
		int dist[][] = new int[N][capacity + 1];
		for(int i = 0; i < N; ++i) Arrays.fill(dist[i], INF);
		
		PriorityQueue<Triple> pq = new PriorityQueue<Triple>();
		dist[st][0] = 0;
		pq.add(new Triple(st, 0, 0));
		
		while(!pq.isEmpty()) {
			Triple u = pq.poll();
			
			if(u.cost > dist[u.city][u.fuel]) continue;
			
			for(Edge e : adj[u.city]) {
				if(e.dist <= u.fuel && u.cost < dist[e.city][u.fuel - e.dist]) {
					dist[e.city][u.fuel - e.dist] = u.cost;
					if(e.city == end && dist[e.city][0] != INF) return dist[end][0];
					pq.add(new Triple(e.city, u.fuel - e.dist, u.cost));
				}
			}
			
			if(u.fuel < capacity && u.cost + price[u.city] < dist[u.city][u.fuel + 1]) {
				dist[u.city][u.fuel + 1] = u.cost + price[u.city];
				pq.add(new Triple(u.city, u.fuel + 1, u.cost + price[u.city]));
			}
		}
		return dist[end][0];
	}

	static class Edge {
		int city, dist;
		
		Edge(int city, int dist) { this.city = city; this.dist = dist; }
	}
	
	static class Triple implements Comparable<Triple>{
		int city, fuel, cost;
		
		public Triple(int city, int fuel, int cost) {
			this.city = city; this.fuel = fuel; this.cost = cost;
		}
		
		@Override
		public int compareTo(Triple t) {
			return Integer.compare(this.cost, t.cost);
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