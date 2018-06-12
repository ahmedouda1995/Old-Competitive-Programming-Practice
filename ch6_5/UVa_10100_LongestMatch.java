package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10100_LongestMatch {

	static int N, M;
	static String [] s1, s2;
	static int dp[][] = new int[1001][1001];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String str1, str2;
		char [] tmp;
		int cases = 1;
		
		while(sc.ready()) {
			tmp = sc.nextLine().toCharArray();
			for(int i = 0; i < tmp.length; ++i)
				if(!check(tmp[i])) tmp[i] = ' ';
			str1 = new String(tmp);
			s1 = str1.split(" "); N = s1.length;
			
			tmp = sc.nextLine().toCharArray();
			for(int i = 0; i < tmp.length; ++i)
				if(!check(tmp[i])) tmp[i] = ' ';
			str2 = new String(tmp);
			s2 = str2.split(" "); M = s2.length;
			if(str1.length() == 0 || str2.length() == 0)
				out.printf("%2d. Blank!\n", cases++);
			else
				out.printf("%2d. Length of longest match: %d\n", cases++, solve());
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean check(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
	}
	
	private static int solve() {
		for(int i = 1; i <= N; ++i)
			for(int j = 1; j <= M; ++j) {
				if(s1[i - 1].equals(s2[j - 1]))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		return dp[N][M];
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