package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Uva_11352_CrazyKing {

	static int N, M, INF = (int) 1e9;
	static char grid[][] = new char[1001][1001];
	static int dist[][] = new int[1001][1001];
	static int kr[] = {-1, -2, -1, -2, 1, 2, 1, 2};
	static int kc[] = {-2, -1, 2, 1, -2, -1, 2, 1};
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			
			String s;
			
			int ax = 0, ay = 0, bx = 0, by = 0;
			
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < M; ++j) {
					dist[i][j] = INF;
					grid[i][j] = s.charAt(j);
					if(grid[i][j] == 'A') { ax = i; ay = j; }
					else if(grid[i][j] == 'B') { bx = i; by = j; }
				}
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(grid[i][j] == 'Z') {
						int x, y;
						grid[i][j] = 'S';
						for(int k = 0; k < 8; ++k) {
							x = i + kr[k];
							y = j + kc[k];
							
							if(x >= 0 && y >= 0 && x < N && y < M) {
								if(grid[x][y] != 'A' && grid[x][y] != 'B' && grid[x][y] != 'Z') {
									grid[x][y] = 'S';
								}
							}
						}
					}
				}
			}
			
			int res = bfs(ax, ay, bx, by);
			
			if(res == INF)
				out.println("King Peter, you can't go now!");
			else
				out.println("Minimal possible length of a trip is " + res);
			
		}
		
		out.flush();
		out.close();
	}
	
	private static int bfs(int ax, int ay, int bx, int by) {
		Queue<Integer> q = new LinkedList<Integer>();
		dist[ax][ay] = 0; q.offer(ax); q.offer(ay);
		
		int ux, uy;
		
		while(!q.isEmpty()) {
			ux = q.poll();
			uy = q.poll();
			
			if(ux == bx && uy == by) return dist[ux][uy];
			
			int x, y;
			
			for(int k = 0; k < 8; ++k) {
				x = ux + dr[k]; y = uy + dc[k];
				if(x >= 0 && y >= 0 && x < N && y < M && grid[x][y] != 'S') {
					if(dist[x][y] == INF) {
						dist[x][y] = dist[ux][uy] + 1;
						q.offer(x); q.offer(y);
					}
				}
			}
		}
		return dist[bx][by];
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