package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_1229_Sub_dictionary {

	static int dfsNumberCounter, numSCC;
	static LinkedList<Integer> adj[] = new LinkedList[100];
	static int dfs_num[] = new int[100];
	static int dfs_low[] = new int[100];
	static boolean vis[] = new boolean[100];
	static Stack<Integer> stack = new Stack<Integer>();
	static TreeSet<Integer> set = new TreeSet<Integer>();
	static TreeSet<Integer> set3 = new TreeSet<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
		TreeSet<String> set2 = new TreeSet<String>();
		
		while((n = sc.nextInt()) != 0) {
			set.clear(); set2.clear(); set3.clear(); map.clear(); map2.clear();
			String s[];
			int k = 0;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine().split(" ");
				if(!map.containsKey(s[0])) {
					adj[k] = new LinkedList<Integer>();
					map.put(s[0], k); map2.put(k++, s[0]);
				}
				for(int j = 1; j < s.length; ++j) {
					if(!map.containsKey(s[j])) {
						adj[k] = new LinkedList<Integer>();
						map.put(s[j], k); map2.put(k++, s[j]);
					}
					adj[map.get(s[0])].add(map.get(s[j]));
				}
			}
			
			for(int i = 0; i < k; ++i) { dfs_low[i] = dfs_num[i] = 0; }
			dfsNumberCounter = numSCC = 0;
			for(int i = 0; i < k; ++i) {
				if(dfs_num[i] == 0)
					tarjanSCC(i);
			}
			for(int i : set)
				vis[i] = true;
			
			for(int i : set) dfs(i);
			
			set.addAll(set3);
			
			for(int i : set)
				set2.add(map2.get(i));
			
			out.println(set2.size());
			StringBuilder sb = new StringBuilder();
			
			for(String tmp : set2) sb.append(tmp + " ");
			
			out.println(sb.toString().trim());
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i) {
		if(!vis[i]) set3.add(i);
		vis[i] = true;
		
		for(int v : adj[i]) {
			if(!vis[v])
				dfs(v);
		}
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
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			while(true) {
				int v = stack.pop(); vis[v] = false; tmp.add(v);
				if(i == v) break;
			}
			if(tmp.size() >= 2)
				set.addAll(tmp);
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