package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_00796_CriticalLinks {

	static int V = 1000, dfsNumberCounter;
	static LinkedList<Integer> adj[] = new LinkedList[V];
	static int dfs_num[] = new int[V];
	static int dfs_low[] = new int[V];
	static int parent[] = new int[V];
	static ArrayList<Pair> res = new ArrayList<Pair>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n; String s;
		
		while((s = sc.nextLine()) != null) {
			res.clear();
			n = Integer.parseInt(s);
			
			for(int i = 0; i < n; ++i) {
				adj[i] = new LinkedList<Integer>();
				dfs_num[i] = dfs_low[i] = parent[i] = 0;
			}
			
			String tmp[];
			for(int i = 0; i < n; ++i) {
				tmp = sc.nextLine().split(" ");
				int a, b;
				a = Integer.parseInt(tmp[0]);
				int k = Integer.parseInt(tmp[1].substring(1, tmp[1].length() - 1));
				for(int j = 0; j < k; ++j) {
					b = Integer.parseInt(tmp[2 + j]);
					adj[a].add(b);
				}
			}
			
			sc.nextLine();
			
			dfsNumberCounter = 0;
			
			for(int i = 0; i < n; ++i) {
				if(dfs_num[i] == 0)
					bridge(i);
			}
			Collections.sort(res);
			out.println(res.size() + " critical links");
			for(int i = 0; i < res.size(); i++) {
				out.println(res.get(i).x + " - " + res.get(i).y);
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void bridge(int i) {
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				parent[v] = i;
				
				bridge(v);
				
				if(dfs_num[i] < dfs_low[v]) {
					if(i < v)
						res.add(new Pair(i, v));
					else
						res.add(new Pair(v, i));
				}
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
			else if(v != parent[i]) {
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