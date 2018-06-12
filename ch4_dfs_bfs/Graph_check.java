package ch4_dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;

public class Graph_check {

	static int V = 8;
	static final int UNVISITED = -1, EXPLORED = 1, VISITED = 2;
	static int vis[] = new int[V];
	static LinkedList<Integer> adj[] = new LinkedList[V];
	static int parent[] = new int[V];
	
	public static void main(String[] args) {
		
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Integer>();
		Arrays.fill(vis, UNVISITED);
		
		// DIRECTED GRAPH
		adj[0].add(1); adj[1].add(3); adj[3].add(2); adj[2].add(1); adj[3].add(4);
		adj[4].add(5); adj[5].add(7); adj[7].add(6); adj[6].add(4);
		
		// UNDIRECTED GRAPH
//		adj[0].add(1); adj[1].add(2); adj[1].add(3); adj[1].add(0); adj[2].add(1);
//		adj[2].add(3); adj[3].add(1); adj[3].add(4); adj[4].add(3); adj[6].add(7);
//		adj[6].add(8); adj[7].add(6); adj[8].add(6);
		
		int numComp = 0;
		for(int i = 0; i < V; ++i) {
			if(vis[i] == UNVISITED) {
				System.out.printf("Component %d:\n", ++numComp); graphCheck(i);
				System.out.println();
			}
		}
	}

	private static void graphCheck(int i) {
		vis[i] = EXPLORED;
		
		for(int v : adj[i]) {
			if(vis[v] == UNVISITED) {
				System.out.println("Tree Edge: " + i + " -> " + v);
				parent[v] = i;
				graphCheck(v);
			}
			else if(vis[v] == EXPLORED){
				if(parent[i] == v)
					System.out.println("Bidirectional Edge: " + i + " -> " + v);
				else
					System.out.println("Back Edge: " + i + " -> " + v);
			}
			else if(vis[v] == VISITED){
				System.out.println("Forward/Cross Edge: " + i + " -> " + v);
			}
		}
		vis[i] = VISITED;
	}
}
