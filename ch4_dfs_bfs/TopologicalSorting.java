package ch4_dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSorting {

	// kahn
	// print all topsorts
	
	static LinkedList<Integer> adj[] = new LinkedList[4];
	static Stack<Integer> s = new Stack<Integer>();
	static boolean vis[] = new boolean[4];
	
	
	public static void main(String[] args) {
		for(int i = 0; i < 4; ++i) adj[i] = new LinkedList<Integer>();
		
		adj[0].add(1); adj[2].add(1); adj[1].add(3);
		
		for(int i = 0; i < 4; ++i) {
			if(!vis[i])
				dfs(i);
		}
		
		while(!s.isEmpty()) System.out.print(s.pop() + " ");
		System.out.println();
		kahn();
		System.out.println("------------------------------------");
		alltopologicalSort();
	}

	//STANDARD
	private static void dfs(int i) {
		vis[i] = true;
		for(int v : adj[i])
			if(!vis[v])
				dfs(v);
		s.push(i);
	}
	
	//KAHN
	public static void kahn() {
		int indegree[] = new int[4];
		for(int i = 0; i < 4; ++i) for(int v : adj[i]) indegree[v]++;
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < 4; ++i) if(indegree[i] == 0) a.add(i);
		
		for(int i = 0; i < a.size(); ++i) {
			for(int v : adj[a.get(i)])
				if(--indegree[v] == 0)
					a.add(v);
		}
		if(a.size() == 4)
			System.out.println(a);
		else
			System.out.println("There exists a cycle in the graph");
	}
	
	// ALL TOPOSORTS
	public static void alltopologicalSort() {
		boolean visited[] = new boolean[4];
		ArrayList<Integer> res = new ArrayList<Integer>();
		int [] indegree = new int[4];
		for(int i = 0; i < 4; ++i) for(int v : adj[i]) indegree[v]++;
		alltopoSortsUtil(res, visited, indegree);
	}

	private static void alltopoSortsUtil(ArrayList<Integer> res, boolean[] visited, int indegree[]) {
		boolean flag = false;
		
		for (int i = 0; i < 4; i++) {
			if (indegree[i] == 0 && !visited[i]) {
				for(int v : adj[i]) indegree[v]--;
				res.add(i);
				visited[i] = true;
				alltopoSortsUtil(res, visited, indegree);
				
				visited[i] = false;
				res.remove(res.size() - 1);
				for(int v : adj[i]) indegree[v]++;
				flag = true;
			}
		}
		
		if (!flag) {
			if(res.size() == 4)
				System.out.println(res);
			else
				System.out.println("There exists a cycle in the graph");
		}
	}
}
