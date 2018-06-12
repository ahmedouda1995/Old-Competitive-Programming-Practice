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

public class UVa_00280_Vertex {

	static LinkedList<Integer> adj[] = new LinkedList[101];
	static boolean vis[] = new boolean[101];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((n = sc.nextInt()) != 0) {
			for(int i = 1; i <= n; ++i) adj[i] = new LinkedList<Integer>();
			
			int v;
			while((v = sc.nextInt()) != 0) {
				int next;
				while((next = sc.nextInt()) != 0) adj[v].add(next);
			}
			
			int k = sc.nextInt(), start;
			
			for(int i = 0; i < k; ++i) {
				vis = new boolean[101];
				start = sc.nextInt();
				dfs(start);
				int res = 0;
				StringBuilder sb = new StringBuilder();
				for(int j = 1; j <= n; ++j)
					if(!vis[j]) {
						sb.append(" " + j);
						res++;
					}
				out.print(res);
				out.println(sb.toString());
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i) {
		for(int v : adj[i]){
			if(!vis[v]) {
				vis[v] = true;
				dfs(v);
			}
		}
	}

	public static void dfs() {
		
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