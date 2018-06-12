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

public class NumberTheory5 {
	
	static long MAX = 1000_000;
	static boolean isPrime[] = new boolean[(int)MAX + 1];
	static ArrayList<Long> primes = new ArrayList<Long>();
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		long n = sc.nextLong();
		ArrayList<Long> arr = new ArrayList<Long>();
		
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(long i = 2; i * i <= n; ++i)
		{
			if(isPrime[(int)i])
			{
				primes.add(i);
				for(long j = i * 2; j * j <= MAX * MAX; j += i)
					isPrime[(int)j] = false;
			}
		}
		
		ArrayList<Long> tmp = new ArrayList<Long>();
		
		for(int i = 1; 1L * i * i <= n; ++i)
		{
			if(n % i != 0) continue;
			arr.add((long)i);
			tmp.add(n / i);
		}
		
		
		for(int i = tmp.size() - 1; i >= 0; i--)
			arr.add(tmp.get(i));
		
		int ans = 0;
		long prev = 1;
		for(long num : arr)
		{
			if(isPrime(num - prev - 1))
				ans++;
			prev = num;
		}
		
		out.println(ans);
		out.flush();
		out.close();
	}

	private static boolean isPrime(long diff) {
		if(diff < 2) return false;
		if(diff > MAX)
		{
			for(long prime : primes)
				if(diff % prime == 0)
					return false;
			return true;
		}
		else
			return isPrime[(int)diff];
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