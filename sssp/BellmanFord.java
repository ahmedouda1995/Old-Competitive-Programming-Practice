package sssp;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

	static int V = 5, INF = (int) 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[V];
	static int dist[] = new int[V];
	
	public static void main(String[] args) {
		for(int i = 0; i < V; ++i) adj[i] = new ArrayList<Pair>();
		
		adj[0].add(new Pair(1, 1)); adj[0].add(new Pair(2, 10)); adj[1].add(new Pair(3, 2));
		adj[2].add(new Pair(3, -10)); adj[3].add(new Pair(4, 3));
		
		Arrays.fill(dist, INF);
		if(bellmanFord(0))
			System.out.println(Arrays.toString(dist));
		else
			System.out.println("Negative weigted cycle!");
	}
	
	private static boolean bellmanFord(int s) {
		
		dist[s] = 0;
		boolean modified = true;
		for(int i = 0; i < V - 1 && modified; ++i) {
			modified = false;
			for(int j = 0; j < V; ++j) {
				for(Pair p : adj[j]) {
					if(dist[j] + p.w < dist[p.v]) {
						dist[p.v] = dist[j] + p.w;
						modified = true;
					}
				}
			}
		}
		
		for(int i = 0; i < V; ++i) {
			for(Pair p : adj[i]) {
				if(dist[i] + p.w < dist[p.v]) return false;
			}
		}
		
		return true;
	}

	static class Pair { int v, w; Pair(int v, int w) { this.v = v; this.w = w; } }
}
