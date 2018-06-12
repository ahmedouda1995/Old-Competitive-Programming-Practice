package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11195_Another_n_Queen_Problem {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int n;
		int cases = 1;
		while((n = sc.nextInt()) != 0){
			int [][] matrix = new int[n][n];
			for (int i = 0; i < matrix.length; i++) {
				String s = sc.nextLine();
				for (int j = 0; j < matrix.length; j++) {
					if(s.charAt(j) == '*')
						matrix[i][j] = -1;
				}
			}
			out.println("Case " + cases++ + ": " + solve(matrix, 0));
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean isSafe(int [][] m, int i, int j){
		for (int k = j - 1; k >= 0; k--) {
			if(m[i][k] == 1) return false;
		}
		for (int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--,l--) {
			if(m[k][l] == 1) return false;
		}
		for (int k = i + 1, l = j - 1; k < m.length && l >= 0; k++,l--) {
			if(m[k][l] == 1) return false;
		}
		return true;
	}
	
	private static int solve(int[][] matrix, int i) {
		int count = 0;
		if(i == matrix.length){
			//for (int j = 0; j < matrix.length; j++) System.out.println(Arrays.toString(matrix[j]));
			//System.out.println();
			count++;
			return count;
		}
		for (int j = 0; j < matrix.length; j++) {
			
			if((matrix[j][i] != -1) && isSafe(matrix, j, i)){
				matrix[j][i] = 1;
				count += solve(matrix, i + 1);
				matrix[j][i] = 0;
			}
		}
		return count;
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