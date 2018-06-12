package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_10131_IsBiggerSmarter {

	static Pair [] a = new Pair[10000];
	static int [] dp = new int[10000];
	static int [] p = new int[10000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n; String s;
		int i = 0;
		while((s = sc.nextLine()) != null) {
			StringTokenizer st = new StringTokenizer(s);
			a[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1);
			i++;
		}
		Arrays.sort(a, 0, i);
		
		lis(i, out);
		
		out.flush();
		out.close();
	}
	
	private static void lis(int n, PrintWriter out) {
		int max = 1, maxIndex = 0;
		dp[0] = 1;
		p[0] = 0;
		for(int i = 1; i < n; ++i) {
			int maxSoFar = 0, maxSoFarIndex = i;
			for(int j = i - 1; j >= 0; --j) {
				if(a[i].w > a[j].w && a[i].s < a[j].s) {
					if(dp[j] > maxSoFar) {
						maxSoFar = dp[j];
						maxSoFarIndex = j;
					}
				}
			}
			dp[i] = maxSoFar + 1;
			p[i] = maxSoFarIndex;
			if(max < dp[i]) {
				max = dp[i];
				maxIndex = i;
			}
		}
		
		out.println(max);
		reconstruct(maxIndex, out);
	}

	private static void reconstruct(int i, PrintWriter out) {
		Stack<Integer> s = new Stack<Integer>();
		while(p[i] != i) {
			s.push(a[i].i);
			i = p[i];
		}
		out.println(a[i].i);
		while(!s.isEmpty()) {
			out.println(s.pop());
		}
	}

	static class Pair implements Comparable<Pair>{
		int w, s, i;
		
		public Pair(int w, int s, int i) {
			this.w = w;
			this.s = s;
			this.i = i;
		}

		@Override
		public int compareTo(Pair p) {
			return this.w - p.w;
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