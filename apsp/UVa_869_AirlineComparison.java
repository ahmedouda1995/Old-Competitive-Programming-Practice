package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_869_AirlineComparison {

	static int N, M;
	static boolean first[][];
	static boolean second[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt();
			
			int a, b;
			
			first = new boolean[26][26];
			
			for(int i = 0; i < N; ++i) {
				a = sc.next().charAt(0) - 'A';
				b = sc.next().charAt(0) - 'A';
				
				first[a][b] = true;
				first[b][a] = true;
			}
			
			M = sc.nextInt();
			
			second = new boolean[26][26];
			
			for(int i = 0; i < M; ++i) {
				a = sc.next().charAt(0) - 'A';
				b = sc.next().charAt(0) - 'A';
				
				second[a][b] = true;
				second[b][a] = true;
			}
			
			floyd(true); floyd(false);
			
			if(compare())
				out.println("YES");
			else
				out.println("NO");
			
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}

	private static boolean compare() {
		for(int i = 0; i < 26; ++i) {
			for(int j = 0; j < 26; ++j) {
				if(first[i][j] != second[i][j]) return false;
			}
		}
		return true;
	}

	private static void floyd(boolean b) {
		boolean a[][] = (b)?first:second;
		
		for(int k = 0; k < 26; ++k)
			for(int i = 0; i < 26; ++i)
				for(int j = 0; j < 26; ++j)
					a[i][j] |= (a[i][k] & a[k][j]);
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