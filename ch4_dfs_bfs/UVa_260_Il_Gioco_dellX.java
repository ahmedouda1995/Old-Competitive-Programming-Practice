package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_260_Il_Gioco_dellX {

	static char grid[][] = new char[200][200];
	static int N;
	static int dr[] = {-1, -1, 0, 0, 1, 1};
	static int dc[] = {-1, 0, -1, 1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, cases = 1; String s;
		
		while((n = sc.nextInt()) != 0) {
			N = n;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < n; ++j) grid[i][j] = s.charAt(j);
			}
			int i = 0;
			for(i = 0; i < n; ++i) {
				if(grid[0][i] == 'b')
					if(dfs(0, i)) {
						out.println(cases++ + " B");
						break;
					}
			}
			if(i == n)
				out.println(cases++ + " W");
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean dfs(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if(grid[x][y] != 'b') return false;
		if(x == N - 1)
			return true;
		grid[x][y] = 'a';
		boolean res = false;
		for(int i = 0; i < 6; ++i) res |= dfs(x + dr[i], y + dc[i]);
		return res;
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