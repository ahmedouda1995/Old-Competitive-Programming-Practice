package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class IncExcExample {
	
	static int n, m, k;
	static int mask;
	static int endMask;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		int [] x, y;
		int [] a = new int[20], b = new int[20];
		
		while(t-- > 0)
		{
			m = sc.nextInt();
			n = sc.nextInt();
			k = sc.nextInt();
			
			x = new int[k];
			y = new int[k];
			
			for(int i = 0; i < k; ++i) { x[i] = sc.nextInt(); y[i] = sc.nextInt(); }
			
			mask = 1;
			endMask = (1 << k);
			int i, j;
			long res = 0;
			
			while(mask < endMask)
			{
				i = 0; j = 0;
				int tmp = mask;
				while(tmp > 0)
				{
					if((tmp & 1) == 1) { a[j] = x[i]; b[j++] = y[i]; }
					i++;
					tmp >>= 1;
				}
				
				int minX = a[0], maxX = a[0], minY = b[0], maxY = b[0];
				
				for(i = 1; i < j; ++i)
				{
					minX = Math.min(minX, a[i]);
					maxX = Math.max(maxX, a[i]);
					minY = Math.min(minY, b[i]);
					maxY = Math.max(maxY, b[i]);
				}
				
				res += (1L * (minX) * (m + 1 - maxX) * (minY) * (n + 1 - maxY)) * ((j % 2 == 0)?-1:1);
				mask++;
			}
			out.println(1L * n * (n + 1) * m * (m + 1) / 4 - res);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int a, b;
		
		public Pair(int x, int y) {
			a = x; b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			return (a == p.a)?b - p.b:a - p.a; 
		}
		
		@Override
		public String toString() {
			return a + " " + b;
		}
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