package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11475_ExtendtoPalindromes {

	static char s[];
	static int N;
	static int lps[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String str;
		
		while(sc.ready()) {
			str = sc.nextLine();
			N = str.length();
			StringBuilder sb = new StringBuilder(str);
			s = (rev(str) + "@" + str).toCharArray();
			lps = new int[s.length];
			int i = N - solve() - 1;
			while(i >= 0) sb.append(str.charAt(i--));
			out.println(sb.toString());
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		int i = 1, j = 0; lps[0] = 0;
		
		while(i < s.length) {
			if(s[i] == s[j]) lps[i++] = ++j;
			else if(j == 0) lps[i++] = 0;
			else j = lps[j - 1];
		}
		return lps[s.length - 1];
	}

	private static String rev(String str) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = str.length() - 1; i >= 0; --i)
			sb.append(str.charAt(i));
		return sb.toString();
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