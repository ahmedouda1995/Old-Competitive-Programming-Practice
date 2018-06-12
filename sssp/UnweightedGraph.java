package sssp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UnweightedGraph {

	static int INF = (int) 1e9;
	static int V = 13;
	static int dist[] = new int[V];
	static int p[] = new int[V];
	static LinkedList<Integer> adj[] = new LinkedList[V];
	
	public static void main(String[] args) {
		
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Integer>();
		
		
		adj[0].add(1); adj[0].add(4);
		adj[1].add(0); adj[1].add(2); adj[1].add(5);
		adj[2].add(1); adj[2].add(3); adj[2].add(6);
		adj[3].add(2); adj[3].add(7);
		adj[4].add(0); adj[4].add(8);
		adj[5].add(1); adj[5].add(6); adj[5].add(10);
		adj[6].add(2); adj[6].add(5);
		adj[7].add(3); adj[7].add(12);
		adj[8].add(4); adj[8].add(9);
		adj[9].add(8); adj[9].add(10);
		adj[10].add(5); adj[10].add(9); adj[10].add(11);
		adj[11].add(6); adj[11].add(10); adj[11].add(12);
		adj[12].add(7); adj[12].add(11);
		
		Arrays.fill(dist, INF);
		int source = 5;
		dist[source] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(source);
		
		while(!q.isEmpty()) {
			int i = q.poll();
			
			for(int v : adj[i]) {
				if(dist[v] == INF) {
					q.offer(v);
					dist[v] = dist[i] + 1;
					p[v] = i;
				}
			}
		}
		
		printPath(7, 5);
		System.out.println();
		System.out.println("dist = " + dist[7]);
	}

	private static void printPath(int i, int s) {
		if(i == s) { System.out.print(i); return; }
		else
			printPath(p[i], s);
		System.out.print(" " + i);
	}
}
