package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_422_WordSearchWonder {

	static int N;
	static char grid[][] = new char[100][100];
	static ArrayList<Integer> chars[] = new ArrayList[26];
	static int dr[] = {1, 1, 0, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, -1, -1, -1};
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		String s;
		N = sc.nextInt();
		
		for(int i = 0; i < 26; ++i) chars[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < N; ++i) {
			s = sc.nextLine();
			for(int j = 0; j < s.length(); ++j) {
				grid[i][j] = s.charAt(j);
				chars[s.charAt(j) - 'A'].add(i);
				chars[s.charAt(j) - 'A'].add(j);
			}
		}
		while(!(s = sc.nextLine()).equals("0")) {
			solve(s);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve(String s) {
		int n = s.length();
		char ch = s.charAt(0);
		int a, b, ch1, ch2;
		for(int i = 0; i < chars[ch - 'A'].size(); i += 2) {
			ch1 = chars[ch - 'A'].get(i);
			ch2 = chars[ch - 'A'].get(i + 1);
			int c;
			
			for(int k = 0; k < 7; ++k) {
				a = ch1; b = ch2;
				int pos1 = a + dr[k] * n - dr[k], pos2 = b + dc[k] * n - dc[k];
				if(valid(pos1, pos2)) {
					for(c = 0; c < n; ++c, a += dr[k], b += dc[k]) {
						if(grid[a][b] != s.charAt(c)) { break; }
					}
					if(c == n) {
						out.printf("%d,%d %d,%d\n", ch1 + 1, ch2+ 1, pos1 + 1, pos2 + 1);
						return;
					}
				}
			}
		}
		out.println("Not found");
	}

	private static boolean valid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
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