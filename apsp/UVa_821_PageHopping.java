package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_821_PageHopping {

	static int N, INF = (int) 1e9;
	static int adj[][] = new int[100][100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int a, b, cases = 1;
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		while((a = sc.nextInt()) != 0 | (b = sc.nextInt()) != 0) {
			int k = 0;
			map.clear();
			
			for(int i = 0; i < 100; ++i) {
				Arrays.fill(adj[i], INF);
				adj[i][i] = 0;
			}
			
			do {
				if(!map.containsKey(a)) {
					map.put(a, k++);
				}
				if(!map.containsKey(b)) {
					map.put(b, k++);
				}
				adj[map.get(a)][map.get(b)] = 1;
			}while((a = sc.nextInt()) != 0 | (b = sc.nextInt()) != 0);
			
			N = k;
			floyd();
			
			double res = 0;
			int count = 0;
			
			for(int i = 0; i < k; ++i) {
				for(int j = 0; j < k; ++j) {
					if(i != j) {
						res += adj[i][j];
						count++;
					}
				}
			}
			out.printf("Case %d: average length between pages = %.3f clicks\n", cases++, (res / count));
			
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