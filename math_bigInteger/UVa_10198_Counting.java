package math_bigInteger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class UVa_10198_Counting {

	static BigInteger dp[] = new BigInteger[1001];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		dp[1] = BigInteger.valueOf(2); dp[2] = BigInteger.valueOf(5);
		dp[3] = BigInteger.valueOf(13);
		BigInteger two = BigInteger.valueOf(2);
		int n;
		
		for(int i = 4; i <= 1000; ++i) {
			dp[i] = dp[i - 1].multiply(two).add(dp[i - 2]).add(dp[i - 3]);
		}
		
		while(sc.ready()) {
			out.println(dp[sc.nextInt()]);
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