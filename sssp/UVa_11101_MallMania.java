package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_11101_MallMania {

	static int INF = (int) 1e9;
	static char[][] grid = new char[2001][2001];
	static int[][] dist = new int[2001][2001];
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		
		while((n = sc.nextInt()) != 0) {
			grid = new char[2001][2001];
			dist = new int[2001][2001];
			
			for(int i = 0; i < 2001; ++i) Arrays.fill(dist[i], INF);
			
			ArrayList<Integer> sources = new ArrayList<Integer>();
			
			int a, b;
			
			while(n-- > 0) {
				a = sc.nextInt(); b = sc.nextInt();
				
				sources.add(a); sources.add(b);
				grid[a][b] = 'a';
			}
			
			n = sc.nextInt();
			
			while(n-- > 0) {
				a = sc.nextInt(); b = sc.nextInt();
				grid[a][b] = 'b';
			}
			
			out.println(bfs(sources));
		}
		
		out.flush();
		out.close();
	}

	private static int bfs(ArrayList<Integer> sources) {
		Queue<Integer> q = new LinkedList<Integer>();
		int a, b;
		
		for(int i = 0; i < sources.size(); i += 2) {
			a = sources.get(i); b = sources.get(i + 1);
			dist[a][b] = 0;
			q.offer(a); q.offer(b);
		}
		
		while(!q.isEmpty()) {
			int ux = q.poll(), uy = q.poll();
			
			if(grid[ux][uy] == 'b') return dist[ux][uy];
			int x, y;
			for(int i = 0; i < 4; ++i) {
				x = ux + dr[i]; y = uy + dc[i];
				if(x >= 0 && y >= 0 && x <= 2000 && y <= 2000 && dist[x][y] == INF) {
					dist[x][y] = dist[ux][uy] + 1;
					q.offer(x); q.offer(y);
				}
			}
		}
		return -1;
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