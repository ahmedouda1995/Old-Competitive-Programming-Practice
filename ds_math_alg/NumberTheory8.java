package ds_math_alg;

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

public class NumberTheory8 {
	
	static final int MAX = (int)1e7;
	static int spf[] = new int[MAX + 1];
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static ArrayList<Long> res = new ArrayList<Long>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		for(int i = 2; i * i <= MAX; ++i)
		{
			if(spf[i] == 0)
			{
				for(int j = i * i; j <= MAX; j += i)
					spf[j] = (spf[j] == 0)?i:spf[j];
			}
		}
		for(int i = 2; i <= MAX; ++i) if(spf[i] == 0) { primes.add(i); spf[i] = i; }
		
		
		int t = sc.nextInt();
		long n;
		
		StringBuilder sb = new StringBuilder();
		
		while(t-- > 0)
		{
			n = sc.nextLong();
			res.clear();
			long ans = 1L;
			res.add(1L);
			
			if(n <= MAX)
				solve(n, ans);
			else
			{

				for(int prime : primes)
				{
					if(1L * prime * prime > n || prime == n) break;
					while(n > prime && n % prime == 0)
					{
						ans *= prime;
						res.add(ans);
						n /= prime;
					}
				}
				if(n <= MAX && spf[(int)n] != n)
					solve(n, ans);
			}
			
			sb.append(res.size());
			for(long i : res)
			{
				sb.append(" ");
				sb.append(i);
			}
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static void solve(long n, long ans)
	{
		while(n != spf[(int)n])
		{
			ans *= spf[(int)n];
			res.add(ans);
			n /= spf[(int)n];
		}
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