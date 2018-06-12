package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// pass on a small one and leave the other small for the way back if smalls are consecutive


public class UVa_11157_Dynamic_Frog {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), N, D, cases = 1;
		
		while(t-- > 0) {
			N = sc.nextInt(); D = sc.nextInt();
			Pair [] a = new Pair[N + 2];
			a[0] = new Pair('B', 0); a[N + 1] = new Pair('B', D);
			for(int i = 1; i <= N; ++i) {
				String [] tmp = sc.next().split("-");
				a[i] = new Pair(tmp[0].charAt(0), Integer.parseInt(tmp[1]));
			}
			int prev = 0;
			int max = 0;
			for(int i = 1; i < a.length; ++i) {
				if(a[i].c == 'B') {
					max = Math.max(max, a[i].n - prev);
					prev = a[i].n;
				}
				else {
					if(a[i - 1] != null) {
						max = Math.max(max, a[i].n - prev);
						prev = a[i].n;
						a[i] = null;
					}
				}
			}
			prev = 0;
			for(int i = 1; i < a.length; ++i)
				if(a[i] != null) {
					max = Math.max(max, a[i].n - prev);
					prev = a[i].n;
				}
			
			out.println("Case " + cases++ + ": " + max);
		}
		
		out.flush();
		out.close();
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