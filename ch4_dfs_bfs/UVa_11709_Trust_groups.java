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
import java.util.TreeMap;

public class UVa_11709_Trust_groups {

	static int dfsNumberCounter, numSCC;
	static LinkedList<Integer> adj[] = new LinkedList[1000];
	static boolean vis[] = new boolean[1000];
	static int dfs_num[] = new int[1000];
	static int dfs_low[] = new int[1000];
	static Stack<Integer> stack = new Stack<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			map.clear();
			String s;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				map.put(s, i);
				adj[i] = new LinkedList<Integer>();
				dfs_num[i] = dfs_low[i] = 0;
			}
			
			for(int i = 0; i < m; ++i)
				adj[map.get(sc.nextLine())].add(map.get(sc.nextLine()));
			
			numSCC = dfsNumberCounter = 0;
			
			for(int i = 0; i < n; ++i)
				if(dfs_num[i] == 0)
					tarjanSCC(i);
			
			out.println(numSCC);
		}
		
		out.flush();
		out.close();
	}
	
	private static void tarjanSCC(int i) {
		vis[i] = true;
		stack.add(i);
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(vis[v])
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
		}
		
		if(dfs_num[i] == dfs_low[i]) {
			numSCC++;
			
			while(true) {
				int v = stack.pop(); vis[v] = false;
				if(i == v) break;
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