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
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10731_Test {

	static LinkedList<Integer> adj[] = new LinkedList[26];
	static ArrayList<Pair> res = new ArrayList<Pair>();
	static Stack<Integer> stack = new Stack<Integer>();
	static int numSCC, dfsNumberCounter;
	static int dfs_num[] = new int[26];
	static int dfs_low[] = new int[26];
	static boolean vis[] = new boolean[26];
	static TreeMap<Integer, Character> map2 = new TreeMap<Integer, Character>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		boolean first = true;
		int n;
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		
		while((n = sc.nextInt()) != 0) {
			
			if(first) first = false;
			else out.println();
			map.clear(); map2.clear();
			
			String [] s;
			int k = 0;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine().split(" ");
				char c = s[5].charAt(0);
				
				if(!map.containsKey(c)){
					adj[k] = new LinkedList<Integer>();
					map2.put(k, c);
					map.put(c, k++);
				}
				
				for(int j = 0; j < 5; ++j) {
					if(!map.containsKey(s[j].charAt(0))){
						adj[k] = new LinkedList<Integer>();
						map2.put(k, s[j].charAt(0));
						map.put(s[j].charAt(0), k++);
					}
					adj[map.get(c)].add(map.get(s[j].charAt(0)));
				}
			}
			
			for(int i = 0; i < k; ++i) {
				dfs_low[i] = dfs_num[i] = 0;
			}
			res.clear();
			
			for(int i = 0; i < k; ++i) {
				if(dfs_num[i] == 0)
					tarjanSCC(i);
			}
			Collections.sort(res);
			
			for(Pair p : res) {
				Collections.sort(p.list);
				for(int i = 0; i < p.list.size() - 1; ++i) {
					out.print(p.list.get(i) + " ");
				}
				out.println(p.list.get(p.list.size() - 1));
			}
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
		
		if(dfs_low[i] == dfs_num[i]) {
			char min = 'Z';
			ArrayList<Character> tmp = new ArrayList<Character>();
			while(true) {
				int v = stack.pop(); vis[v] = false;
				char c = map2.get(v); if(c < min) min = c;
				tmp.add(c);
				if(v == i) break;
			}
			res.add(new Pair(min, tmp));
		}
	}


	static class Pair implements Comparable<Pair>{
		char c;
		ArrayList<Character> list;
		
		public Pair(char c, ArrayList<Character> list) {
			this.c = c;
			this.list = list;
		}

		@Override
		public int compareTo(Pair p) {
			return Character.compare(this.c, p.c);
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