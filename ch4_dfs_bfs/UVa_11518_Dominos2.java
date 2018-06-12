package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_11518_Dominos2 {

	static ArrayList<Integer> adj[] = new ArrayList[100001];
	static boolean vis[] = new boolean[100001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m, l;
		
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt(); l = sc.nextInt();
			for(int i = 1; i <= n; ++i) {
				adj[i] = new ArrayList<Integer>();
				vis[i] = false;
			}
			while(m-- > 0) {
				adj[sc.nextInt()].add(sc.nextInt());
			}
			int res = 0, i;
			while(l-- > 0) {
				i = sc.nextInt();
				if(i >= 1 && i <= n) {
					if(!vis[i])
						res += dfs(i);
				}
			}
			
			out.println(res);
		}
		
		out.flush();
		out.close();
	}

	private static int dfs(int i) {
		vis[i] = true;
		
		int res = 1;
		
		for(int v : adj[i])
			if(!vis[v])
				res += dfs(v);
		return res;
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