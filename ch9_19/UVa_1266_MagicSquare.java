package ch9_19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_1266_MagicSquare {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		int m, n;
		boolean first = true;
		
		while((s = sc.nextLine()) != null) {
			if(first) { first = false; }
			else out.println();
			m = Integer.parseInt(s);
			n = m * m;
			
			int i = 1;
			int r = 0, c = m / 2;
			int [][] mat = new int[m][m];
			while(i <= n) {
				if(mat[r][c] == 0) {
					mat[r][c] = i++;
					r = (r - 1 + m) % m; c = (c + 1) % m;
				}
				else {
					r = (r + 1) % m; c = (c - 1 + m) % m;
					r = (r + 1) % m;
				}
			}
			
			int sum = 0;
			for(int j = 0; j < m; ++j) sum += mat[0][j];
			
			out.printf("n=%d, sum=%d\n", m, sum);
			
			for(int j = 0; j < m; ++j) {
				for(int k = 0; k < m; ++k) {
					if(m == 3) out.print(" " + mat[j][k]);
					else if(m <= 9) out.printf(" %2d", mat[j][k]);
					else out.printf(" %3d", mat[j][k]);
				}
				out.println();
			}
		}
		
		
		
		out.flush();
		out.close();
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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