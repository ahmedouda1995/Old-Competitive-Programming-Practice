package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_12467_Secretword {

	static int N;
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		int t = sc.nextInt();
		
		String s, rev;
		
		while(t-- > 0) {
			s = sc.nextLine();
			N = s.length();
			rev = reverse(s);
			lps(s + "@" + rev, rev);
		}

		out.flush();
		out.close();
	}
	
	private static void lps(String str, String rev) {
		char s[] = str.toCharArray();
		int b[] = new int[s.length];
		int i = 1, j = 0; b[0] = 0;
		
		while(i < b.length) {
			if(s[i] == s[j]) b[i++] = ++j;
			else if(j == 0) b[i++] = 0;
			else j = b[j - 1];
		}
		
		int max = 1, maxPos = s.length - 1;
		i = N + 1;
		
		while(i < s.length) {
			if(b[i] > max) {
				max = b[i];
				maxPos = i;
			}
			i++;
		}
		StringBuilder res = new StringBuilder();
		for(i = maxPos; max > 0; i--, max--) res.append(s[i]);
		out.println(res.toString());
	}

	private static String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = s.length() - 1; i >= 0; i--)
			sb.append(s.charAt(i));
		
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