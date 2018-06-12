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
import java.util.TreeMap;

public class UVa_10199_TouristGuide {

	static LinkedList<Integer> adj[] = new LinkedList[100];
	static int dfs_num[] = new int[100];
	static int dfs_low[] = new int[100];
	static int parent[] = new int[100];
	static int dfsNumberCounter, dfsRoot, rootChildren;
	static boolean ap[] = new boolean[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, cases = 1;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
		boolean first = true;
		while((n = sc.nextInt()) != 0) {
			if(!first) out.println();
			else first = false;
			
			String s;
			map.clear(); map2.clear();
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				map.put(s, i); map2.put(i, s);
			}
			for(int i = 0; i < n; ++i) {
				adj[i] = new LinkedList<Integer>();
				dfs_num[i] = dfs_low[i] = parent[i] = 0;
				ap[i] = false;
			}
			
			int r = sc.nextInt();
			for(int i = 0; i < r; ++i) {
				int a = map.get(sc.next()), b = map.get(sc.next());
				adj[a].add(b); adj[b].add(a);
			}
			
			dfsNumberCounter = 0;
			
			for(int i = 0; i < n; ++i) {
				if(dfs_num[i] == 0) {
					dfsRoot = i; rootChildren = 0;
					articulationPoints(i);
					ap[i] = (rootChildren > 1);
				}
			}
			
			ArrayList<String> a = new ArrayList<String>();
			
			for(int i = 0; i < n; ++i) {
				if(ap[i])
					a.add(map2.get(i));
			}
			
			out.printf("City map #%d: %d camera(s) found\n", cases++, a.size());
			Collections.sort(a);
			for(String st : a)
				out.println(st);
		}
		
		out.flush();
		out.close();
	}
	
	private static void articulationPoints(int i) {
		dfs_num[i] = dfs_low[i] = ++dfsNumberCounter;
		
		for(int v : adj[i]) {
			if(dfs_num[v] == 0) {
				if(i == dfsRoot) rootChildren++;
				parent[v] = i;
				articulationPoints(v);
				if(dfs_num[i] <= dfs_low[v])
					ap[i] = true;
				
				dfs_low[i] = Math.min(dfs_low[i], dfs_low[v]);
			}
			else if(v != parent[i])
				dfs_low[i] = Math.min(dfs_low[i], dfs_num[v]);
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