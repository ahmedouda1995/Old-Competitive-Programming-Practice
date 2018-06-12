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

public class UVa_10946_Youwantwhatfilled {

	static char grid[][] = new char[50][50];
	static int N, M;
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		int cases = 1;
		ArrayList<Pair> res = new ArrayList<Pair>();
		while((N = sc.nextInt()) != 0 | (M = sc.nextInt()) != 0) {
			res.clear();
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < M; ++j) grid[i][j] = s.charAt(j);
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid[i][j] != '.') {
						char c = grid[i][j];
						res.add(new Pair(dfs(i, j, c), c));
					}
				}
			}
			
			Collections.sort(res);
			out.printf("Problem %d:\n", cases++);
			
			for(Pair p : res) {
				out.println(p.c + " " + p.n);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int dfs(int i, int j, char c) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] != c) return 0;
		
		grid[i][j] = '.';
		int res = 1;
		
		for(int k = 0; k < 4; ++k) res += dfs(i + dr[k], j + dc[k], c);
		return res;
	}

	static class Pair implements Comparable<Pair>{
		int n;
		char c;
		
		public Pair(int n, char c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.n, p.n) == 0)
				return Integer.compare(this.c, p.c);
			return Integer.compare(p.n, this.n);
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