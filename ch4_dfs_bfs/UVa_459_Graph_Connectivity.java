package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_459_Graph_Connectivity {

	static LinkedList<Integer> adj[] = new LinkedList[26];
	static boolean vis[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		while(t-- > 0) {
			int n = sc.nextLine().charAt(0) - 'A' + 1;
			for(int i = 0; i < n; i++) adj[i] = new LinkedList<Integer>();
			
			String s;
			vis = new boolean[26];
			
			while((s = sc.nextLine()) != null && s.length() > 0) {
				adj[s.charAt(0) - 'A'].add(s.charAt(1) - 'A');
				adj[s.charAt(1) - 'A'].add(s.charAt(0) - 'A');
			}

			int cc = 0;
			
			for(int i = 0; i < n; ++i) {
				if(!vis[i]) {
					dfs(i);
					cc++;
				}
			}
			out.println(cc);
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i) {
		vis[i] = true;
		
		for(int v : adj[i])
			if(!vis[v])
				dfs(v);
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