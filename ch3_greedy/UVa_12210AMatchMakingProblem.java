package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_12210AMatchMakingProblem {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int b, s, cases = 1;
		while((b = sc.nextInt()) != 0 && (s = sc.nextInt()) != 0) {
			int smallest = 70;
			
			for(int i = 0; i < b; ++i) smallest = Math.min(smallest, sc.nextInt());
			for(int i = 0; i < s; ++i) sc.nextInt();
			
			if(b > s)
				out.println("Case " + cases++ + ": " +  (b - s) + " " + smallest);
			else
				out.println("Case " + cases++ + ": " + 0);
		}
		
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