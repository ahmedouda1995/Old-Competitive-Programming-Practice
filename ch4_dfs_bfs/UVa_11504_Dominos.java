package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_11504_Dominos {

	static LinkedList<Integer> adj[] = new LinkedList[100001];
	static boolean vis[] = new boolean[100001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 1; i <= 100000; ++i) {
			adj[i] = new LinkedList<Integer>();
		}
		
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			
			for(int i = 1; i <= n; ++i) {
				vis[i] = false;
				adj[i].clear();
			}
			
			for(int i = 0 ; i < m; ++i) adj[sc.nextInt()].add(sc.nextInt());
			
			for(int i = 1; i <= n; ++i) {
				if(!vis[i])
					kosaraju1(i, stack);
			}
			int res = 0;
			
			while(!stack.isEmpty()) {
				int i = stack.pop();
				if(vis[i]) {
					kosaraju2(i);
					res++;
				}
			}
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static void kosaraju2(int i) {
		vis[i] = false;
		for(int v : adj[i]) {
			if(vis[v])
				kosaraju2(v);
		}
	}

	private static void kosaraju1(int i, Stack<Integer> stack) {
		vis[i] = true;
		for(int v : adj[i]) {
			if(!vis[v])
				kosaraju1(v, stack);
		}
		stack.push(i);
	}
	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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