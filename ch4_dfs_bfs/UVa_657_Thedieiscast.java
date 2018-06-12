package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa_657_Thedieiscast {

	static char grid[][] = new char[50][50];
	static int N, M;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		ArrayList<Integer> res = new ArrayList<Integer>();
		int cases = 1;
		while((M = sc.nextInt()) != 0 | (N = sc.nextInt()) != 0) {
			res.clear();
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < M; ++j) grid[i][j] = s.charAt(j);
			}
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid[i][j] == '*' || grid[i][j] == 'X') {
						res.add(dfs(i, j));
					}
				}
			}
			
			Collections.sort(res);
			out.printf("Throw %d\n", cases++);
			for(int i = 0; i < res.size() - 1; ++i) out.print(res.get(i) + " ");
			out.println(res.get(res.size() - 1));
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int dfs(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == '.') return 0;
		int res = 0;
		if(grid[i][j] == 'X') {
			res++;
			dfs2(i, j);
		}
		grid[i][j] = '.';
		for(int k = 0; k < 4; ++k) res += dfs(i + dr[k], j + dc[k]);
		return res;
	}

	private static void dfs2(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == '.' || grid[i][j] == '*')
			return;
		grid[i][j] = '*';
		for(int k = 0; k < 4; ++k)  dfs2(i + dr[k], j + dc[k]);
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