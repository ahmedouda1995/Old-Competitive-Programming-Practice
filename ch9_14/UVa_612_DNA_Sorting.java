package ch9_14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_612_DNA_Sorting {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		int n, m;
		char a[], b[];
		String s;
		
		while(t-- > 0) {
			n = sc.nextInt(); m = sc.nextInt();
			a = new char[n];
			b = new char[n];
			Pair [] p = new Pair[m];
			
			for(int i = 0; i < m; ++i) {
				s = sc.nextLine();
				a = s.toCharArray();
				
				p[i] = new Pair(mergeInv(a, 0, n - 1, b), s);
			}
			
			Arrays.sort(p);
			
			for(int i = 0; i < m; ++i) out.println(p[i].s);
			
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int mergeInv(char[] a, int l, int h, char[] b) {
		int inv = 0;
		
		if(l < h) {
			int mid = (l + h) / 2;
			inv += mergeInv(a, l, mid, b);
			inv += mergeInv(a, mid + 1, h, b);
			inv += merge(a, l, mid, h, b);
		}
		
		return inv;
	}

	private static int merge(char[] a, int l, int mid, int h, char[] b) {
		int inv = 0;
		
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
	
	static class Pair implements Comparable<Pair>{
		int key;
		String s;
		
		public Pair(int key, String s) {
			this.key = key;
			this.s = s;
		}

		@Override
		public int compareTo(Pair p) {
			return this.key - p.key;
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