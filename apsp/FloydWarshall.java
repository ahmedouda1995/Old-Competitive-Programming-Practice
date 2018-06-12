package apsp;

import java.util.Arrays;

public class FloydWarshall {

	static int V = 5, INF = (int) 1e9;
	static int p[][] = new int[V][V];
	static int adj[][] = {{0, 2, 1, INF, 3},
		   				  {INF, 0, INF, 4, INF},
						  {INF, 1, 0, INF, 1},
						  {1, INF, 3, 0, 5},
						  {INF, INF, INF, INF, 0}};
	static boolean adj2[][] = {{true, true, true, false, true},
			  			   {false, true, false, true, false},
						   {false, true, true, false, true},
						   {true, false, true, true, true},
						   {false, false, false, false, true}};
	
	public static void main(String[] args) {
		
		for(int i = 0; i < V; ++i)
			for(int j = 0; j < V; ++j)
				p[i][j] = i;
		
		floyd();
		//minimax();
		for(int i = 0; i < V; ++i) System.out.println(Arrays.toString(adj[i]));
		for(int i = 0; i < V; ++i) System.out.println(Arrays.toString(p[i]));
		//print(3, 4);
		System.out.println();
		System.out.println();
		//transitiveClosure();
		//for(int i = 0; i < V; ++i) System.out.println(Arrays.toString(adj2[i]));
	}

	public static void minimax() {
		for (int k = 0; k < V; k++)
			for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
			adj[i][j] = Math.min(adj[i][j], Math.max(adj[i][k], adj[k][j]));
	}
	
	public static void transitiveClosure() {
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j) {
					adj2[i][j] |= (adj2[i][k] & adj2[k][j]);
				}
	}
	
	private static void print(int src, int dest) {
		if(src == dest) System.out.print(src);
		else {
			print(src, p[src][dest]);
			System.out.print(" " + dest);
		}
	}

	private static void floyd() {
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j) {
					if(adj[i][k] + adj[k][j] < adj[i][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
						p[i][j] = p[k][j];
					}
				}
	}
}
