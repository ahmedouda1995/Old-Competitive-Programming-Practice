package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10803_ThunderMountain {

	static int N;
	static double INF = 1e9;
	static double adj[][] = new double[100][100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		int posX[] = new int[100];
		int posY[] = new int[100];
		
		while(t-- > 0) {
			N = sc.nextInt();
			for(int i = 0; i < N; ++i) {
				posX[i] = sc.nextInt();
				posY[i] = sc.nextInt();
				Arrays.fill(adj[i], INF);
				adj[i][i] = 0;
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = i + 1; j < N; ++j) {
					double d = dist(posX[i], posY[i], posX[j], posY[j]);
					if(d <= 10.0) {
						adj[i][j] = d; adj[j][i] = d;
					}
				}
			}
			
			floyd();
			
			double max = 0;
			
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					max = Math.max(max, adj[i][j]);
			
			out.printf("Case #%d:\n", cases++);
			if(max == INF)
				out.println("Send Kurdy");
			else
				out.printf("%.4f\n", max);
			out.println();
		}
		
		out.flush();
		out.close();
	}

	private static void floyd() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
	}

	private static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
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