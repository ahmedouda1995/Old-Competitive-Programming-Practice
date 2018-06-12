package graphs;

import java.io.PrintWriter;
import java.util.*;

public class DFS {

	static LinkedList<Integer> adj[];
	static boolean vis[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int v = sc.nextInt();  // no. of vertices
		vis = new boolean[v];
		adj = new LinkedList[v];
		for (int i = 0; i < adj.length; i++) adj[i] = new LinkedList<Integer>();
		
		int e = sc.nextInt();
		while(e-- > 0) adj[sc.nextInt()].add(sc.nextInt());
		sc.close();
		
		
//		for (int i = 0; i < adj.length; i++) {
//			System.out.println(adj[i]);
//		}
		
		dfsUtil(3);
		
		//dfs();
		
		out.flush();
		out.close();
	}
	
	private static void dfs() {
		for (int i = 0; i < adj.length; i++) {
			if(!vis[i])
				dfsUtil(i);
		}
		
	}

	public static void dfsUtil(int i){
		vis[i] = true;
		System.out.println(i);
		for (int j : adj[i]) {
			if(!vis[j])
				dfsUtil(j);
		}
	}
}
