package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_11906_KnightinWarGrid {

	static int [][] mat;
	static boolean [][] vis;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		int a, b, m, n, cases = 1;
		
		while(t-- > 0) {
			a = sc.nextInt(); b = sc.nextInt(); m = sc.nextInt(); n = sc.nextInt();
			mat = new int[a][b];
			vis = new boolean[a][b];
			int k = sc.nextInt();
			
			for(int i = 0; i < k; ++i) mat[sc.nextInt()][sc.nextInt()] = -2;
			
			bfs(m, n, a, b);
			
			int even = 0, odd = 0;
			
			for(int i = 0; i < a; ++i) {
				for (int j = 0; j < b; j++) {
					if(vis[i][j]) {
						if(mat[i][j] % 2 == 0)
							even++;
						else
							odd++;
					}
				}
			}
			out.printf("Case %d: %d %d\n", cases++, even, odd);
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int m, int n, int a, int b) {
		Queue<Pair> q = new LinkedList<Pair>();
		
		int [][] mov = {{m, n}, {n, m}, {m, -n}, {n, -m}, {-n, -m}, {-m, -n}, {-n, m}, {-m, n}};
		TreeSet<Pair> set = new TreeSet<Pair>();
		
		q.add(new Pair(0, 0));
		vis[0][0] = true;
		
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			set.clear();
			for(int i = 0; i < 8; ++i) {
				if(isSafe(p.x + mov[i][0], p.y + mov[i][1], a, b) && mat[p.x + mov[i][0]][p.y + mov[i][1]] != -2) {
					set.add(new Pair((p.x + mov[i][0]), (p.y + mov[i][1])));
				}
			}
			for(Pair pair : set) {
				mat[pair.x][pair.y]++;
				if(!vis[pair.x][pair.y]) {
					vis[pair.x][pair.y] = true;
					q.add(pair);
				}
			}
		}
	}
	
	private static boolean isSafe(int x, int y, int a, int b) {
		if(x >= 0 && y >= 0 && x < a && y < b)
			return true;
		return false;
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