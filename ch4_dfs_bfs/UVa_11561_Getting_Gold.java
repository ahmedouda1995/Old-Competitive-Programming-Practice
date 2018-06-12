package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11561_Getting_Gold {

	static char grid[][] = new char[50][50];
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {-1, 1, 0, 0};
	static int H, W;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready()) {
			W = sc.nextInt();
			H = sc.nextInt();
			
			String s;
			
			int posX = 0, posY = 0;
			
			for(int i = 0; i < H; ++i) {
				s = sc.nextLine();
				
				for(int j = 0; j < s.length(); ++j) {
					if(s.charAt(j) == 'P') {
						posX = i; posY = j;
					}
					grid[i][j] = s.charAt(j);
				}
			}
			out.println(dfs(posX, posY));
		}
		
		out.flush();
		out.close();
	}

	private static int dfs(int i, int j) {
		if(i < 0 || j < 0 || i >= H || j >= W || grid[i][j] == '#') return 0;
		if(risk(i, j)) {
			if(grid[i][j] == 'G') {
				grid[i][j] = '#';
				return 1;
			}
			return 0;
		}
		int res = 0;
		if(grid[i][j] == 'G')
			res++;
		grid[i][j] = '#';
		for(int k = 0; k < 4; ++k) res += dfs(i + dr[k], j + dc[k]);
		return res;
	}

	static boolean risk(int i, int j) {
		for(int k = 0; k < 4; ++k) {
			int x = i + dr[k], y = j + dc[k];
			if(x < 0 || y < 0 || x >= H || y >= W) continue;
			if(grid[x][y] == 'T') return true;
		}
		return false;
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