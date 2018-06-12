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

public class Sam2 {

	static int N, M;
	static boolean vis[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		M = sc.nextInt();
		
		String s;
		int grid1[][], grid2[][];
		
		grid1 = new int[N][M];
		grid2 = new int[N][M];
		vis = new boolean[N][M];
		
		for(int i = 0; i < N; ++i) {
			s = sc.next();
			for(int j = 0; j < M; ++j) {
				grid1[i][j] = s.charAt(j);
			}
		}
		
		sc.nextLine();
		
		for(int i = 0; i < N; ++i) {
			s = sc.next();
			for(int j = 0; j < M; ++j) {
				grid2[i][j] = s.charAt(j);
			}
		}
		
		int dist1 = bfs(0, 0, grid1);
		for(int i = 0; i < N; ++i) Arrays.fill(vis[i], false);
		int dist2 = bfs(0, 0, grid2);
		for(int i = 0; i < N; ++i) Arrays.fill(vis[i], false);
		int dist = bfs2(grid1, grid2);
		
		if(dist1 == dist2 && dist == dist1 && dist != -1)
			out.println("YES");
		else
			out.println("NO");
		
		out.flush();
		out.close();
	}
	
	static int bfs2(int grid1[][], int grid2[][]) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0); q.offer(0); q.offer(0);
		vis[0][0] = true;
		
		int a, b, d, x, y;
		
		while(!q.isEmpty()) {
			a = q.poll(); b = q.poll(); d = q.poll();
			
			if(a == N - 1 && b == M - 1) return d;
			
			for(int i = 0; i < 4; ++i) {
				x = a + dr[i];
				y = b + dc[i];
				
				if(valid(x, y) && !vis[x][y] && grid1[x][y] != '#' && grid2[x][y] != '#') {
					q.offer(x);
					q.offer(y);
					q.offer(d + 1);
					vis[x][y] = true;
				}
			}
		}
		return -1;
	}
	
	static boolean valid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
	
	static int bfs(int x, int y, int [][] grid) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x); q.offer(y); q.offer(0);
		vis[x][y] = true;
		
		int a, b, d;
		
		while(!q.isEmpty()) {
			a = q.poll(); b = q.poll(); d = q.poll();
			
			if(a == N - 1 && b == M - 1) return d;
			
			for(int i = 0; i < 4; ++i) {
				x = a + dr[i];
				y = b + dc[i];
				
				if(valid(x, y) && !vis[x][y] && grid[x][y] != '#') {
					q.offer(x);
					q.offer(y);
					q.offer(d + 1);
					vis[x][y] = true;
				}
			}
		}
		return -1;
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