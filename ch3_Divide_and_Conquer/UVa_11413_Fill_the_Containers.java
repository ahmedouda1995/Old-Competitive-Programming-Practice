package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11413_Fill_the_Containers {

	
	static int [] a;
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		while((s = sc.nextLine()) != null){
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			a = new int[n];
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			out.println(solve(m));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int m) {
		int l = 1, h = 1000_000_000;
		int res = 0;
		int oldLow = 0, oldHigh = 1;
		while((h - l) > 0){
			if(oldLow == l && oldHigh == h)
				break;
			oldLow = l; oldHigh = h;
			int mid = l + (h - l) / 2;
			int ans = simulate(mid, m);
			if(ans == -1 || ans > m)
				l = mid;
			else {
				res = mid;
				h = mid;
			}
		}
		return res;
	}

	private static int simulate(int c, int m){
		int containers = 0;
		int cap = 0, i;
		for(i = 0; i < a.length && containers <= m; ++i){
			if(a[i] > c)
				return -1;
			if((c - cap) >= a[i])
				cap += a[i];
			else {
				containers++;
				cap = 0;
				--i;
			}
		}
		if(cap > 0)
			containers++;
		return containers;
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