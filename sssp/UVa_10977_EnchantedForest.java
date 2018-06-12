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

public class UVa_10977_EnchantedForest {

	static int N, M;
	static boolean grid[][];
	static int dr[] = {0, 0, 1, -1}; // left, right, down, up
	static int dc[] = {-1, 1, 0, 0};
	static final int INF = (int) 1e9;
	static int dist[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int m, a, b;
		
		while((N = sc.nextInt()) != 0 | (M = sc.nextInt()) != 0) {
			
			grid = new boolean[N][M];
			dist = new int[N][M];
			
			for(int i = 0; i < N; ++i) Arrays.fill(dist[i], INF);
			
			m = sc.nextInt();
			
			while(m-- > 0) {
				a = sc.nextInt() - 1; b = sc.nextInt() - 1;
				grid[a][b] = true;
			}
			
			m = sc.nextInt();
			int k = 2;
			while(m-- > 0) {
				a = sc.nextInt() - 1; b = sc.nextInt() - 1;
				jigglyPuff(a, b, sc.nextInt());
				k++;
			}
			
			int res = bfs();
			
			if(res == INF)
				out.println("Impossible.");
			else
				out.println(res);
		}
		
		out.flush();
		out.close();
	}

	private static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		if(grid[0][0]) return INF;
		dist[0][0] = 0; q.offer(0); q.offer(0);
		
		int x, y;
		
		while(!q.isEmpty()) {
			x = q.poll(); y = q.poll();
			
			if(x == N - 1 && y == M - 1) return dist[N - 1][M - 1];
			
			int u, v;
			
			for(int i = 0; i < 4; ++i) {
				u = x + dr[i]; v = y + dc[i];
				if(u >= 0 && v >= 0 && u < N && v < M && dist[u][v] == INF && !grid[u][v]) {
					dist[u][v] = dist[x][y] + 1;
					q.offer(u); q.offer(v);
				}
			}
		}
		return dist[N - 1][M - 1];
	}
	
	private static void jigglyPuff(int a, int b, int r) {
		int i = a - r, j = b - r;
		if(i < 0) i = 0; if(j < 0) j = 0;
		int n = a + r, m = b + r;
		if(n >= N) n = N - 1; if(m >= M) m = M - 1;
		
		int tmp = j;
		for(;i <= n; ++i)
			for(j = tmp; j <= m; ++j) {
				double d = dist(i, j, a, b);
				if(d <= r) grid[i][j] = true;
			}
	}

	static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
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