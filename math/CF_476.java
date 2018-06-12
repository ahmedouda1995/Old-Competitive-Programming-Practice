package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CF_476 {

	static final int MOD = (int) 1e9 + 7;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int a, b;
		
		a = sc.nextInt();
		b = sc.nextInt();
		
		long res = 0;
		
		for(int k = 1; k <= a; ++k)
			res = (res + (1L * b * k + 1)) % MOD;
		
		int D = b - 1;
		
		out.println(1L * res * D % MOD * (D + 1) % MOD * modPow(2, MOD - 2) % MOD);
		
		out.flush();
		out.close();
	}
	
	private static long modPow(int b, int p) {
		if(p == 0) return 1;
		long tmp = modPow(b, p / 2);
		
		tmp = tmp * tmp % MOD;
		
		if(p % 2 == 1)
			tmp = tmp * b % MOD;
		return tmp;
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
