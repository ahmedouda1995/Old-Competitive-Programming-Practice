package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A2 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		char s[] = sc.nextLine().toCharArray();
		
		int i = 0, j = s.length - 1;
		char res[] = new char[s.length];
		while(i < j)
		{
			if(s[i] == '?' && s[j] == '?')
				res[i] = res[j] = 'a';
			else if(s[i] == '?')
				res[i] = res[j] = s[j];
			else if(s[j] == '?')
				res[j] = res[i] = s[i];
			else if(s[i] == s[j])
			{
				res[i] = s[i++];
				res[j] = s[j--];
				continue;
			}
			else
			{
				System.out.println(-1);
				return;
			}
			i++;
			j--;
		}
		if(i == j)
		{
			if(s[i] == '?')
				res[i] = 'a';
			else
				res[i] = s[i];
		}
		out.println(new String(res));
		
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