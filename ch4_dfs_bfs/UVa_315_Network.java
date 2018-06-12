package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_315_Network {

	static LinkedList<Integer> adj[] = new LinkedList[100];
	static int dfs_num[] = new int[100];
	static int dfs_low[] = new int[100];
	static int dfsNumberCounter, dfs_root, root_children;
	static boolean ap[] = new boolean[100];
	static int parent[] = new int[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n; String s[];
		
		while((n = sc.nextInt()) != 0) {
			
			for(int i = 1; i <= n; ++i) {
				dfs_num[i] = dfs_low[i] = 0;
				ap[i] = false;
				adj[i] = new LinkedList<Integer>();
				parent[i] = 0;
			}
			
			label: while(true) {
				s = sc.nextLine().split(" ");
				int a = Integer.parseInt(s[0]);
				if(a == 0) break;
				int b;
				for(int i = 1; i < s.length; ++i) {
					b = Integer.parseInt(s[i]);
					if(b == 0) break label;
					adj[a].add(b); adj[b].add(a);
				}
			}
			
			for(int i = 1; i <= n; ++i) {
				if(dfs_num[i] == 0) {
					dfs_root = i; dfsNumberCounter = 0; root_children = 0;
					articulationPoints(i);
					ap[i] = (root_children > 1);
				}
			}
			int res = 0;
			for(int i = 1; i <= n; ++i) {
				if(ap[i]) res++;
			}
			
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static void articulationPoints(int i) {
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				parent[v] = i;
				if(i == dfs_root) root_children++;
				articulationPoints(v);
				
				if(dfs_low[v] >= dfs_num[i])
					ap[i] = true;
				
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
			else if(v != parent[i]){
				dfs_low[i] = Math.min(dfs_low[i], dfs_num[v]);
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