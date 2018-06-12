package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_929_NumberMaze {

	static int N, M, INF = (int) 1e9;
	static int grid[][] = new int[1000][1000];
	static int dist[][] = new int[1000][1000];
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) grid[i][j] = sc.nextInt();
			}
			
			out.println(dijkstra());
			
		}
		
		out.flush();
		out.close();
	}
	
	private static int dijkstra() {
		for(int i = 0; i < N; ++i) for(int j = 0; j < M; ++j) dist[i][j] = INF;
		
		PriorityQueue<Triple> pq = new PriorityQueue<Triple>();
		dist[0][0] = grid[0][0]; pq.add(new Triple(0, 0, grid[0][0]));
		
		while(!pq.isEmpty()) {
			Triple pos = pq.poll();
			if(pos.d > dist[pos.x][pos.y]) continue;
			int a, b;
			for(int i = 0; i < 4; ++i) {
				a = pos.x + dr[i]; b = pos.y + dc[i];
				if(isSafe(a, b)) {
					if(dist[pos.x][pos.y] + grid[a][b] < dist[a][b]) {
						dist[a][b] = dist[pos.x][pos.y] + grid[a][b];
						if(a == N - 1 && b == M - 1) break;
						pq.add(new Triple(a, b, dist[a][b]));
					}
				}
			}
		}
		return dist[N - 1][M - 1];
	}

	static boolean isSafe(int i, int j) {
		return (i >= 0 && j >= 0 && i < N && j < M);
	}
	
	static class Triple implements Comparable<Triple>{
		int x;
		int y;
		int d;
		
		public Triple(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Triple p) {
			return Integer.compare(this.d, p.d);
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