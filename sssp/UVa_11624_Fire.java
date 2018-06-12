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

public class UVa_11624_Fire {

	static int N, M, INF = (int) 1e9;
	static int dist[][] = new int[1001][1001];
	static char grid[][] = new char[1001][1001];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Queue<Integer> qF = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), sx = 0, sy = 0;
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			qF.clear();
			String s;
			
			for(int i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < M; ++j) {
					dist[i][j] = INF;
					grid[i][j] = s.charAt(j);
					
					if(grid[i][j] == 'F') {
						qF.offer(i); qF.offer(j);
					}
					else if(grid[i][j] == 'J') { sx = i; sy = j; }
				}
			}
			
			int res = bfs(sx, sy);
			
			if(res == INF)
				out.println("IMPOSSIBLE");
			else
				out.println(res + 1);
		}
		
		out.flush();
		out.close();
	}
	
	private static int bfs(int sx, int sy) {
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> tmp = new LinkedList<Integer>();
		dist[sx][sy] = 0; q.offer(sx); q.offer(sy);
		
		
		int ux, uy;
		
		while(!q.isEmpty()) {

			int x, y;
			
			while(!qF.isEmpty()) {
				int a, b;
				a = qF.poll(); b = qF.poll();
				for(int i = 0; i < 4; ++i) {
					x = a + dr[i];
					y = b + dc[i];
					if(x >= 0 && y >= 0 && x < N && y < M && grid[x][y] != '#') {
						if(grid[x][y] != 'F') {
							tmp.offer(x); tmp.offer(y); grid[x][y] = 'F';
						}
					}
				}
			}
			
			qF.addAll(tmp); tmp.clear();
			
			while(!q.isEmpty()) {
				
				ux = q.poll(); uy = q.poll();
				
				if(ux == 0 || uy == 0 || ux == N - 1 || uy == M - 1) {
					return dist[ux][uy];
				}
				
				for(int i = 0; i < 4; ++i) {
					x = ux + dr[i]; y = uy + dc[i];
					if(x >= 0 && y >= 0 && x < N && y < M && grid[x][y] != '#') {
						if(grid[x][y] != 'F' && dist[x][y] == INF) {
							tmp.offer(x); tmp.offer(y); dist[x][y] = dist[ux][uy] + 1;
						}
					}
				}
			}
			
			q.addAll(tmp); tmp.clear();
		}
		
		return INF;
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