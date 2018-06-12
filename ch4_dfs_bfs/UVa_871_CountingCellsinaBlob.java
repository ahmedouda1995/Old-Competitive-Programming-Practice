package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_871_CountingCellsinaBlob {

	static char grid[][] = new char[25][25];
	static int N, M;
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(); String s;
		sc.nextLine();
		while(t-- > 0) {
			int i = 0, j = 0;
			while(((s = sc.nextLine()) != null) && s.length() > 0) {
				for(j = 0; j < s.length(); ++j) grid[i][j] = s.charAt(j);
				i++;
			}
			N = i; M = j;
			int max = 0;
			for(i = 0; i < N; ++i) {
				for(j = 0; j < M; ++j) {
					if(grid[i][j] == '1')
						max = Math.max(max, dfs(i, j));
				}
			}
			out.println(max);
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int dfs(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == '0') return 0;
		
		grid[i][j] = '0';
		int res = 1;
		
		for(int k = 0; k < 8; ++k) res += dfs(i + dr[k], j + dc[k]);
		
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