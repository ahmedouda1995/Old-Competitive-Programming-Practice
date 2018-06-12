package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_11902_Dominator {

	@SuppressWarnings("unchecked")
	static LinkedList<Integer> adj[] = new LinkedList[99];
 	static boolean vis[] = new boolean[99];
 	static boolean test[] = new boolean[99];
 	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int t = sc.nextInt();
		int next, cases = 1;
		while(t-- > 0) {
			int n = sc.nextInt();
			for(int i = 0; i < n; ++i) adj[i] = new LinkedList<Integer>();
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < n; ++j) {
					next = sc.nextInt();
					if(next == 1)
						adj[i].add(j);
				}
			}
			Arrays.fill(vis, false);
			dfs(0, vis);
			out.printf("Case %d:\n", cases++);
			seperate(n);
			for(int i = 0; i < n; ++i) {
				Arrays.fill(test, false);
				test[i] = true;
				dfs(0, test);
				out.print("|");
				for(int j = 0; j < n; ++j)
					if((j == i && vis[i] == true) || vis[j] == true && test[j] == false)
						out.print("Y|");
					else
						out.print("N|");
				out.println();
				seperate(n);
			}
			
		}
		
		out.flush();
		out.close();
	}
	
	public static void seperate(int n) {
		out.print("+");
		for(int i = 0; i < (2 * n - 1); ++i) out.print('-');
		out.println("+");
	}
	
//	private static void bfs(int i, boolean visited[]) {
//		if(visited[i])
//			return;
//		Queue<Integer> q = new LinkedList<Integer>();
//		q.offer(i);
//		visited[i] = true;
//		while(!q.isEmpty()) {
//			int curr = q.poll();
//			for(int v : adj[curr]) {
//				if(!visited[v]) {
//					q.offer(v);
//					visited[v] = true;
//				}
//			}
//		}
//	}
	
	public static void dfs(int i, boolean visited[]) {
		if(visited[i])
			return;
		visited[i] = true;
		for(int v : adj[i]) {
			if(!visited[v])
				dfs(v, visited);
		}
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}