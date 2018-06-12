package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11456_Trainsorting {

	// mn hena (index) shofly aktar 7aga bto3od t2el w aktar 7aga bto3od tzeed not bitonic
	// very important
	static int [] a = new int[2000];
	static int [] dpAsc = new int[2000];
	static int [] dpDesc = new int[2000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			int n = sc.nextInt();
			if(n == 0) out.println(0);
			else {
				for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
				
				lisAsc(n); lisDesc(n);
				System.out.println(Arrays.toString(a));
				System.out.println(Arrays.toString(dpAsc));
				System.out.println(Arrays.toString(dpDesc));
				
				int max  = 0;
				
				for(int i = 0; i < n; ++i) {
					max = Math.max(dpAsc[i] + dpDesc[i], max);
				}
				out.println(max - 1);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int lisAsc(int n) {
		dpAsc[n - 1] = 1; int max = 1;
		for(int i = n - 2; i >= 0; --i) {
			int maxSoFar = 0;
			for(int j = i + 1; j < n; ++j) {
				if(a[i] < a[j]) maxSoFar = Math.max(maxSoFar, dpAsc[j]);
			}
			dpAsc[i] = maxSoFar + 1;
			max = Math.max(max, dpAsc[i]);
		}
		return max;
	}
	
	private static int lisDesc(int n) {
		dpDesc[n - 1] = 1; int max = 1;
		for(int i = n - 2; i >= 0; --i) {
			int maxSoFar = 0;
			for(int j = i + 1; j < n; ++j) {
				if(a[i] > a[j])
					maxSoFar = Math.max(maxSoFar, dpDesc[j]);
			}
			dpDesc[i] = maxSoFar + 1;
			max = Math.max(max, dpDesc[i]);
		}
		return max;
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