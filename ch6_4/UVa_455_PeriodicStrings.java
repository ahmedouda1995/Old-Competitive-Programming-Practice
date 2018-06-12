package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_455_PeriodicStrings {

	static char pat[];
	static int lps[], N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			sc.nextLine();
			pat = sc.nextLine().toCharArray();
			N = pat.length;
			lps = new int[N];
			out.println(solve());
			if(t > 0) out.println();	
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		int i = 1, j = 0; lps[0] = 0;
		
		while(i < N) {
			if(pat[i] == pat[j]) lps[i++] = ++j;
			else if(j == 0) lps[i++] = 0;
			else j = lps[j - 1];
		}
		if(lps[N - 1] % (N - lps[N - 1]) == 0)
			return N - lps[N - 1];
		else
			return N;
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