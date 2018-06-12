package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class NumberTheory1 {
	
	static final int MAX = (int) 1e6, MOD = (int) 1e9 + 7;
	static TreeMap<Integer, Integer>[] powers;
	static int [] spf, classCount;
	static int lcm = 1;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		spf = new int[MAX + 1];
		powers = new TreeMap[MAX + 1];
		for(int i = 2; i <= MAX; ++i)
		{
			if(spf[i] == 0)
			{
				spf[i] = i;
				powers[i] = new TreeMap<Integer, Integer>();
				if(1L * i * i <= MAX)
				{
					for(int j = i * i; j <= MAX; j += i)
						spf[j] = (spf[j] == 0)?i:spf[j];
				}
			}
		}
		
		int q = sc.nextInt(), val, i, j;
		classCount = new int[q + 1];
		
		for(int k = 1; k <= q; ++k)
		{
			if(sc.next().charAt(0) == 'o')
			{
				val = sc.nextInt();
				classCount[k] = val;
				fix(val, true);
			}
			else
			{
				i = sc.nextInt();
				j = sc.nextInt();
				classCount[k] = classCount[i] + classCount[j];
				fix(classCount[i], false);
				fix(classCount[j], false);
				fix(classCount[k], true);
			}
			out.println(lcm);
		}
		
		out.flush();
		out.close();
	}
	
	private static void fix(int val, boolean add) {
		int copy, cnt;
		while(val > 1)
		{
			copy = val;
			cnt = 0;
			while(val % spf[copy] == 0)
			{
				cnt++;
				val /= spf[copy];
			}
			
			if(add)
				add(powers[spf[copy]], cnt, spf[copy]);
			else
				remove(powers[spf[copy]], cnt, spf[copy]);
		}
	}

	private static void remove(TreeMap<Integer, Integer> map, int cnt, int num) {
		if(map.get(cnt) == 1)
		{
			if(cnt == map.lastKey())
			{
				lcm = (int) (1L * lcm * modPow(modPow(num, cnt), MOD - 2) % MOD);
				map.remove(cnt);
				if(!map.isEmpty())
					lcm = (int) (1L * lcm * modPow(num, map.lastKey()) % MOD);
			}
			else
				map.remove(cnt);
		}
		else
			map.put(cnt, map.get(cnt) - 1);
	}

	private static void add(TreeMap<Integer, Integer> map, int cnt, int num) {
		if(!map.containsKey(cnt))
		{
			if(map.isEmpty())
				lcm = (int) (1L * lcm * modPow(num, cnt) % MOD);
			else if(cnt > map.lastKey())
			{
				lcm = (int) (1L * lcm * modPow(modPow(num, map.lastKey()), MOD - 2) % MOD);
				lcm = (int) (1L * lcm * modPow(num, cnt) % MOD);
			}
			map.put(cnt, 0);
		}
		map.put(cnt, map.get(cnt) + 1);
	}
	
	static int modPow(int b, int p)
	{
		if(p == 0)
			return 1;
		int ans = modPow(b, p >> 1);
		ans = (int) (1L * ans * ans % MOD);
		if((p & 1) == 1)
			ans = (int) (1L * ans * b % MOD);
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