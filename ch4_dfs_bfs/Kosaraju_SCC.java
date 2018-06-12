package ch4_dfs_bfs;

import java.util.LinkedList;
import java.util.Stack;

public class Kosaraju_SCC {

	static int V = 8;
	static LinkedList<Integer> adj[] = new LinkedList[V];
	static LinkedList<Integer> adjR[] = new LinkedList[V];
	static Stack<Integer> stackPostOrder = new Stack<Integer>();
	static boolean vis[] = new boolean[V];
	
	public static void main(String[] args) {
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Integer>();
		adj[0].add(1); adj[1].add(3); adj[3].add(2); adj[2].add(1); adj[3].add(4);
		adj[4].add(5); adj[5].add(7); adj[7].add(6); adj[6].add(4);
		
		for(int i = 0; i < V; ++i) adjR[i] = new LinkedList<Integer>();
		adjR[1].add(0); adjR[3].add(1); adjR[2].add(3); adjR[1].add(2); adjR[4].add(3);
		adjR[5].add(4); adjR[7].add(5); adjR[6].add(7); adjR[4].add(6);
		
		for(int i = 0; i < V; ++i) {
			if(!vis[i])
				kosaraju1(i);
		}
		System.out.println(stackPostOrder);
		int numSCC = 0;

		while(!stackPostOrder.isEmpty()) {
			int i = stackPostOrder.pop();
			if(vis[i]) {
				numSCC++;
				System.out.println(numSCC + ":");
				kosaraju2(i);
				System.out.println();
			}
		}
	}

	private static void kosaraju2(int i) {
		vis[i] = false;
		System.out.print(i + " ");
		for(int v : adjR[i]) {
			if(vis[v])
				kosaraju2(v);
		}
	}

	private static void kosaraju1(int i) {
		vis[i] = true;
		for(int v : adj[i]) {
			if(!vis[v])
				kosaraju1(v);
		}
		stackPostOrder.push(i);
	}
}
