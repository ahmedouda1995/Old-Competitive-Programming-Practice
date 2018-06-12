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

public class UVa_532_DungeonMaster {

	static final int INF = (int) 1e9;
	static int L, R, C;
	static char grid[][][];
	static int dist[][][];
	static int dL[] = {1, -1, 0, 0, 0, 0};
	static int dR[] = {0, 0, 1, -1, 0, 0};
	static int dC[] = {0, 0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while((L = sc.nextInt()) != 0 | (R = sc.nextInt()) != 0 | (C = sc.nextInt()) != 0) {
			grid = new char[L][R][C];
			dist = new int[L][R][C];
			
			for(int i = 0; i < L; ++i)
				for(int j = 0; j < R; ++j)
					Arrays.fill(dist[i][j], INF);
			
			int sL = 0, sR = 0, sC = 0, eL = 0, eR = 0, eC = 0;
			String s;
			
			for(int i = 0; i < L; ++i) {
				for(int j = 0; j < R; ++j) {
					s = sc.nextLine();
					for(int k = 0; k < C; ++k) {
						char c = s.charAt(k);
						if(c == 'S') {
							sL = i; sR = j; sC = k;
							grid[i][j][k] = '.';
						}
						else if(c == 'E') {
							eL = i; eR = j; eC = k;
							grid[i][j][k] = '.';
						}
						else
							grid[i][j][k] = c;
					}
				}
				sc.nextLine();
			}
			
			int res = bfs(sL, sR, sC, eL, eR, eC);
			if(res == INF)
				out.println("Trapped!");
			else
				out.printf("Escaped in %d minute(s).\n", res);
		}
		
		out.flush();
		out.close();
	}

	private static int bfs(int sL, int sR, int sC, int eL, int eR, int eC) {
		Queue<Integer> q = new LinkedList<Integer>();
		dist[sL][sR][sC] = 0;
		q.offer(sL); q.offer(sR); q.offer(sC);
		int a, b, c;
		
		while(!q.isEmpty()) {
			sL = q.poll(); sR = q.poll(); sC = q.poll();
			
			if(sL == eL && sR == eR && sC == eC) return dist[eL][eR][eC];
			
			for(int i = 0; i < 6; ++i) {
				a = sL + dL[i]; b = sR + dR[i]; c = sC + dC[i];
				if(isSafe(a, b, c) && dist[a][b][c] == INF) {
					dist[a][b][c] = dist[sL][sR][sC] + 1;
					q.offer(a); q.offer(b); q.offer(c);
				}
			}
		}
		return dist[eL][eR][eC];
	}

	public static boolean isSafe(int i, int j, int k) {
		return (i >= 0 && j >= 0 && k >= 0 && i < L && j < R && k < C && grid[i][j][k] != '#');
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