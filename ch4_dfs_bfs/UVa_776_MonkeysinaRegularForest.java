package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_776_MonkeysinaRegularForest {

	static char grid[][] = new char[1000][1000];
	static int res[][] = new int[1000][1000];
	static boolean vis[][];
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		StringTokenizer st; String s;
		
		while(sc.ready()) {
			int i = 0, j = 0;
			
			while((s = sc.nextLine()) != null && !s.equals("%")) {
				j = 0;
				st = new StringTokenizer(s);
				
				while(st.hasMoreTokens()) {
					grid[i][j++] = st.nextToken().charAt(0);
				}
				i++;
			}
			N = i; M = j;
			vis = new boolean[N][M];
			
			int k = 1;
			
			for(i = 0; i < N; ++i) {
				for(j = 0; j < M; ++j) {
					if(!vis[i][j]) {
						dfs(i, j, grid[i][j], k);
						k++;
					}
				}
			}
			
			k--;
			
			int len[] = new int[M];
			
			for(j = 0; j < M; ++j) {
				int max = 0;
				for(i = 0; i < N; ++i) {
					max = Math.max(max, res[i][j]);
				}
				len[j] = (max + "").length();
			}
			
			for(i = 0; i < N; ++i) {
				out.print(format(res[i][0], len[0]));
				for(j = 1; j < M; ++j) {
					out.print(" " + format(res[i][j], len[j]));
				}
				out.println();
			}
			
			out.println("%");
		}
		
		out.flush();
		out.close();
	}

	private static String format(int i, int len) {
		String s = Integer.toString(i);
		while(s.length() < len) {
			s = " " + s;
		}
		return s;
	}

	private static char[] format(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void dfs(int i, int j, char c, int x) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] != c || vis[i][j]) return;
		
		res[i][j] = x; vis[i][j] = true;
		for(int k = 0; k < 8; ++k) dfs(i + dr[k], j + dc[k], c, x);
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