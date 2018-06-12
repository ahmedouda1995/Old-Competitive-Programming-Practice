package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11576_ScrollingSign {

	static int N, K;
	static String arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			K = sc.nextInt(); N = sc.nextInt();
			arr = new String[N];
			
			for(int i = 0; i < N; ++i) arr[i] = sc.nextLine();
			
			int len = K;
			
			for(int i = 1; i < N; ++i) {
				if(!arr[i].equals(arr[i - 1]))
					len += suffPref(arr[i], arr[i - 1]);
			}
			out.println(len);
		}
		
		out.flush();
		out.close();
	}
	
	private static int suffPref(String s1, String s2) {
		String s = s1 + "@" + s2;
		int lps[] = new int[s.length()];
		int i = 1, j = 0; lps[0] = 0;
		
		while(i < s.length()) {
			if(s.charAt(i) == s1.charAt(j)) lps[i++] = ++j;
			else if(j == 0) lps[i++] = 0;
			else j = lps[j - 1];
		}
		return K - lps[s.length() - 1];
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