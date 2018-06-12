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

public class UVa_10305_Ordering_Tasks {

	static LinkedList<Integer> adj[] = new LinkedList[101];
	static boolean vis[];
	static Stack<Integer> stack = new Stack<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			for(int i = 1; i <= n; ++i) adj[i] = new LinkedList<Integer>();
			vis = new boolean[n + 1];
			
			for(int i = 0; i < m; ++i) adj[sc.nextInt()].add(sc.nextInt());
			
			for(int i = 1; i <= n; ++i) {
				if(!vis[i])
					dfs(i);
			}
			
			while(stack.size() > 1)
				out.print(stack.pop() + " ");
			out.println(stack.pop());
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i) {
		vis[i] = true;
		
		for(int v : adj[i])
			if(!vis[v])
				dfs(v);
		
		stack.push(i);
	}

	public static int gcd(int n, int m) {
	    return (n % m) == 0? m : gcd(m, n % m);
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