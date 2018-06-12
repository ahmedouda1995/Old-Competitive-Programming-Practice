package ch4_dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite_graph_check {

	static LinkedList<Integer> adj[] = new LinkedList[4];
	static int vis[] = new int[4];
	
	public static void main(String[] args) {
		Arrays.fill(vis, -1);
		for(int i = 0; i < 4; ++i) adj[i] = new LinkedList<Integer>();
		
		adj[0].add(1); adj[1].add(3); adj[2].add(3);
		adj[1].add(0); adj[3].add(1); adj[3].add(2);
		
		boolean isBipartite = true;
		
		for(int i = 0; i < 4 && isBipartite; ++i) {
			if(vis[i] == -1)
				if(!dfs(i, 0))
					isBipartite = false;
		}
//		for(int i = 0; i < 4 && isBipartite; ++i) {
//			if(vis[i] == -1)
//				if(!bfs(i))
//					isBipartite = false;
//		}
		
		if(isBipartite)
			System.out.println("bipartite");
		else
			System.out.println("is not bipartite");
	}

	private static boolean bfs(int j) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(j);
		vis[j] = 0;
		
		while(!q.isEmpty()) {
			int i = q.poll();
			for(int v : adj[i])
				if(vis[v] == - 1) {
					q.add(v);
					vis[v] = 1 - vis[i];
				}
				else if(vis[v] == vis[i])
					return false;
		}
		return true;
	}

	private static boolean dfs(int i, int c) {
		vis[i] = c;
		
		for(int v : adj[i]) {
			if(vis[v] == -1) {
				if(!dfs(v, 1 - c))
					return false;
			}
			else {
				if(vis[v] == vis[i])
					return false;
			}
		}
		return true;
	}
}
