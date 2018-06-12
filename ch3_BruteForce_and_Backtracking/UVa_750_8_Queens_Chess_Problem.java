package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_750_8_Queens_Chess_Problem {
	
	static int count = 1;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		while(t-- > 0){
			int posX = sc.nextInt(), posY = sc.nextInt();
			count  = 1;
			out.println("SOLN       COLUMN");
			out.println(" #      1 2 3 4 5 6 7 8");
			out.println();
			int [][] m = new int[8][8]; m[posX - 1][posY - 1] = 1;
			int res[] = new int[8]; res[posY - 1] = posX - 1;
			solve(m, 0, posY - 1, res, out);
			if(t != 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isSafe(int [][] m, int x, int y){
		for (int i = 0; i < y; i++)
			if(m[x][i] == 1)
				return false;
		for (int i = y + 1; i < 8; i++)
			if(m[x][i] == 1)
				return false;
		for (int i = x - 1, j = y - 1 ; i >= 0 && j >= 0; i--,j--)
			if(m[i][j] == 1)
				return false;
		for (int i = x + 1, j = y - 1 ; i < 8 && j >= 0; i++,j--)
			if(m[i][j] == 1)
				return false;
		for (int i = x + 1, j = y + 1 ; i < 8 && j < 8; i++,j++)
			if(m[i][j] == 1)
				return false;
		for (int i = x - 1, j = y + 1 ; i >= 0 && j < 8; i--,j++)
			if(m[i][j] == 1)
				return false;
		return true;
	}
	
	private static void solve(int[][] m, int i, int posY, int res[], PrintWriter out) {
		if(i == posY){
			solve(m, i + 1, posY, res, out);
			return;
		}
		if(i == 8){
			if(count <= 9)
				out.print(" " + count++ + "     ");
			else
				out.print(count++ + "     ");
			for (int j = 0; j < 8; j++) {
				out.print(" " + (res[j] + 1));
			}
			out.println();
			return;
		}
		
		for (int j = 0; j < res.length; j++) {
			if(isSafe(m, j, i)){
				m[j][i] = 1;
				res[i] = j;
				solve(m, i + 1, posY, res, out);
				m[j][i] = 0;
			}
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