package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11015_052Rendezvous {

	static int N, M, INF = (int) 1e9;
	static int adj[][] = new int[23][23];
	static String place[] = new String[23];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			M = sc.nextInt();
			int a, b, c;
			for(int i = 1; i <= N; ++i) place[i] = sc.nextLine();
			for(int i = 1; i <= N; ++i) {
				Arrays.fill(adj[i], INF);
				adj[i][i] = 0;
			}
			for(int i = 0; i < M; ++i) {
				a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();
				adj[a][b] = adj[b][a] = c;
			}
			
			floyd();
			
			int min = INF, minPos = 1;
			
			for(int i = 1; i <= N; ++i) {
				int sum = 0;
				for(int j = 1; j <= N; ++j) sum += adj[i][j];
				if(sum < min) {
					min = sum;
					minPos = i;
				}
			}
			
			out.printf("Case #%d : %s\n", cases++, place[minPos]);
		}
		
		out.flush();
		out.close();
	}

	private static void floyd() {
		for(int k = 1; k <= N; ++k)
			for(int i = 1; i <= N; ++i)
				for(int j = 1; j <= N; ++j)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
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