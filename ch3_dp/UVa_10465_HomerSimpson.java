package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10465_HomerSimpson {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int m, n, t;
		
		while(sc.ready()) {
//			m = sc.nextInt(); n = sc.nextInt(); t = sc.nextInt();
			
//			int a = Math.min(m,  n), b = Math.max(m, n);
//			
//			int max = 0, burgers = 0;
//			
//			for(int i = 0; i * a <= t; ++i) {
//				int j = (t - i * a) / b;
//				
//				int tmp = i * a + j * b;
//				
//				if(tmp >= max && i + j >= burgers) {
//					max = tmp;
//					burgers = i + j;
//				}
//			}
//			
//			out.print(burgers);
//			
//			if(max < t)
//				out.println(" " + (t - max));
//			else
//				out.println();
			
			//out.println(dp[t] == t?dp[t]);
			while(sc.ready()) {
				m = sc.nextInt(); n = sc.nextInt(); t = sc.nextInt();
				
				int dp[] = new int[t + 1]; Arrays.fill(dp, -1); dp[0] = 0;
				
				for(int i = 1; i <= t; ++i) {
					int xx = 0, yy = 0;
					if(i - n >= 0) {
						xx = 1 + dp[i - n];
					}
					if(i - m >= 0) {
						yy = 1 + dp[i - m];
					}
					if(Math.max(xx, yy) > 0) {
						dp[i] = Math.max(xx, yy);
					}
				}
				
				if(dp[t] >= 0) out.println(dp[t]);
				else {
					int i = t - 1;
					while(dp[i] < 0) i--;
					out.println(dp[i] + " " + (t - i));
				}
			}
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