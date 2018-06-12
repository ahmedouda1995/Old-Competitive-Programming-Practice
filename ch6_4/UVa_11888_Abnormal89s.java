package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11888_Abnormal89s {

	static String str;
	static String rev;
	static char s[];
	static int lps[];
	static int lps2[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			str = sc.nextLine();
			rev = rev(); 
			s = (str + "@" + rev).toCharArray();
			
			if(str.length() == 1)
				out.println("palindrome");
			else {
				solve1();
				int posEnd = lps[s.length - 1];
				int posSt = lps[str.length() - 1];
				//System.out.println(Arrays.toString(lps));
				
				if(str.length() - posEnd == 0) {
					if(palin(str.substring(0, str.length() - posSt)) && palin(str.substring(str.length() - posSt, str.length())))
						out.println("alindrome");
					else
						out.println("palindrome");
				}
				else {
					solve2();
					if((palin(str.substring(0, posEnd)) && palin(str.substring(posEnd, str.length())))) {
						out.println("alindrome");
					}
					else if((palin(str.substring(0, str.length() - lps2[s.length - 1])) && palin(str.substring(str.length() - lps2[s.length - 1], str.length()))))
						out.println("alindrome");
					else
						out.println("simple");
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve2() {
		char [] a = (rev + "@" + str).toCharArray();
		lps2 = new int[a.length];
		int i = 1, j = 0; lps[0] = 0;
		
		while(i < a.length) {
			if(a[i] == a[j]) lps2[i++] = ++j;
			else if(j == 0) lps2[i++] = 0;
			else j = lps2[j - 1];
		}
	}

	static boolean palin(String a) {
		int i = 0, j = a.length() - 1;
		
		while(i < j) {
			if(a.charAt(i) != a.charAt(j))
				return false;
			i++; j--;
		}
		return true;
	}
	
	private static void solve1() {
		lps = new int[s.length];
		int i = 1, j = 0; lps[0] = 0;
		
		while(i < s.length) {
			if(s[i] == s[j]) lps[i++] = ++j;
			else if(j == 0) lps[i++] = 0;
			else j = lps[j - 1];
		}
	}

	private static String rev() {
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