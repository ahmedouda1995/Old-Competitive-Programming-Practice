package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_257_Palinwords {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		StringTokenizer st;
		
		while(sc.ready()) {
			st = new StringTokenizer(sc.nextLine());
			
			while(st.hasMoreTokens()) {
				s = st.nextToken();
				if(palinWord(s))
					out.println(s);
			}
		}

		out.flush();
		out.close();
	}
	
	private static boolean palinWord(String s) {
		if(s.length() < 4) return false;
		int n = s.length();
		boolean dp[][] = new boolean[n][n];
		
		for(int i = 0; i < n; ++i) dp[i][i] = true;
		for(int i = 0; i < n - 1; ++i)
			if(s.charAt(i) == s.charAt(i + 1))
				dp[i][i + 1] = true;
		
		int j;
		boolean first = false;
		String a = null;
		
		for(int l = 3; l <= n; ++l) {
			for(int i = 0; i < n - l + 1; ++i) {
				j = i + l - 1;
				
				if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
					dp[i][j] = true;
					if(!first) {
						a = s.substring(i, j + 1);
						first = true;
					}
					else if(!s.substring(i, j + 1).contains(a))
						return true;
				}
			}
		}
		return false;
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