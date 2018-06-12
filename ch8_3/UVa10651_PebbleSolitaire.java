package ch8_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10651_PebbleSolitaire {

	static int memo[] = new int[1 << 12];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		String s;
		while(t-- > 0) {
			int mask = 0;
			s = sc.nextLine();
			for(int i = 0; i < 12; ++i) {
				if(s.charAt(i) == 'o') mask |= (1 << i);
			}
			Arrays.fill(memo, -1);
			out.println(solve(mask));
		}

		out.flush();
		out.close();
	}
	
	private static int solve(int mask) {
		if(memo[mask] != -1) return memo[mask];
		
		int min = counOnes(mask);
		
		for(int i = 0; i < 12; ++i) {
			if(((mask >> i) & 1) == 1 && isValid(mask, i, 2))
				min = Math.min(min, solve(clearBit(clearBit(setBit(mask, i + 2), i + 1), i)));
			if(((mask >> i) & 1) == 1 && isValid(mask, i, -2))
				min = Math.min(min, solve(clearBit(clearBit(setBit(mask, i - 2), i - 1), i)));
		}
		
		return memo[mask] = min;
	}

	static int clearBit(int mask, int i) {
		return (mask & ~(1 << i));
	}
	
	static int setBit(int mask, int i) {
		return (mask | (1 << i));
	}
	
	static boolean isValid(int mask, int idx, int offset) {
		int i = idx + offset;
		if(i < 0 || i >= 12) return false;
		
		int middle = idx + offset / 2;
		
		if(((mask >> middle) & 1) == 1 && ((mask >> i) & 1) == 0)
			return true;
		return false;
	}
	
	static int counOnes(int mask) {
		int res = 0;
		while(mask > 0) { if(mask % 2 == 1) res++; mask /= 2; }
		return res;
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