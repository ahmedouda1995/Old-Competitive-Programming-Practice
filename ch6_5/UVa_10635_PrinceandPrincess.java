package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa_10635_PrinceandPrincess {

	static int a[] = new int[62501];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t =sc.nextInt(), n, p, q, next, pos, cases = 1;
		ArrayList<Integer> L;
		
		while(t-- > 0) {
			n = sc.nextInt(); p = sc.nextInt() + 1; q = sc.nextInt() + 1;
			for(int i = 1; i <= n * n; ++i) a[i] = -1;
			for(int i = 1; i <= p; ++i) a[sc.nextInt()] = i;
			L = new ArrayList<Integer>();
			while(q-- > 0) {
				next = sc.nextInt();
				if(a[next] != -1) {
					pos = Collections.binarySearch(L, a[next]);
					if(pos < 0) pos = -(pos + 1);
					if(pos == L.size()) L.add(a[next]);
					else L.set(pos, a[next]);
				}
			}
			out.printf("Case %d: %d\n", cases++, L.size());
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