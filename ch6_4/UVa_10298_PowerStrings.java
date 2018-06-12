package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10298_PowerStrings {

	static int N, M;
	static char pat[], text[];
	static int lps[] = new int[1000000];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		
		while(!(s = sc.nextLine()).equals(".")) {
			pat = s.toCharArray();
			text = (s + s).toCharArray();
			N = text.length; M = pat.length;
			
			kmpPreprocess();
			//int pos = kmpSearch();
			if(lps[M - 1] % (M - lps[M - 1]) == 0)
				out.println(M / (M - lps[M - 1]));
			else
				out.println(1);
		}
		
		out.flush();
		out.close();
	}
	
	private static void kmpPreprocess() {
		int i = 1, j = 0; lps[0] = 0;
		while(i < M) {
			if(pat[i] == pat[j])
				lps[i++] = j++ + 1;
			else if(j != 0)
				j = lps[j - 1];
			else
				lps[i++] = 0;
		}
	}

	private static int kmpSearch() {
		for(int i = 1, j = 0; i < N; ++i) {
			while(j > 0 && text[i] != pat[j]) j = lps[j - 1];
			if(text[i] == pat[j]) j++;
			if(j == M) {
				return i - M + 1;
			}
		}
		return -1;
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