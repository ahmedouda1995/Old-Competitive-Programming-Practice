package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_990_Divingforgold_rev {

	static int t, w, n;
	static int d[], v[];
	static int memo[][] = new int[100][1001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		boolean first = true;
		
		while(sc.ready()) {
			if(first) first = false; else out.println();
			t = sc.nextInt(); w = sc.nextInt();
			n = sc.nextInt();
			d = new int[n]; v = new int[n];
			for(int i = 0; i < n; ++i) { d[i] = sc.nextInt(); v[i] = sc.nextInt(); }
			for(int i = 0; i < n; ++i) for(int j = 0; j <= t; ++j) memo[i][j] = -1;
			int val = solve(0, t);
			out.println(val);
			
			int item = 0, time = t;
			
			ArrayList<Integer> arr = new ArrayList<Integer>();
			
			while(val > 0) {
				if(memo[item][time] != memo[item + 1][time]) {
					arr.add(item);
					time -= (3 * w * d[item]);
					val -= v[item];
				}
				item++;
			}
			out.println(arr.size());
			for(int i : arr) out.println(d[i] + " " + v[i]);
			sc.nextLine();
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int item, int t) {
		if(t <= 0) return 0;
		if(item == n) return 0;
		if(memo[item][t] != -1) return memo[item][t];
		
		if(3 * w * d[item] <= t) {
			return memo[item][t] = Math.max(solve(item + 1, t), v[item] + solve(item + 1, t - 3 * w * d[item]));
		}
		else
			return memo[item][t] = solve(item + 1, t);
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