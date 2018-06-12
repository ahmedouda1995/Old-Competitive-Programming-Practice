package ch4_dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;

public class ArticulationPoints_and_Bridges {

	// UNDIRECTED GRAPH
	static int V = 9, dfsNumberCounter;
	static LinkedList<Integer> adj[] = new LinkedList[V];
	static int dfs_num[] = new int[V];
	static int dfs_low[] = new int[V];
	static boolean ap[] = new boolean[V];
	static int parent[] = new int[V];
	static int dfs_root, root_children;
	
	public static void main(String[] args) {
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Integer>();
		
		adj[0].add(1); adj[1].add(2); adj[1].add(3); adj[2].add(3); adj[3].add(4);
		adj[1].add(0); adj[2].add(1); adj[3].add(1); adj[3].add(2); adj[4].add(3);
		adj[6].add(7); adj[6].add(8); adj[7].add(6); adj[8].add(6);
		
		for(int i = 0; i < V; ++i) {
			if(dfs_num[i] == 0) {
				dfs_root = i;
				root_children = 0;
				articulationPointAndBridge(i);
				ap[dfs_root] = (root_children > 1);
			}
		}
		
		System.out.printf("Articulation Points:\n");
		for (int i = 0; i < V; i++)
			if (ap[i]) {
				System.out.printf("Vertex %d\n", i);
			}
	}

	private static void articulationPointAndBridge(int i) {
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				parent[v] = i;
				if(i == dfs_root) root_children++;
				articulationPointAndBridge(v);
				if(dfs_low[v] >= dfs_num[i]) {
					ap[i] = true;
				}
				if(dfs_low[v] > dfs_num[i]) {
					System.out.println("bridge from " + i + " to " + v);
				}
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
			else if(v != parent[i]){
				dfs_low[i] = Math.min(dfs_low[i], dfs_num[v]);
			}
		}
	}
}
