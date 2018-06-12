package ch9_14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa10810UltraQuicksort {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, a[], b[];
		
		while((n = sc.nextInt()) != 0) {
			a = new int[n];
			b = new int[n];
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			
			out.println(mergeInv(a, 0, n - 1, b));
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
		}
	}
	
	private static long mergeInv(int[] a, int l, int h, int[] b) {
		long inv = 0;
		
		if(l < h) {
			int mid = (l + h) / 2;
			inv += mergeInv(a, l, mid, b);
			inv += mergeInv(a, mid + 1, h, b);
			inv += merge(a, l, mid, h, b);
		}
		
		return inv;
	}

	private static long merge(int[] a, int l, int mid, int h, int[] b) {
		long inv = 0;
		
		for(int i = l, j = mid + 1, k = l; k <= h; ++k) {
			if(i > mid)
				b[k] = a[j++];
			else if(j > h)
				b[k] = a[i++];
			else {
				if(a[i] <= a[j])
					b[k] = a[i++];
				else {
					b[k] = a[j++];
					inv += (mid - i + 1);
				}
			}
		}
		
		
		System.arraycopy(b, l, a, l, (h - l + 1));
		return inv;
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