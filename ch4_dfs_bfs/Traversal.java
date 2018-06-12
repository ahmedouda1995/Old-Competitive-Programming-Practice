package ch4_dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {

	@SuppressWarnings("unchecked")
	static LinkedList<Integer> adj[] = new LinkedList[13];
	static final int INF = (int) 1e9;
	static int d[] = new int[13];
	static boolean vis[] = new boolean[13];
	
	public static void main(String[] args) {
		for(int i = 0; i < 13; ++i) adj[i] = new LinkedList<Integer>();
		Arrays.fill(d, INF);
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
		
		dfs(0);
		System.out.println(Arrays.toString(vis));
		//bfs(5);
		//traverse();
		System.out.println(Arrays.toString(d));
	}

	private static void dfs(int i) {
		vis[i] = true;
		for(int v : adj[i]){
			if(!vis[v]) {
				dfs(v);
			}
		}
	}
	
	private static void bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		d[i] = 0;
		while(!q.isEmpty()) {
			int curr = q.poll();
			//System.out.println(curr);
			for(int v : adj[curr]) {
				if(d[v] == INF) {
					d[v] = d[curr] + 1;
					q.offer(v);
				}
			}
		}
	}
	
	public static void traverse() { for(int i = 0; i < adj.length; ++i) if(d[i] == INF) bfs(i); }
}
