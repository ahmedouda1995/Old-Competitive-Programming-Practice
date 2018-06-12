package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10446_TheMarriageInterview {

	static BigInteger memo[][] = new BigInteger[61][61];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n, back, cases = 1;
		for(int i = 0; i < 61; ++i) Arrays.fill(memo[i], BigInteger.valueOf(-1));
		
		while((n = sc.nextInt()) <= 60) {
			back = sc.nextInt();
			
			out.printf("Case %d: %s\n", cases++, solve(n, back).toString());
		}
		
		out.flush();
		out.close();
	}
	
	private static BigInteger solve(int n, int back) {
		if(n <= 1) return BigInteger.ONE;
		if(memo[n][back].compareTo(BigInteger.valueOf(-1)) != 0) return memo[n][back];
		BigInteger times = BigInteger.ONE;
		
		for(int i = 1; i <= back; ++i) {
			times = times.add(solve(n - i, back));
		}
		return memo[n][back] = times;
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