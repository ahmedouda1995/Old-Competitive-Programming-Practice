package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_436_ArbitrageII {

	static int N;
	static double adj[][];
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			map.clear();
			adj = new double[N][N];
			
			for(int i = 0; i < N; ++i) {
				map.put(sc.next(), i);
			}
			
			for(int i = 0; i < N; ++i) adj[i][i] = 1.0;
			
			int m = sc.nextInt();
			int a, b;
			double c;
			
			while(m-- > 0) {
				a = map.get(sc.next());
				c = sc.nextDouble();
				b = map.get(sc.next());
				
				adj[a][b] = c;
			}
			
			out.printf("Case %d: ", cases++);
			
			if(floyd()) 
				out.println("Yes");
			else
				out.println("No");
			
		}
		
		out.flush();
		out.close();
	}

	private static boolean floyd() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.max(adj[i][j], adj[i][k] * adj[k][j]);
					if(i == j && adj[i][i] > 1.0) return true;
				}
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