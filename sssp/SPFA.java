package sssp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SPFA {

	static int V = 5, INF = (int) 1e9;
	static ArrayList<Pair> adj[] = new ArrayList[V];
	static int dist[] = new int[V];
	static int inQTimes[] = new int[V];
	static boolean inQ[] = new boolean[V];
	
	public static void main(String[] args) {
		for(int i = 0; i < V; ++i) adj[i] = new ArrayList<Pair>();
		
		adj[1].add(new Pair(0, 1)); adj[0].add(new Pair(2, 10)); adj[3].add(new Pair(1, 2));
		adj[2].add(new Pair(3, -10)); adj[3].add(new Pair(4, 3));
		
		Arrays.fill(dist, INF);
		if(spfa(0))
			System.out.println(Arrays.toString(dist));
		else
			System.out.println("Negative weigted cycle!");
	}

	private static boolean spfa(int s) {
		dist[s] = 0;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s); inQ[s] = true; inQTimes[s]++;
		
		while(!q.isEmpty()) {
			int u = q.poll(); inQ[u] = false;
			
			for(Pair p : adj[u]) {
				if(dist[u] + p.w < dist[p.v]) {
					dist[p.v] = dist[u] + p.w;
					
					if(!inQ[p.v]) {
						q.offer(p.v);
						inQ[p.v] = true;
						inQTimes[p.v]++;
						if(inQTimes[p.v] > V - 1)
							return false;
					}
				}
			}
		}
		return true;
	}
	
	static class Pair { int v, w; Pair(int v, int w) { this.v = v; this.w = w; } }
}
