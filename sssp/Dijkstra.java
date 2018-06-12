package sssp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra {

	static int INF = (int) 1e9;
	static int V = 5;
	static int dist[] = new int[V];
	static int parent[] = new int[V];
	static LinkedList<Edge> adj[] = new LinkedList[V];
	
	public static void main(String[] args) {
		
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Edge>();
		
		adj[0].add(new Edge(1, 0)); adj[0].add(new Edge(2, -1)); adj[0].add(new Edge(3, -1));
		adj[2].add(new Edge(1, -2)); adj[3].add(new Edge(2, -2)); adj[3].add(new Edge(1, -5));
		
		Arrays.fill(dist, INF);
		int s = 0;
		dist[s] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(s, 0));
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			if(p.d > dist[p.v]) continue;
			for(Edge e : adj[p.v]) {
				if (dist[p.v] + e.w < dist[e.v]) {
					dist[e.v] = dist[p.v] + e.w;
					parent[e.v] = p.v;
					pq.add(new Pair(e.v, dist[e.v]));
				}
			}
		}
		System.out.println(Arrays.toString(dist));
	}
	
	static class Pair implements Comparable<Pair>{
		int v;
		int d;
		
		public Pair(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.d, p.d) == 0)
				return Integer.compare(this.v, p.v);
			return Integer.compare(this.d, p.d);
		}
	}
	
	static class Edge {
		int v;
		int w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
