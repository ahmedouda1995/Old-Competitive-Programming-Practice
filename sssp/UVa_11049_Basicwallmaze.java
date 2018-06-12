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

public class UVa_11049_Basicwallmaze {

	static char grid[][];
	static int states[][];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));

		int sx, sy, ex, ey;
		
		while((sy = sc.nextInt()) != 0 | (sx = sc.nextInt()) != 0) {
			ey = sc.nextInt(); ex = sc.nextInt();
			ex--; ey--; sx--; sy--;
			grid = new char[6][6]; states = new int[6][6];
			
			int ax, ay, bx, by;
			for(int i = 0; i < 3; ++i) {
				ay = sc.nextInt(); ax = sc.nextInt();
				by = sc.nextInt(); bx = sc.nextInt();
				
				if(ax == bx) { // horizontal
					for(int j = ay; j < by; ++j) {
						if(isSafe(ax, j))
							states[ax][j] |= (1 << 0);
						if(isSafe(ax - 1, j))
							states[ax - 1][j] |= (1 << 1);
					}
				}
				else { // vertical
					for(int j = ax; j < bx; ++j) {
						if(isSafe(j, ay))
							states[j][ay] |= (1 << 3);
						if(isSafe(j, ay - 1))
							states[j][ay - 1] |= (1 << 2);
					}
				}
			}
			
			for(int i = 0; i < 6; ++i) Arrays.fill(grid[i], 'F');
			bfs(sx, sy, ex, ey);
			print(ex, ey);
			out.println();
		}
		out.flush();
		out.close();
	}
	
	private static void print(int x, int y) {
		if(grid[x][y] == 'C') return;
		
		if(grid[x][y] == 'N') print(x + 1, y);
		if(grid[x][y] == 'S') print(x - 1, y);
		if(grid[x][y] == 'E') print(x, y - 1);
		if(grid[x][y] == 'W') print(x, y + 1);
		out.print(grid[x][y]);
	}

	private static void bfs(int sx, int sy, int ex, int ey) {
		Queue<Integer> q = new LinkedList<Integer>();
		grid[sx][sy] = 'C'; q.offer(sx); q.offer(sy);
		
		while(!q.isEmpty()) {
			int ux = q.poll(), uy = q.poll();
			
			if(ux == ex && uy == ey) return;
			
			if(((states[ux][uy] >> 0) & 1) != 1 && isSafe(ux - 1, uy) && grid[ux - 1][uy] == 'F') {
				q.offer(ux - 1); q.offer(uy); grid[ux - 1][uy] = 'N';
			}
			if(((states[ux][uy] >> 1) & 1) != 1 && isSafe(ux + 1, uy) && grid[ux + 1][uy] == 'F') {
				q.offer(ux + 1); q.offer(uy); grid[ux + 1][uy] = 'S';
			}
			if(((states[ux][uy] >> 2) & 1) != 1 && isSafe(ux, uy + 1) && grid[ux][uy + 1] == 'F') {
				q.offer(ux); q.offer(uy + 1); grid[ux][uy + 1] = 'E';
			}
			if(((states[ux][uy] >> 3) & 1) != 1 && isSafe(ux, uy - 1) && grid[ux][uy - 1] == 'F') {
				q.offer(ux); q.offer(uy - 1); grid[ux][uy - 1] = 'W';
			}
		}
	}

	static boolean isSafe(int x, int y) {
		return x >= 0 && y >= 0 && x < 6 && y < 6;
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