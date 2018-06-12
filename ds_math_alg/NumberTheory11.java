package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NumberTheory11 {
	
	static int N;
	static int [] a, b, sol;
	static final int MAX = (int) 1e7;
	static int spf[] = new int[MAX + 1];
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static ArrayList<Long> m1, m2, mod;
	static ArrayList<Integer> p;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		m1 = new ArrayList<Long>();
		m2 = new ArrayList<Long>();
		mod = new ArrayList<Long>();
		
		for(int i = 2; i * i <= MAX; ++i)
		{
			if(spf[i] == 0)
			{
				for(int j = i * i; j <= MAX; j += i)
					spf[j] = (spf[j] == 0)?i:spf[j];
			}
		}
		for(int i = 2; i <= MAX; ++i) if(spf[i] == 0) { primes.add(i); spf[i] = i; }

		N = sc.nextInt();
		a = new int[N];
		b = new int[N];
		sol = new int[N];
		
		for(int i = 0; i < N; ++i)
			a[i] = sc.nextInt();
		for(int i = 0; i < N; ++i)
			b[i] = sc.nextInt();
		
		for(int i = 0; i < N; ++i)
			sol[i] = solve(a[i], b[i]);
		
		// combine using CRT
		p = new ArrayList<Integer>();
		for(int k : b) p.add(k);
		ArrayList<Integer> part = new ArrayList<Integer>();
		for(int k : sol) part.add(k);
		out.println(combine(part));
		
		out.flush();
		out.close();
	}
	
	static void clear()
	{
		m1.clear();
		m2.clear();
		mod.clear();
	}
	
	static int solve(int a, int m)
	{
		if(check(a, m)) return 0;
		if(gcd(a, m) == 1)
			return modPow(a, solve(a, phi(m)), m);
		else
		{
			phi(m);
			ArrayList<Integer> copy = (ArrayList<Integer>) p.clone();
			ArrayList<Integer> part = new ArrayList<Integer>();
			for(int next : p)
				part.add(solve(a, next));
			p = copy;
			return combine(part);
		}
	}
	
	static int combine(ArrayList<Integer> part)
	{
		clear();
		mod.add(1L * p.get(0));
		for(int i = 1; i < p.size(); ++i)
			mod.add(mod.get(i - 1) * p.get(i));
		long prev = p.get(0);
		m1.add(-1L); m2.add(-1L);
		for(int i = 1; i < p.size(); ++i)
		{
			extendedEuclid(prev, p.get(i));
			m1.add(x);
			m2.add(y);
			prev *= p.get(i);
		}
		Long MOD = mod.get(mod.size() - 1);
		long res = part.get(0);
		
		for(int i = 1; i < p.size(); ++i)
		{
			res = fix(fix(res * m2.get(i), MOD) * p.get(i), MOD);
			res = fix(res + fix(fix(1L * part.get(i) * m1.get(i), MOD) * mod.get(i - 1), MOD), MOD);
		}
		return (int)res;
	}
	
	static long fix(long num, long MOD)
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
	
	static boolean check(int a, int m) {
		long num = 1;
		while(num < m)
			num *= a;
		if(num == m)
			return true;
		return false;
	}

	static int gcd(int a, int b) {
		return b == 0?a:gcd(b, a % b);
	}

	static int modPow(int b, int p, int MOD)
	{
		if(p == 0) return 1;
		long ans = modPow(b, p >> 1, MOD);
		ans = ans * ans % MOD;
		if((p & 1) != 0)
			ans = ans * b % MOD;
		return (int) ans;
	}
	
	static int phi(int n)
	{
		int i = 0;
		int ans = 1, cnt, tmp;
		
		p = new ArrayList<Integer>();
		
		while(i < primes.size() && n > MAX)
		{
			if(1L * primes.get(i) * primes.get(i) > n)
				break;
			cnt = 0; tmp = 1;
			while(n % primes.get(i) == 0)
			{
				cnt++;
				tmp *= primes.get(i);
				n /= primes.get(i);
			}
			if(cnt > 0)
			{
				ans *= (tmp / primes.get(i)) * (primes.get(i) - 1); // check
				p.add(tmp);
			}
			i++;
		}
		
		if(n > MAX)
		{
			p.add(n);
			return ans * (n - 1);
		}
		while(n > 1)
		{
			int cur = spf[n]; cnt = 0; tmp = 1;
			while(n % cur == 0)
			{
				cnt++;
				tmp *= cur;
				n /= cur;
			}
			ans *= (tmp / cur) * (cur - 1);
			p.add(tmp);
		}
		
		return ans;
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