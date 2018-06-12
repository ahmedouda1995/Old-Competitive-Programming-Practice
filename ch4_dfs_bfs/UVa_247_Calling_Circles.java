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

public class UVa_247_Calling_Circles {

	static int dfsNumberCounter, numSCC;
	static LinkedList<Integer> adj[] = new LinkedList[25];
	static int dfs_num[] = new int[25];
	static int dfs_low[] = new int[25];
	static boolean vis[] = new boolean[25];
	static Stack<Integer> stack = new Stack<Integer>();
	static PrintWriter out = new PrintWriter(System.out);
	static TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int n, m, cases = 1;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		boolean first = true;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			if(first) first = false;
			else out.println();
			map.clear(); map2.clear();
			for(int i = 0; i < n; ++i) {
				dfs_num[i] = dfs_low[i] = 0;
				adj[i] = new LinkedList<Integer>();
			}
			int k = 0;
			String s[];
			for(int i = 0; i < m; ++i) {
				s = sc.nextLine().split(" ");
				if(!map.containsKey(s[0])) {
					map.put(s[0], k); map2.put(k++, s[0]);
				}
				if(!map.containsKey(s[1])) {
					map.put(s[1], k); map2.put(k++, s[1]);
				}
				adj[map.get(s[0])].add(map.get(s[1]));
			}
			dfsNumberCounter = numSCC = 0;
			out.printf("Calling circles for data set %d:\n", cases++);
			for(int i = 0; i < n; ++i) {
				if(dfs_num[i] == 0) {
					tarjanSCC(i);
				}
			}
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
		if(dfs_low[i] == dfs_num[i]) {
			numSCC++;
			int v = stack.pop(); vis[v] = false;
			out.print(map2.get(v));
			while(i != v) {
				v = stack.pop(); vis[v] = false;
				out.print(", " + map2.get(v));
			}
			out.println();
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