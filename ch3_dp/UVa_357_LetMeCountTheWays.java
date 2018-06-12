package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_357_LetMeCountTheWays {

	static int den[] = {1, 5, 10, 25, 50};
	static long dp[] = new long[30_001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n; dp[0] = 1;
		for(int i = 0; i < 5; ++i) {
			for(int j = den[i]; j <= 30000; ++j)
				dp[j] += dp[j - den[i]];
		}
		
		while(sc.ready()) {
			n = sc.nextInt();
			
			if(dp[n] == 1)
				out.printf("There is only 1 way to produce %d cents change.\n", n);
			else
				out.printf("There are %d ways to produce %d cents change.\n", dp[n], n);
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