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

public class UVa_11838_ComeandGo {

	static LinkedList<Integer> adj[] = new LinkedList[2001];
	static int numSCC, dfsNumberCounter;
	static boolean vis[] = new boolean[2001];
	static int dfs_num[] = new int[2001];
	static int dfs_low[] = new int[2001];
	static Stack<Integer> stack = new Stack<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			for(int i = 1; i <= n; ++i) adj[i] = new LinkedList<Integer>();
			
			for(int i = 0; i < m; ++i) {
				int a = sc.nextInt(), b = sc.nextInt(), dir = sc.nextInt();
				adj[a].add(b);
				if(dir == 2)
					adj[b].add(a);
			}
			numSCC = dfsNumberCounter = 0;
			for(int i = 1; i <= n; ++i) {
				vis[i] = false;
				dfs_num[i] = 0;
				dfs_low[i] = 0;
			}
			
			for(int i = 1; i <= n; ++i) {
				if(dfs_num[i] == 0)
					tarjanSCC(1);
			}
			
			if(numSCC == 1)
				out.println(1);
			else
				out.println(0);
		}
		
		out.flush();
		out.close();
	}
	
	private static void tarjanSCC(int i) {
		vis[i] = true;
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		stack.push(i);
		for(int v : adj[i]) {
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(vis[v])
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
		}
		
		if(dfs_num[i] == dfs_low[i]) {
			++numSCC;
			while(true) {
				int v = stack.pop(); vis[v] = false;
				if(v == i)
					break;
			}
		}
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