package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_314_Robot {

	static int N, M, INF = (int) 1e9;
	static int grid[][];
	static int dist[][][];
	static int dx[] = {0, -1, -1, 0};
	static int dy[] = {0, -1, 0, -1};
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	static int arr[] = {0, 1, 2, 1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while((N = sc.nextInt()) != 0 | (M = sc.nextInt()) != 0) {
			grid = new int[N][M]; dist = new int[N][M][4];
			
			int sx, sy, ex, ey, dir;
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					grid[i][j] = sc.nextInt();
					for(int k = 0; k < 4; ++k) {
						dist[i][j][k] = INF;
					}
				}
			}
			
			sx = sc.nextInt(); sy = sc.nextInt();
			ex = sc.nextInt(); ey = sc.nextInt();
			dir = getDir(sc.next());
			
			bfs(sx, sy, ex, ey, dir);
			
			int res = INF;
			
			for(int k = 0; k < 4; ++k) res = Math.min(res, dist[ex][ey][k]);
			
			if(res == INF)
				out.println(-1);
			else
				out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int i, int j, int ex, int ey, int dir) {
		Queue<Triple> q = new LinkedList<Triple>();
		
		dist[i][j][dir] = 0; q.offer(new Triple(i, j, dir));

		while(!q.isEmpty()) {
			Triple u = q.poll();
			
			if(u.a == ex && u.b == ey) return;
			
			int x, y, d;
			
			for(int l = 0; l < 4; ++l) {
				d = (u.c + l) % 4;
				for(int w = 1; w <= 3; ++w) {
					x = u.a + (dr[d] * w); y = u.b + (dc[d] * w);
					if(isSafe(x, y) && dist[u.a][u.b][u.c] + arr[l] + 1 <= dist[x][y][d]) {
						dist[x][y][d] = dist[u.a][u.b][u.c] + arr[l] + 1;
						q.offer(new Triple(x, y, d));
					}
					else
						break;
				}
			}
			
		}
	}

	public static boolean isSafe(int x, int y) {
		
		int i, j;
		
		for(int k = 0; k < 4; ++k) {
			i = x + dx[k]; j = y + dy[k];
			if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == 1) return false;
		}
		
		return true;
	}
	
	private static int getDir(String s) {
		int res = 0;
		
		switch(s) {
		case "north":res = 0;break;
		case "east": res = 1;break;
		case "south":res = 2;break;
		case "west": res = 3;break;
		}
		
		return res;
	}

	static class Triple {
		int a, b, c;
		
		public Triple(int x, int y, int z) {
			a = x; b = y; c = z;
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