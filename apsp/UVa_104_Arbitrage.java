package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_104_Arbitrage {

	static double adj[][][];
	static int path[][][];
	static int N;
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		while(sc.ready()) {
			N = sc.nextInt();
			adj = new double[N][N][N];
			path = new int[N][N][N];
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					Arrays.fill(path[i][j], i);
				}
			}
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(i == j)
						adj[i][j][0] = 1.0;
					else
						adj[i][j][0] = sc.nextDouble();
				}
			}
			
			floyd();
			
		}
		
		out.flush();
		out.close();
	}

	private static void floyd() {

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