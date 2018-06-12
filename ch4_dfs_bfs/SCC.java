package ch4_dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;

public class SCC {
	
	static int V = 8;
	static LinkedList<Integer> adj[] = new LinkedList[V];
	static ArrayList<Integer> S = new ArrayList<Integer>();
	static int dfsNumberCounter = 0, numSCC = 0;
	static boolean vis[] = new boolean[V];
	static int dfs_num[] = new int[V];
	static int dfs_low[] = new int[V];

	public static void main(String[] args) {
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Integer>();
		// DIRECTED GRAPH
		adj[0].add(1); adj[1].add(3); adj[3].add(2); adj[2].add(1); adj[3].add(4);
		adj[4].add(5); adj[5].add(7); adj[7].add(6); adj[6].add(4);
		
		//adj[0].add(1); adj[0].add(2); adj[2].add(3); adj[4].add(2);
		
		for (int i = 0; i < V; i++)
			if (dfs_num[i] == 0)
				tarjanSCC(i);
	}

	private static void tarjanSCC(int i) {
		dfs_low[i] = dfs_num[i] = ++dfsNumberCounter;
		vis[i] = true;
		S.add(i);
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				tarjanSCC(v);
			}
			if (vis[v]) {
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
		}
		
		if (dfs_low[i] == dfs_num[i]) {
			System.out.printf("SCC %d:", ++numSCC);
			
			while(true) {
				int v = S.get(S.size() - 1); S.remove(S.size() - 1); vis[v] = false;
				System.out.printf(" %d", v);
				if (i == v) break;
			}
			System.out.println();
		}
	}
}
