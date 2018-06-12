package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_408 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int a, b;
		
		while(sc.ready())
		{
			a = sc.nextInt();
			b = sc.nextInt();
			
			int chk = gcd(a, b);
			
			out.printf("%10d%10d", a, b);
			if(chk == 1)
				out.printf("    %s\n\n", "Good Choice");
			else
				out.printf("    %s\n\n", "Bad Choice");
		}
		
		out.flush();
		out.close();
	}
	
	static int gcd(int a, int b) { if(b == 0) return a; else return gcd(b, a % b); }
	
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
