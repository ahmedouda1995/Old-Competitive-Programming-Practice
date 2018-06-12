package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa_11749_PoorTradeAdvisor {

	static int N;
	static ArrayList<Integer> adj[] = new ArrayList[501];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int m;
		ArrayList<Triple> edges = new ArrayList<Triple>();
		
		while((N = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			edges.clear();
			for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < m; ++i)
				edges.add(new Triple(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			
			Collections.sort(edges);
			
			int max = edges.get(0).z;
			int k = 0;
			
			while(k < edges.size() && edges.get(k).z == max) {
				adj[edges.get(k).x].add(edges.get(k).y);
				adj[edges.get(k).y].add(edges.get(k).x);
				k++;
			}
			
			int res = 2;
			
			boolean vis[] = new boolean[N + 1];
			
			for(int i = 1; i <= N; ++i) {
				res = Math.max(res, dfs(i, vis));
			}
			
			out.println(res);
		}
		
		out.flush();
		out.close();
	}

	private static int dfs(int i, boolean [] vis) {
		vis[i] = true;
		
		int res = 1;
		
		for(int v : adj[i]) {
			if(!vis[v])
				res += dfs(v, vis);
		}
		return res;
	}

	static class Triple implements Comparable<Triple>{
		int x, y, z;
		Triple(int a, int b, int c) { x = a; y = b; z = c; }

		@Override
		public int compareTo(Triple t) {
			return Integer.compare(t.z, this.z);
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