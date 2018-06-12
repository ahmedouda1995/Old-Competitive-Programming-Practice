package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11953_Battleships {

	static char grid[][] = new char[1000][1000];
	static int N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			N = sc.nextInt(); String s;
			for(int  i = 0; i < N; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < N; ++j) grid[i][j] = s.charAt(j);
			}
			int res = 0;
			for(int  i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(grid[i][j] == 'x') {
						if(j < N - 1 && (grid[i][j + 1] == '@' || grid[i][j + 1] == 'x')) {
							int k = j;
							while(k < N && grid[i][k] != '.') {
								grid[i][k] = '.'; k++;
							}
							j = k; res++;
						}
						else {
							int k = i;
							while(k < N && grid[k][j] != '.') {
								grid[k][j] = '.'; k++;
							}
							res++;
						}
					}
				}
			}
			out.printf("Case %d: %d\n", cases++, res);
		}
		
		out.flush();
		out.close();
	}
	
	public static int gcd(int n, int m) {
	    return (n % m) == 0? m : gcd(m, n % m);
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