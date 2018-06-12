package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class UVA_113 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		BigInteger p;
		
		while(sc.ready())
		{
			n = Integer.parseInt(sc.nextLine());
			p = new BigInteger(sc.nextLine());
			
			int lo = 1;
			int hi = (int) 1e9;
			int ans = -1;
			
			while(lo <= hi)
			{
				int mid = ((lo + hi) >> 1);
				if(BigInteger.valueOf(mid).pow(n).compareTo(p) < 0)
					lo = mid + 1;
				else if(BigInteger.valueOf(mid).pow(n).compareTo(p) > 0)
					hi = mid - 1;
				else
				{
					ans = mid;
					break;
				}
			}
			
			out.println(ans);
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
