package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CF_284 {

	static int MOD;
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(new FileReader("input.txt"));
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		MOD = sc.nextInt();
		
		int res = 0;
		
		for(int x = 1; x < MOD; ++x)
		{
			if(check(x))
				res++;
		}
		
		out.println(res);
		
		out.flush();
		out.close();
	}
	
	static boolean check(int x)
	{
		for(int i = 1; i <= MOD - 2; ++i)
		{
			if(((modPow(x, i) - 1) % MOD + MOD) % MOD == 0)
				return false;
		}
		return ((modPow(x, MOD - 1) - 1) % MOD + MOD) % MOD == 0;
	}
	
	static long modPow(int b, int p)
	{
		if(p == 0) return 1L;
		
		long a = modPow(b, p / 2);
		
		a = a * a % MOD;
		
		if(p % 2 != 0)
			a = a * b % MOD;
		return a;
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
