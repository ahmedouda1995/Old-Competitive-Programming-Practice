package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10336_Rank_the_Languages {

	static char grid[][] = new char[1000][1000];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
			int n = sc.nextInt(), m = sc.nextInt(); N = n; M = m;
			String s;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < m; ++j) grid[i][j] = s.charAt(j);
			}
			
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < m; ++j) {
					if(grid[i][j] != '.') {
						if(!map.containsKey(grid[i][j])) {
							map.put(grid[i][j], 1);
							dfs(i, j, grid[i][j]);
						}
						else {
							map.replace(grid[i][j], map.get(grid[i][j]) + 1);
							dfs(i, j, grid[i][j]);
						}
					}
				}
			}
			
			ArrayList<Pair> a = new ArrayList<Pair>();
			for(Entry<Character, Integer> e : map.entrySet())
				a.add(new Pair(e.getKey(), e.getValue()));
			Collections.sort(a);
			
			out.printf("World #%d\n", cases++);
			for(Pair p : a) out.println(p.c + ": " + p.o);
			
		}
		
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i, int j, char c) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] != c)
			return;
		grid[i][j] = '.';
		for(int k = 0; k < 4; ++k) dfs(i + dr[k], j + dc[k], c);
	}

	public static int gcd(int n, int m) {
	    return (n % m) == 0? m : gcd(m, n % m);
	}
	
	static class Pair implements Comparable<Pair>{
		char c;
		int o;
		
		public Pair(char c, int o) {
			this.c = c;
			this.o = o;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.o, p.o) == 0)
				return Integer.compare(this.c, p.c);
			return Integer.compare(p.o, this.o);
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