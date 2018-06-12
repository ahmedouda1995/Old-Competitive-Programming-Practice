package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10755_GarbageHeap {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), x, y, z;
		long [][][] a;
		
		while(t-- > 0) {
			a = new long[(x = sc.nextInt())][(y = sc.nextInt())][(z = sc.nextInt())];
			
			for(int i = 0; i < x; ++i)
				for(int j = 0; j < y; ++j)
					for(int k = 0; k < z; ++k) {
						a[i][j][k] = sc.nextLong();
						a[i][j][k] += (k == 0)?0:a[i][j][k - 1];
					}
			
			long tmp[][] = new long[x][y];
			long max = a[0][0][0];
			
			for(int top = 0; top < z; ++top) {
				for(int bot = top; bot < z; ++bot) {
					
					for(int i = 0; i < x; ++i)
						for(int j = 0; j < y; ++j) {
							tmp[i][j] = a[i][j][bot];
							tmp[i][j] -= (top == 0)?0:a[i][j][top - 1];
						}
				
					for(int j = 0; j < y; ++j)
						for(int i = 1; i < x; ++i)
							tmp[i][j] += tmp[i - 1][j];
					
					for(int i = 0; i < x; ++i)
						for(int j = i; j < x; ++j) {
							long next = tmp[j][0];
							next -= (i == 0)?0:tmp[i - 1][0];
							
							long sum = next;
							max = Math.max(max, sum);
							
							for(int k = 1; k < y; ++k) {
								
								next = tmp[j][k];
								next -= (i == 0)?0:tmp[i - 1][k]; 
								
								sum = Math.max(sum + next, next);
								max = Math.max(max, sum);
							}
						}
				}
			}
			out.println(max);
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
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