package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_507_JillRidesAgain {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), route = 1;
		
		while(t-- > 0) {
			int n = sc.nextInt() - 1;
			
			int [] a = new int[n];
			
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			
			solve(a, route++, out);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve(int[] a, int route, PrintWriter out) {
		int max = a[0], sum = a[0];
		int currSt = 0, currEnd = 0;
		int st = 0, end = 0;
		for(int i = 1; i < a.length; ++i) {
			if(sum + a[i] < a[i]) {
				sum = a[i]; currSt = i; currEnd = i;
			}
			else {
				sum += a[i]; currEnd = i;
			}
			
			if(max <= sum) {
				if(max == sum) {
					if((currEnd - currSt) > (end - st)) {
						st = currSt; end = currEnd;
					}
				}
				else {
					max = sum; st = currSt; end = currEnd;
				}
			}
		}
		if(max <= 0)
			out.println("Route " + route + " has no nice parts");
		else
			out.println("The nicest part of route " + route + " is between stops " +
					(st + 1) + " and " + (end + 2));
	}

	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
		}
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