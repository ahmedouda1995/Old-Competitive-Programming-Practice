package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10912_SimpleMindedHashing {

	static int L, S;
	static int memo[][][] = new int[27][27][352];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int cases = 1;
		for(int i = 0; i < 27; ++i)
			for(int j = 0; j < 27; ++j)
				Arrays.fill(memo[i][j], -1);
		
		while((L = sc.nextInt()) != 0 | (S = sc.nextInt()) != 0) {
			int res;
			if(L > 26 || S > 351) res = 0;
			else
				res = solve(1, L, S);
			out.printf("Case %d: %d\n", cases++, res);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int idx, int L, int sum) {
		if(idx == 27) {
			if(L == 0 && sum == 0) return 1;
			return 0;
		}
		if(L == 0) {
			if(sum == 0) return 1;
			return 0;
		}
		if(memo[idx][L][sum] != -1) return memo[idx][L][sum];
		int takeIt = 0;
		if(sum >= idx)
			takeIt = solve(idx + 1, L - 1, sum - idx);
		return memo[idx][L][sum] = takeIt + solve(idx + 1, L, sum);
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