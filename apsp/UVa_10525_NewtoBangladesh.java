package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10525_NewtoBangladesh {

	static int N, INF = (int) 1e9;
	static int dist[][] = new int[100][100];
	static int time[][] = new int[100][100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), m;
		
		while(t-- > 0) {
			N = sc.nextInt(); m = sc.nextInt();
			int a, b, c, d;
			
			for(int i = 1; i <= N; ++i) {
				Arrays.fill(dist[i], INF);
				Arrays.fill(time[i], INF);
				dist[i][i] = time[i][i] = 0;
			}
			
			for(int i = 0; i < m; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				c = sc.nextInt(); d = sc.nextInt();
				
				if(c < time[a][b] || (c == time[a][b] && d < dist[a][b])) {
					time[a][b] = time[b][a] = c;
					dist[a][b] = dist[b][a] = d;
				}
			}
			
			floyd();
			
			int q = sc.nextInt();
			
			while(q-- > 0) {
				a = sc.nextInt(); b = sc.nextInt();
				int res = dist[a][b];
				if(res == INF)
					out.println("No Path.");
				else
					out.printf("Distance and time to reach destination is %d & %d.\n", res, time[a][b]);
			}
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}

	private static void floyd() {
		for(int k = 1; k <= N; ++k)
			for(int i = 1; i <= N; ++i)
				for(int j = 1; j <= N; ++j) {
					if(time[i][j] > time[i][k] + time[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						time[i][j] = time[i][k] + time[k][j];
					}
					else if(time[i][j] == time[i][k] + time[k][j] && dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
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