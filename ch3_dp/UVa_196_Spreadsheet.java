package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_196_Spreadsheet {

	static int N, M;
	static int grid[][] = new int[1000][18279];
	static boolean calc[][];
	static TreeMap<Pair, String> map = new TreeMap<Pair, String>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		while(t-- > 0) {
			map.clear();
			M = sc.nextInt(); N = sc.nextInt();
			calc = new boolean[N + 1][M + 1];
			
			String s;
			
			for(int i = 1; i <= N; ++i) {
				for(int j = 1; j <= M; ++j) {
					if(!(s = sc.next()).startsWith("=")) {
						grid[i][j] = Integer.parseInt(s);
						calc[i][j] = true;
					}
					else {
						calc[i][j] = false;
						map.put(new Pair(i, j), s.substring(1));
					}
				}
			}
			
			for(int i = 1; i <= N; ++i) {
				for(int j = 1; j <= M; ++j) {
					if(!calc[i][j]) {
						solve(i, j);
					}
				}
			}
			
			for(int i = 1; i <= N; ++i) {
				out.print(grid[i][1]);
				for(int j = 2; j <= M; ++j) {
					out.print(" " + grid[i][j]);
				}
				out.println();
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int i, int j) {
		if(calc[i][j]) return grid[i][j];
		
		String tmp[] = map.get(new Pair(i, j)).replace('+', ',').split(",");
		grid[i][j] = 0;
		
		for(int k = 0; k < tmp.length; ++k) {
			Pair p = getIndex(tmp[k]);
			grid[i][j] += solve(p.a, p.b);
		}
		calc[i][j] = true;
		return grid[i][j];
	}

	private static Pair getIndex(String s) {
		String a, b;
		
		int j = 0, c = 0;
		while(Character.isLetter(s.charAt(j))) j++;
		a = s.substring(0, j); b = s.substring(j, s.length());
		j = 0;
		for(int k = a.length() - 1; k >= 0; k--, j++) {
			c += (a.charAt(k) - 'A' + 1) * Math.pow(26, j);
		}
		return new Pair(Integer.parseInt(b), c);
	}

	static class Pair implements Comparable<Pair> { 
		int a, b; Pair(int x, int y) { a = x; b = y; }
		
		public int compareTo(Pair p) {
			if(Integer.compare(this.a, p.a) == 0)
				return Integer.compare(this.b, p.b);
			return Integer.compare(this.a, p.a);
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