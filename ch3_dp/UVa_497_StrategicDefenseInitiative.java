package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_497_StrategicDefenseInitiative {

	static int L_id[] = new int[100000];
	static int p[] = new int[100000];
	static int a[] = new int[100000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		while(t-- > 0) {
			String s;
			int i = 0;
			while((s = sc.nextLine()) != null && s.length() > 0) {
				a[i++] = Integer.parseInt(s);
			}
			lis(i, out);
			if(t != 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void reconstruct(int lis_end, PrintWriter out) {
		Stack<Integer> s = new Stack<Integer>();
		
		int x = lis_end;
	    while(p[x] >= 0) {
	    	s.push(a[x]);
	    	x = p[x];
	    }
		out.println(a[x]);
		while(!s.isEmpty()) {
			out.println(s.pop());
		}
	}
	
	private static void lis(int n, PrintWriter out) {
		
		ArrayList<Integer> L = new ArrayList<Integer>();
		int lis = 0, lis_end = 0;
		
		for (int i = 0; i < n; ++i) {
			int pos = Collections.binarySearch(L, a[i]);
			
			if(pos < 0) pos = -(pos + 1);
			if(pos >= L.size()) L.add(a[i]);
			else L.set(pos, a[i]);
			
			L_id[pos] = i;
			p[i] = (pos > 0)?L_id[pos - 1]:-1;
			if (pos + 1 > lis) {
		        lis = pos + 1;
		        lis_end = i;
		    }
		}
		
		out.printf("Max hits: %d\n", lis);
		reconstruct(lis_end, out);
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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