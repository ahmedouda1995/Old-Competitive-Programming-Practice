package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_10354_AvoidingYourBoss {

	static int INF = (int) 1e9, P;
	static int boss[][] = new int[101][101];
	static int me[][] = new int[101][101];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int R, BH, OF, YH, M;
		
		while(sc.ready()) {
			
			P = sc.nextInt(); R = sc.nextInt(); BH = sc.nextInt();
			OF = sc.nextInt(); YH = sc.nextInt(); M = sc.nextInt();
			
			for(int i = 1; i <= P; ++i) {
				Arrays.fill(boss[i], INF);
				Arrays.fill(me[i], INF);
				boss[i][i] = me[i][i] = 0;
			}
			int a, b, c;
			for(int i = 0; i < R; ++i) {
				a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();
				boss[a][b] = boss[b][a] = me[a][b] = me[b][a] = c;
			}
			
			floyd(true);
			
			boolean vis[] = new boolean[P + 1];
			
			for(int i = 1; i <= P; ++i) {
				if(boss[BH][OF] == boss[BH][i] + boss[i][OF]) {
					vis[i] = true;
				}
			}
			
			for(int i = 1; i <= P; ++i) {
				if(vis[i]) {
					for(int j = 1; j <= P; ++j) {
						me[i][j] = me[j][i] = INF;
					}
				}
			}
			
			floyd(false);
			
			out.println((me[YH][M] == INF)?"MISSION IMPOSSIBLE.":me[YH][M]);
		}
		
		out.flush();
		out.close();
	}

	private static void floyd(boolean isBoss) {
		int [][] a = (isBoss)?boss:me;
		for(int k = 1; k <= P; ++k)
			for(int i = 1; i <= P; ++i)
				for(int j = 1; j <= P; ++j)
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
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