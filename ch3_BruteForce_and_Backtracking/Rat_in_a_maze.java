package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Rat_in_a_maze {
	static int moveX[] = {0, 0, -1, 1}, moveY[] = {1, -1, 0, 0};
	static char dir[] = {'R', 'L', 'U', 'D'};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		while(t-- > 0){
			int n = sc.nextInt();
			int matrix[][] = new int[n][n];
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					matrix[i][j] = sc.nextInt();
			
			ArrayList<String> res = new ArrayList<String>();
			solve(0, 0, matrix, res, "");
			for(int i = 0; i < res.size(); ++i){
				out.print((i != res.size() - 1)?res.get(i) + " ":res.get(i));
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isSafe(int x, int y, int [][] matrix){
		if(x >= 0 && x < matrix.length && y >= 0 && y < matrix.length && matrix[x][y] == 1)
			return true;
		else 
			return false;
	}
	
	private static void solve(int x, int y, int[][] matrix, ArrayList<String> res, String s) {
		if(x == matrix.length - 1 && y == matrix.length - 1)
			res.add(s);
		for(int i = 0; i < 4; ++i){
			int nextX = x + moveX[i], nextY = y + moveY[i];
			s += dir[i];
			int prev = matrix[x][y];
			matrix[x][y] = 0;
			if(isSafe(nextX, nextY, matrix))
				solve(nextX, nextY, matrix, res, s);
			matrix[x][y] = prev;
			s = s.substring(0, s.length() - 1);
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