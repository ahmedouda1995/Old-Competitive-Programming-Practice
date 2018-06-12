package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10452_Marcushelp {

	static char grid[][] = new char[8][8];
	static char orig[][] = new char[8][8];
	static int dr[] = {-1, 0, 0};
	static int dc[] = {0, -1, 1};
	static int posX, posY;
	static int n, m;
	static String comp = "@IEHOVA#";
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		String [] s = {"forth", "right", "left"};
		
		while(t-- > 0) {
			n = sc.nextInt();  m = sc.nextInt();
			
			for(int i = 0; i < n; ++i)
				grid[i] = sc.nextLine().toCharArray();
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					orig[i][j] = grid[i][j];
			
			int x = -1, y = -1;
			
			for(int i = 0; i < m; ++i) {
				if(grid[n - 1][i] == '@') {
					x = n - 1;
					y = i;
				}
			}
			
			solve(x, y, 'I');
			
			//for(int i = 0; i < n; ++i) System.out.println(Arrays.toString(grid[i]));
			
			grid[posX][posY] = 'x';
			dr[0] = 1;
			
			StringBuilder sb = new StringBuilder();
			int a, b, k = comp.length() - 2;
			
			while(posX != x || posY != y) {
				for(int i = 0; i < 3; ++i) {
					a = posX + dr[i];
					b = posY + dc[i];
					
					if(isValid(a, b) && grid[a][b] == 'x' && orig[a][b] == comp.charAt(k)) {
						sb.insert(0, s[i] + " ");
						posX = a; posY = b;
						k--;
						break;
					}
				}
			}
			
			dr[0] = -1;
			
			System.out.println(sb.toString().trim());
		}
		
		out.flush();
		out.close();
	}
	
	static boolean solve(int x, int y, char nextChar) {
		if(grid[x][y] == '#') {
			posX = x;
			posY = y;
			return true;
		}
		
		grid[x][y] = 'x';
		
		int a, b;
		
		for(int i = 0; i < 3; ++i) {
			a = x + dr[i];
			b = y + dc[i];
			
			if(isValid(a, b) && grid[a][b] != 'x' && grid[a][b] == nextChar) {
				if(solve(a, b, getNext(nextChar)))
					return true;
			}
		}
		
		return false;
	}
	
	static char getNext(char c) {
		for(int i = 0; i < comp.length() - 1; ++i) {
			if(c == comp.charAt(i))
				return comp.charAt(i + 1);
		}
		return '#';
	}
	
	private static boolean isValid(int a, int b) {
		return a >= 0 && b >= 0 && a < n && b < m;
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