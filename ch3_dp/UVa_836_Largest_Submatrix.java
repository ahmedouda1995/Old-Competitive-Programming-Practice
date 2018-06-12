package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_836_Largest_Submatrix {

	static int a[][] = new int[25][25];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		
		while(t-- > 0) {
			
			String s;
			int i = 0, n;
			for(; (s = sc.nextLine()) != null && s.length() > 0; ++i)
				for(int j = 0; j < s.length(); ++j)
					a[i][j] = (s.charAt(j) == '1')?1:(int) -1e7;
			
			n = i;
			
			//for(int j = 0; j < n; ++j) System.out.println(Arrays.toString(a[j]));
			
			for(int j = 1; j < n; ++j)
				for(int k = 0; k < n; ++k)
					a[j][k] += a[j - 1][k];
			
			int max = a[0][0], res = (max == (int) -1e7)?0:1;
			//System.out.println();
			//for(int j = 0; j < n; ++j) System.out.println(Arrays.toString(a[j]));
			
			for(int j = 0; j < n; ++j)
				for(int k = j; k < n; ++k) {
					int sum = a[k][0];
					
					if(j > 0) sum -= a[j - 1][0];
					int st = 0, end = 0;
					if(sum > max) {
						max = sum;
						res = (k - j + 1) * (end - st + 1);
					}
					for(int l = 1; l < n; ++l) {
						int tmp = a[k][l];
						if(j > 0) tmp -= a[j - 1][l];
						
						if(sum + tmp > tmp) {
							end = l;
							sum += tmp;
						}
						else {
							sum = tmp;
							st = l; end = l;
						}
						
						if(sum > max) {
							max = sum;
							res = (k - j + 1) * (end - st + 1);
						}
					}
				}
			out.println(res);
			if(t > 0)
				out.println();
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