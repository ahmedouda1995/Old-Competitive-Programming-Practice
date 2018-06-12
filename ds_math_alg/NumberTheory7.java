package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberTheory7 {
	
	static final int MAX = (int) 1e7;
	static int factors[] = new int[MAX + 1];
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		for(int i = 2; i <= MAX; ++i)
		{
			if(factors[i] == 0)
			{
				factors[i] = 1;
				primes.add(i);
				for(int j = i + i; j <= MAX; j += i)
					factors[j]++;
			}
		}
		int t = sc.nextInt(), res;
		boolean first = true;
		long num;

		while(t-- > 0)
		{
			num = sc.nextLong();
			if(num <= MAX)
				out.println(factors[(int)num]);
			else
			{
				res = 0;
				for(int prime : primes)
				{
					if(1L * prime * prime > num) break;
					first = true;
					while(num % prime == 0)
					{
						if(first) res++;
						first = false;
						num /= prime;
					}
				}
				if(num > 1)
					res++;
				out.println(res);
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