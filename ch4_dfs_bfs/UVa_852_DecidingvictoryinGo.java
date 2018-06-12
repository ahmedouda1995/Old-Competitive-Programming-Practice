package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_852_DecidingvictoryinGo {

	static char [][] grid = new char[9][9];
	static boolean X, O;
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(); String s;
		
		while(t-- > 0) {
			int white = 0, black = 0;
			for(int i = 0; i < 9; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < 9; ++j) {
					grid[i][j] = s.charAt(j);
					if(grid[i][j] == 'O') white++;
					else if(grid[i][j] == 'X') black++;
				}
			}
			
			for(int i = 0; i < 9; ++i) {
				for(int j = 0; j < 9; ++j) {
					if(grid[i][j] == '.') {
						X = false; O = false;
						int count = dfs(i, j);
						if(X && !O) black += count;
						else if(!X && O) white += count;
					}
				}
			}
			
			out.printf("Black %d White %d\n", black, white);
		}
		
		out.flush();
		out.close();
	}

	private static int dfs(int i, int j) {
		if(i < 0 || j < 0 || i >= 9 || j >= 9) return 0;
		
		int res = 0;
		
		if(grid[i][j] == 'X') X = true;
		else if(grid[i][j] == 'O') O = true;
		else if(grid[i][j] == '.'){
			
			grid[i][j] = 'a';
			res = 1;
			
			for(int k = 0; k < 4; ++k) res += dfs(i + dr[k], j + dc[k]);
		}
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