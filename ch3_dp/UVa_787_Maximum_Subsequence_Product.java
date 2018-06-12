package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class UVa_787_Maximum_Subsequence_Product {

	static int [] a = new int[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		
		while((s = sc.nextLine()) != null) {
			
			while(!s.contains("-999999")) {
				s += " " + sc.nextLine();
			}
			
			StringTokenizer st = new StringTokenizer(s);
			String tmp;
			int n = 0;
			while(!(tmp = st.nextToken()).equals("-999999")) {
				a[n++] = Integer.parseInt(tmp);
			}
			
			BigInteger prod, max = new BigInteger(Integer.toString(-99999));
			for(int i = 0; i < n; ++i) {
				prod = BigInteger.ONE;
				for(int j = i; j < n; ++j) {
					prod = prod.multiply(BigInteger.valueOf(a[j]));
					
					if(max.compareTo(prod) < 0) max = prod;
				}
			}
			
			out.println(max);
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