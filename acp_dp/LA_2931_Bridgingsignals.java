package acp_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class LA_2931_Bridgingsignals {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), n;
		int [] a = new int[40000];
		int [] dp = new int[40000];
		dp[0] = 1;
		
		while(t-- > 0) {
			n = sc.nextInt();
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			
			ArrayList<Integer> lis = new ArrayList<Integer>();
			
			for(int i = 0; i < n; ++i) {
				int pos = Collections.binarySearch(lis, a[i]);
				if(pos < 0) pos = -(pos + 1);
				
				if(pos == lis.size()) lis.add(a[i]);
				else lis.set(pos, a[i]);
			}
			
			out.println(lis.size());
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