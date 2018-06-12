package graphs;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Connected_cell_in_a_grid {

	static LinkedList<Integer> [] adj;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = sc.nextInt(), m = sc.nextInt();
		int [][] matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		adj = new LinkedList[n * m];
		boolean [] visited = new boolean[n * m];
		for (int i = 0; i < adj.length; i++) adj[i] = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(matrix[i][j] == 1){
					if(i - 1 >= 0 && j - 1 >= 0 && matrix[i-1][j - 1] == 1)
						adj[m * i + j].add((m * (i - 1)) + j - 1);
					if(i - 1 >= 0 && matrix[i-1][j] == 1)
						adj[m * i + j].add((m * (i - 1)) + j);
					if(i - 1 >= 0 && j + 1 < m && matrix[i-1][j + 1] == 1)
						adj[m * i + j].add((m * (i - 1)) + j + 1);
					if((j - 1) >= 0 && matrix[i][j - 1] == 1)
						adj[m * i + j].add((m * i) + j - 1);
					if((j + 1) < m && matrix[i][j + 1] == 1)
						adj[m * i + j].add((m * i) + j + 1);
					if(i + 1 < n && j - 1 >= 0 && matrix[i+1][j - 1] == 1)
						adj[m * i + j].add((m * (i + 1)) + j - 1);
					if(i + 1 < n && matrix[i+1][j] == 1)
						adj[m * i + j].add((m * (i + 1)) + j);
					if(i + 1 < n && j + 1 < m && matrix[i+1][j + 1] == 1)
						adj[m * i + j].add((m * (i + 1)) + j + 1);
				}
			}
		}
		int max = 0;
		for (int i = 0; i < adj.length; i++) {
			if(!visited[i])
				max = Math.max(max, dfs(i, visited));
		}
		out.println(max);
		out.flush();
		out.close();
	}

	private static int dfs(int i, boolean [] visited) {
		visited[i] = true;
		int n = 1;
		for (int next : adj[i]) {
			if(!visited[next])
				n += dfs(next, visited);
		}
		return n;
	}
}
