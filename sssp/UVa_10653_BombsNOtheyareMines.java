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

public class UVa_10653_BombsNOtheyareMines {

	static int dr[] = {0, 0, -1, 1};
	static int dc[] = {-1, 1, 0, 0};
	static boolean grid[][];
	static int dist[][] = new int[1000][1000];
	static int N, M, INF = (int) 1e9;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while((N = sc.nextInt()) != 0 | (M = sc.nextInt()) != 0) {
			grid = new boolean[1000][1000];
			int r = sc.nextInt();
			int a, k;
			while(r-- > 0) {
				a = sc.nextInt(); k =sc.nextInt();
				
				for(int i = 0; i < k; ++i) grid[a][sc.nextInt()] = true;
			}
			
			int sx = sc.nextInt(), sy = sc.nextInt();
			int ex = sc.nextInt(), ey = sc.nextInt();
			
			bfs(sx, sy, ex, ey);
			
			out.println(dist[ex][ey]);
		}
		
		out.flush();
		out.close();
	}

	private static void bfs(int sx, int sy, int ex, int ey) {
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) dist[i][j] = INF;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		dist[sx][sy] = 0; q.offer(sx); q.offer(sy);
		int ux, uy;
		while(!q.isEmpty()) {
			ux = q.poll(); uy = q.poll();
			if(ux == ex && uy == ey) return;
			for(int i = 0; i < 4; ++i) {
				int vx = ux + dr[i];
				int vy = uy + dc[i];
				if(safe(vx, vy) && dist[vx][vy] == INF) {
					dist[vx][vy] = dist[ux][uy] + 1;
					q.offer(vx); q.offer(vy);
				}
			}
		}
	}

	static boolean safe(int ux, int uy) {
		return (ux >= 0 && uy >= 0 && ux < N && uy < M && !grid[ux][uy]);
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