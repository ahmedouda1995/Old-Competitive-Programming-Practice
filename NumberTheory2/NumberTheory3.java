package NumberTheory2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberTheory3 {
	
	static long [] m, m1, m2, mod;
	static int n;
	static long MOD;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int q;
		n = sc.nextInt();
		q = sc.nextInt();
		
		m = new long[n];
		m1 = new long[n];
		m2 = new long[n];
		mod = new long[n];
		for(int i = 0; i < n; ++i)
			m[i] = sc.nextInt();
		mod[0] = m[0];
		for(int i = 1; i < n; ++i)
			mod[i] = mod[i - 1] * m[i];
		long prev = m[0];
		
		for(int i = 1; i < n; ++i)
		{
			extendedEuclid(prev, m[i]);
			m1[i] = x;
			m2[i] = y;
			prev *= m[i];
		}
		MOD = mod[n - 1];
		int val;
		
		while(q-- > 0)
		{
			if(sc.nextInt() == 1)
			{
				val = sc.nextInt();
				for(int i = 0; i < n; ++i)
					out.print(val % m[i] + " ");
				out.println();
				
			}
			else
			{
				int a[] = new int[n];
				for(int i = 0; i < n; ++i)
					a[i] = sc.nextInt();
				
				long res = a[0];
				
				for(int i = 1; i < n; ++i)
				{
					res = fix(fix(res * m2[i]) * m[i]);
					res = fix(res + fix(fix(1L * a[i] * m1[i]) * mod[i - 1]));
				}
					
				out.println(res);
			}
		}
		
		out.flush();
		out.close();
	}
	
	static long fix(long num)
	{
		return (num % MOD + MOD) % MOD;
	}
	
    static long x, y, d;
    static void extendedEuclid(long a, long b) {
        if(b == 0) { x = 1; y = 0; d = a; return; }

        extendedEuclid(b, a % b);
        long nx = y;
        long ny = x - y * (a / b);

        x = nx; y = ny;
    }
	
	static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}