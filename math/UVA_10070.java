package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_10070 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		BigInteger n;
		BigInteger zero = BigInteger.valueOf(0);
		BigInteger four = BigInteger.valueOf(4);
		BigInteger fteen = BigInteger.valueOf(15);
		BigInteger ff = BigInteger.valueOf(55);
		BigInteger hundred = BigInteger.valueOf(100);
		BigInteger fhundred = BigInteger.valueOf(400);
		
		boolean first = true;
		
		while(sc.ready())
		{
			if(first) first = false; else out.println();
			n = new BigInteger(sc.nextLine());
			
			boolean printed = false;
			boolean leap = false;
			
			if(n.mod(fhundred).compareTo(zero) == 0 || (n.mod(four).compareTo(zero) == 0&& n.mod(hundred).compareTo(zero) != 0))
			{
				out.println("This is leap year.");
				printed = true;
				leap = true;
			}
			if(n.mod(fteen).compareTo(zero) == 0)
			{
				out.println("This is huluculu festival year.");
				printed = true;
			}
			if(leap && n.mod(ff).compareTo(zero) == 0)
			{
				out.println("This is bulukulu festival year.");
				printed = true;
			}
			
			if(!printed)
				out.println("This is an ordinary year.");
			
			
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
