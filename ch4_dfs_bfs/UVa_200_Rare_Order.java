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

public class UVa_200_Rare_Order {

	static LinkedList<Integer> adj[] = new LinkedList[26];
	static boolean vis[] = new boolean[26];
	static int N;
	static Stack<Integer> stack = new Stack<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String prev = "", s;
		int k = 0;
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		TreeMap<Integer, Character> map2 = new TreeMap<Integer, Character>();
		for(int i = 0; i < 26; ++i) adj[i] = new LinkedList<Integer>();
		
		while(!(s = sc.nextLine()).equals("#")) {
			
			for(int i = 0; i < s.length(); ++i) {
				if(!map.containsKey(s.charAt(i))) {
					map.put(s.charAt(i), k);
					map2.put(k++, s.charAt(i));
				}
			}
			
			for(int i = 0; i < prev.length() && i < s.length(); ++i) {
				if(s.charAt(i) != prev.charAt(i)) {
					adj[map.get(prev.charAt(i))].add(map.get(s.charAt(i)));
					break;
				}
			}
			prev = s;
		}
		for(int i = 0; i < k; ++i) {
			if(!vis[i])
				dfs(i);
		}
		
		while(!stack.isEmpty()) out.print(map2.get(stack.pop()));
		out.println();
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i) {
		vis[i] = true;
		
		for(int v : adj[i])
			if(!vis[v])
				dfs(v);
		
		stack.add(i);
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